CREATE VIEW situation AS  SELECT sortie.datesortie,
    reservation.idvehicule,
    reservation.datedebut,
    infosparking.type,
    tarif.montant AS montanttarif,
    reservation.montant,
    tarif.durree AS durreenormaltarif,
        CASE
            WHEN ((reservation.datedebut >= getdatenowamn18h(reservation.datedebut)) OR (reservation.datedebut < getdateamn6rahampitso(reservation.datedebut))) 
				THEN ((reservation.datedebut + (tarif.durree)::interval) + ('00:35:00'::time without time zone)::interval)
            ELSE (reservation.datedebut + (tarif.durree)::interval)
        END AS datefin,
        CASE
            WHEN ((reservation.datedebut >= getdatenowamn18h(reservation.datedebut)) OR (reservation.datedebut < getdateamn6rahampitso(reservation.datedebut))) THEN (tarif.durree + ('00:35:00'::time without time zone)::interval)
            ELSE tarif.durree
        END AS durree,
        CASE
            WHEN (infosparking.type = 'infraction'::text) THEN 100000
            ELSE 0
        END AS amende
   FROM (((infosparking
     RIGHT JOIN reservation ON ((infosparking.idreservation = reservation.id)))
     LEFT JOIN sortie ON ((reservation.id = sortie.idreservation)))
     LEFT JOIN tarif ON ((tarif.id = reservation.idtarif)))




     CREATE VIEW infosparking AS  SELECT parking.id,
    parkingoccupe.nom,
    parkingoccupe.prenom,
    parkingoccupe.datesortie,
    COALESCE(parkingoccupe.idreservation, 0) AS idreservation,
    parking.numero AS numeroparking,
    COALESCE(parkingoccupe.idsortie, 0) AS idsortie,
    COALESCE(parkingoccupe.idclient, 0) AS idclient,
    COALESCE(parkingoccupe.idvehicule, '0'::character varying) AS idvehicule,
    parkingoccupe.datedebut,
    parking.etat,
        CASE
            WHEN ((parkingoccupe.datedebut >= getdatenowamn18h(parkingoccupe.datedebut)) OR (parkingoccupe.datedebut < getdateamn6rahampitso(parkingoccupe.datedebut))) THEN 
(parkingoccupe.datefin + ('00:35:00'::time without time zone)::interval)
            ELSE parkingoccupe.datefin
        END AS datefin,
    parkingoccupe.delaidepart,
        CASE
            WHEN ((parkingoccupe.datedebut >= getdatenowamn18h(parkingoccupe.datedebut)) or (parkingoccupe.datedebut < getdateamn6rahampitso(parkingoccupe.datedebut))) THEN (parkingoccupe.durree + ('00:35:00'::time without time zone)::interval)
            ELSE parkingoccupe.durree
        END AS durree,
        CASE
            WHEN (parkingoccupe.datesortie IS NULL) THEN
            CASE
                WHEN (parkingoccupe.datefin < getdatenow()) THEN 'infraction'::text
                WHEN (parkingoccupe.datefin >= getdatenow()) THEN 'occupee'::text
                ELSE 'libre'::text
            END
            ELSE
            CASE
                WHEN ((parkingoccupe.datefin < getdatenow()) AND (getdatenow() < parkingoccupe.datesortie)) THEN 'infraction'::text
                WHEN ((getdatenow() <= parkingoccupe.datesortie) AND (parkingoccupe.datesortie <= parkingoccupe.datefin)) THEN 'occupee'::text
                ELSE 'libre'::text
            END
        END AS type,
    COALESCE(parkingoccupe.montant, (0.0)::double precision) AS montant
   FROM (parkingoccupe
     RIGHT JOIN parking ON ((parking.id = parkingoccupe.id)))
  ORDER BY parking.numero