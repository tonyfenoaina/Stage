


 CREATE  TABLE "public".admins ( 
	id                   integer  NOT NULL  ,
	nom                  varchar(30)  NOT NULL  ,
	prenom               varchar(100)    ,
	email                varchar(25)    ,
	mdp                  varchar(100)    ,
	CONSTRAINT admins_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".client ( 
	id                   integer  NOT NULL  ,
	nom                  varchar(30)  NOT NULL  ,
	prenom               varchar(100)    ,
	email                varchar(25)    ,
	mdp                  varchar(100)    ,
	CONSTRAINT admins_pkey_0 PRIMARY KEY ( id )
 );

CREATE  TABLE "public".parking ( 
	id                   integer  NOT NULL  ,
	taille               double precision    ,
	numero               integer    ,
	CONSTRAINT parking_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".pfvalide ( 
	id                   integer  NOT NULL  ,
	idclient             integer    ,
	montant              double precision    ,
	idpfvirtuel          integer    ,
	CONSTRAINT pk_pfvirtuelle_0 PRIMARY KEY ( id )
 );

CREATE  TABLE "public".pfvirtuelle ( 
	id                   integer  NOT NULL  ,
	idclient             integer    ,
	montant              double precision    ,
	CONSTRAINT pk_pfvirtuelle PRIMARY KEY ( id )
 );

CREATE  TABLE "public".tarif ( 
	id                   integer  NOT NULL  ,
	durree               time    ,
	montant              double precision    ,
	CONSTRAINT tarif_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".amende ( 
	id                   integer  NOT NULL  ,
	idclient             integer    ,
	montant              double precision    ,
	CONSTRAINT amende_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".amendepaye ( 
	id                   integer  NOT NULL  ,
	idamende             integer    ,
	idclient             integer    ,
	CONSTRAINT amende_paye_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".reservation ( 
	id                   integer  NOT NULL  ,
	datedebut            timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL  ,
	idparking            integer    ,
	idvehicule           varchar(20)    ,
	idtarif              integer    ,
	idclient             integer    ,
	CONSTRAINT reservation_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".sortie ( 
	id                   integer  NOT NULL  ,
	idreservation        integer    ,
	CONSTRAINT sortie_pkey PRIMARY KEY ( id )
 );

ALTER TABLE "public".amende ADD CONSTRAINT fk_amende_client FOREIGN KEY ( idclient ) REFERENCES "public".client( id );

ALTER TABLE "public".amendepaye ADD CONSTRAINT fk_amendepaye_client FOREIGN KEY ( idclient ) REFERENCES "public".client( id );

ALTER TABLE "public".pfvalide ADD CONSTRAINT fk_pfvalide_client FOREIGN KEY ( idclient ) REFERENCES "public".client( id );

ALTER TABLE "public".pfvirtuelle ADD CONSTRAINT fk_pfvirtuelle_client FOREIGN KEY ( idclient ) REFERENCES "public".client( id );

ALTER TABLE "public".reservation ADD CONSTRAINT fk_reservation_client FOREIGN KEY ( idclient ) REFERENCES "public".client( id );

ALTER TABLE "public".reservation ADD CONSTRAINT fk_reservation_parking FOREIGN KEY ( idparking ) REFERENCES "public".parking( id );

ALTER TABLE "public".reservation ADD CONSTRAINT fk_reservation_tarif FOREIGN KEY ( idtarif ) REFERENCES "public".tarif( id );

ALTER TABLE "public".sortie ADD CONSTRAINT fk_sortie_reservation FOREIGN KEY ( idreservation ) REFERENCES "public".reservation( id );

CREATE OR REPLACE view amendenonpayee AS SELECT amende.id AS amende,
    amende.idclient AS id,
    client.nom,
    client.prenom,
    amende.montant
   FROM ((amende
     JOIN client ON ((amende.idclient = client.id)))
     LEFT JOIN amendepaye ON ((amendepaye.idamende = amende.id)))
  WHERE (amendepaye.idamende IS NULL);

CREATE VIEW "public".infosparking AS  SELECT parking.id,
    client.nom,
    client.prenom,
    COALESCE(reservation.id, 0) AS idreservation,
    parking.numero AS numeroparking,
    COALESCE(sortie.idreservation, 0) AS idsortie,
    COALESCE(reservation.idclient, 0) AS idclient,
    COALESCE(reservation.idvehicule, '0'::character varying) AS idvehicule,
    reservation.datedebut,
	reservation.datedebut + tarif.durree as datefin,
	(reservation.datedebut + tarif.durree) - current_timestamp  as delaidepart,
    tarif.durree,
    COALESCE(tarif.montant, (0.0)::double precision) AS montant
   FROM ((((parking
     LEFT JOIN reservation ON ((parking.id = reservation.idparking)))
     LEFT JOIN client ON ((client.id = reservation.idclient)))
     LEFT JOIN sortie ON ((reservation.id = sortie.idreservation)))
     LEFT JOIN tarif ON ((tarif.id = reservation.idtarif)));

CREATE VIEW "public".pfnonvalide AS  SELECT pfvirtuelle.id AS pfvirtuelle,
    pfvirtuelle.idclient AS id,
    client.nom,
    client.prenom,
    pfvirtuelle.montant
   FROM ((pfvirtuelle
     JOIN client ON ((pfvirtuelle.idclient = client.id)))
     LEFT JOIN pfvalide ON ((pfvalide.idpfvirtuel = pfvirtuelle.id)))
  WHERE (pfvalide.idpfvirtuel IS NULL);
    

	CREATE OR REPLACE FUNCTION getDatenow () RETURNS timestamp AS $$
    BEGIN
        return (SELECT datenowvalue from datenow order by id desc limit 1 );
    END;
    $$ LANGUAGE plpgsql;


    CREATE OR REPLACE FUNCTION getDatenowAmn18h (datedebut timestamp) RETURNS timestamp AS $$
    BEGIN
       return (select(datedebut::date + time'18:00:00'));
    END;
    $$ LANGUAGE plpgsql;


     CREATE OR REPLACE FUNCTION getDateAmn6rahampitso (datedebut timestamp) RETURNS timestamp AS $$
    BEGIN
       return (select(datedebut::date + time'06:00:00'));
    END;
    $$ LANGUAGE plpgsql;


 CREATE OR REPLACE FUNCTION getDatenowAmn15h (datedebut timestamp) RETURNS timestamp AS $$
    BEGIN
       return (select(datedebut::date + time'15:00:00'));
    END;
    $$ LANGUAGE plpgsql;


     CREATE OR REPLACE FUNCTION getDateAmn16rahampitso (datedebut timestamp) RETURNS timestamp AS $$
    BEGIN
       return (select(datedebut::date + time'16:00:00'));
    END;
    $$ LANGUAGE plpgsql;



    CREATE OR REPLACE FUNCTION getVolaPayena ( payant time) RETURNS numeric AS $$
    BEGIN
       return (select montant from tarif where durree>= payant order by durree limit 1);
    END;
    $$ LANGUAGE plpgsql;



CREATE VIEW ${schema}.${name} AS  SELECT parking.id,
    client.nom,
    client.prenom,
	sortie.datesortie,
    COALESCE(reservation.id, 0) AS idreservation,
    parking.numero AS numeroparking,
    COALESCE(sortie.idreservation, 0) AS idsortie,
    COALESCE(reservation.idclient, 0) AS idclient,
    COALESCE(reservation.idvehicule, '0'::character varying) AS idvehicule,
    reservation.datedebut,
    (reservation.datedebut + (tarif.durree)::interval) AS datefin,
    (((reservation.datedebut + (tarif.durree)::interval))::timestamp with time zone - CURRENT_TIMESTAMP) AS delaidepart,
    tarif.durree,
    COALESCE(tarif.montant, (0.0)::double precision) AS montant
   FROM ((((parking
     JOIN reservation ON ((parking.id = reservation.idparking)))
     LEFT JOIN client ON ((client.id = reservation.idclient)))
     LEFT JOIN sortie ON ((reservation.id = sortie.idreservation)))
     LEFT JOIN tarif ON ((tarif.id = reservation.idtarif)))
  WHERE ((reservation.datedebut < getdatenow()) AND sortie.datesortie is null or sortie.datesortie<getDatenow())
  ORDER BY parking.numero



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
    parkingoccupe.datefin,
    parkingoccupe.delaidepart,
    parkingoccupe.durree,
        CASE
            WHEN (parkingoccupe.datesortie IS NULL) THEN
            CASE
                WHEN (parkingoccupe.datefin < getdatenow()) THEN 'infraction'::text
                WHEN (parkingoccupe.datefin > getdatenow()) THEN 'occupee'::text
                ELSE 'libre'::text
            END
            ELSE
            CASE
                WHEN ((parkingoccupe.datefin < getdatenow()) AND (getdatenow() < parkingoccupe.datesortie)) THEN 'infraction'::text
                WHEN ((getdatenow() < parkingoccupe.datesortie) AND (parkingoccupe.datesortie < parkingoccupe.datefin)) THEN 'occupee'::text
                ELSE 'libre'::text
            END
        END AS type,
    COALESCE(parkingoccupe.montant, (0.0)::double precision) AS montant
   FROM (parkingoccupe
     RIGHT JOIN parking ON ((parking.id = parkingoccupe.id)))
  ORDER BY parking.numero



        CASE
            WHEN (informations.dateDepartReelle IS NULL) THEN
            CASE
                WHEN (datedepart < getdatenow()) THEN 'infraction'::text
                WHEN (datedepart > getdatenow()) THEN 'occupee'::text
                ELSE 'libre'::text
            END
            ELSE
            CASE
                WHEN ((datedepart < getdatenow()) AND (getdatenow() < datedepart)) THEN 'infraction'::text
                WHEN ((getdatenow() < informations.dateDepartReelle) AND (informations.dateDepartReelle < datedepart)) THEN 'occupee'::text
                ELSE 'libre'::text
            END
        END AS remarque,



CREATE VIEW stat AS  SELECT infosparking.type,
    count(*) AS nombre
   FROM infosparking
  GROUP BY infosparking.type
  
alter TABLE parking ADD Column etat varchar(10) default 'active';








-- vaovao 01:20 

UPDATE reservation SET datedebut = '2022-07-18 23:30:00' WHERE idvehicule = '6666 TAA';
UPDATE reservation SET idtarif = 337 WHERE idvehicule = '6666 TAA';

UPDATE tarif SET montant = 337 WHERE id = 468;




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
        CASE
            WHEN ((parkingoccupe.datedebut >= getdatenowamn18h(parkingoccupe.datedebut)) AND (parkingoccupe.datedebut < getdateamn6rahampitso(parkingoccupe.datedebut))) THEN (parkingoccupe.datefin + ('00:35:00'::time without time zone)::interval)
            ELSE parkingoccupe.datefin
        END AS datefin,
    parkingoccupe.delaidepart,

   	CASE
           WHEN ((parkingoccupe.datedebut >= getdatenowamn18h(parkingoccupe.datedebut)) AND (parkingoccupe.datedebut < getdateamn6rahampitso(parkingoccupe.datedebut))) THEN ( parkingoccupe.durree + ('00:35:00'::time without time zone)::interval)
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


CREATE OR REPLACE VIEW situation AS  SELECT sortie.datesortie,
    reservation.idvehicule,
    reservation.datedebut,
    infosparking.type,
    reservation.montant,
        CASE
            WHEN ((reservation.datedebut >= getdatenowamn18h(reservation.datedebut)) OR (reservation.datedebut < getdateamn6rahampitso(reservation.datedebut))) THEN ((reservation.datedebut + (tarif.durree)::interval) + ('00:35:00'::time without time zone)::interval)
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
     LEFT JOIN tarif ON ((tarif.id = reservation.idtarif))) ;





---------------------------------- ty le vaovao




CREATE VIEW situation AS SELECT sortie.datesortie,
    reservation.idvehicule,
    reservation.datedebut,
    infosparking.type,
    tarif.montant AS montanttarif,
    reservation.montant,
    tarif.durree AS durreenormaltarif,
        CASE
            WHEN (((reservation.datedebut >= getdatenowamn18h(reservation.datedebut)) OR ((reservation.datedebut < getdateamn6rahampitso(reservation.datedebut)) 
            AND (tarif.durree <= '00:35:00'::time without time zone))) or 
            ((reservation.datedebut >= getdatenowamn15h(reservation.datedebut)) OR ((reservation.datedebut < getdateamn16rahampitso(reservation.datedebut)) 
            AND (tarif.durree <= '00:35:00'::time without time zone))))    
            THEN (reservation.datedebut + ('00:35:00'::time without time zone)::interval)
            ELSE (reservation.datedebut + (tarif.durree)::interval)
        END AS datefin,
        CASE
            WHEN (((reservation.datedebut >= getdatenowamn15h(reservation.datedebut)) OR ((reservation.datedebut < getdateamn16rahampitso(reservation.datedebut)) 
            AND (tarif.durree <= '00:35:00'::time without time zone))) or
            ((reservation.datedebut >= getdatenowamn15h(reservation.datedebut)) OR ((reservation.datedebut < getdateamn16rahampitso(reservation.datedebut)) 
            AND (tarif.durree <= '00:35:00'::time without time zone))))
            THEN '00:35:00'::time without time zone
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
            WHEN (((parkingoccupe.datedebut >= getdatenowamn18h(parkingoccupe.datedebut)) OR (parkingoccupe.datedebut < getdateamn6rahampitso(parkingoccupe.datedebut))
			 and parkingoccupe.durree<= '00:35:00' )) 
              THEN 
				(parkingoccupe.datedebut + ('00:35:00'::time without time zone)::interval)
            ELSE parkingoccupe.datedebut + parkingoccupe.durree
        END AS datefin,
    parkingoccupe.delaidepart,
        CASE
            WHEN (((parkingoccupe.datedebut >= getdatenowamn18h(parkingoccupe.datedebut)) OR (parkingoccupe.datedebut < getdateamn6rahampitso(parkingoccupe.datedebut)) 
			and parkingoccupe.durree<= '00:35:00'))

				THEN '00:35:00'
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





situationnnnnnnnnnnnnnnn

CREATE VIEW situation AS  SELECT sortie.datesortie,
    reservation.idvehicule,
    reservation.datedebut,
    infosparking.type,
    tarif.montant AS montanttarif,
    reservation.montant,
    tarif.durree AS durreenormaltarif,
        CASE
            WHEN ((reservation.datedebut >= getdatenowamn18h(reservation.datedebut)) OR ((reservation.datedebut < getdateamn6rahampitso(reservation.datedebut))
             AND (tarif.durree <= '00:35:00'::time without time zone)) OR ((reservation.datedebut >= getdatenowamn15h(reservation.datedebut)) OR ((reservation.datedebut < getdateamn16rahampitso(reservation.datedebut)) AND (tarif.durree <= '00:35:00'::time without time zone)))) THEN (reservation.datedebut + ('00:35:00'::time without time zone)::interval)
            ELSE (reservation.datedebut + (tarif.durree)::interval)
        END AS datefin,
        CASE
            WHEN ((reservation.datedebut >= getdatenowamn15h(reservation.datedebut)) OR ((reservation.datedebut < getdateamn16rahampitso(reservation.datedebut)) AND (tarif.durree <= '00:35:00'::time without time zone)) OR ((reservation.datedebut >= getdatenowamn15h(reservation.datedebut)) OR ((reservation.datedebut < getdateamn16rahampitso(reservation.datedebut)) AND (tarif.durree <= '00:35:00'::time without time zone)))) THEN '00:35:00'::time without time zone
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


 







 CREATE VIEW ${schema}.${name} AS  SELECT sortie.datesortie,
    reservation.idvehicule,
    reservation.datedebut,
    infosparking.type,
    tarif.montant AS montanttarif,
	  CASE
         WHEN ((reservation.datedebut >= getdatenowamn18h(reservation.datedebut)) OR ((reservation.datedebut < getdateamn6rahampitso(reservation.datedebut)) AND 
				(tarif.durree <= '00:35:00'::time without time zone))) 
                then 0.0
         WHEN ((reservation.datedebut >= getdatenowamn18h(reservation.datedebut)) OR ((reservation.datedebut < getdateamn6rahampitso(reservation.datedebut)) AND 
				(tarif.durree <= '00:35:00'::time without time zone))) 
                then 0.0       

                
                
                
                
                
                
                
                 OR 
                ((reservation.datedebut >= getdatenowamn15h(reservation.datedebut)) 
                OR ((reservation.datedebut < getdateamn16rahampitso(reservation.datedebut)) AND (tarif.durree <= '00:35:00'::time without time zone)))) 
				THEN (0.0)
            ELSE (tarif.montant)
        END AS montant,
    tarif.durree AS durreenormaltarif,
        CASE
            WHEN ((reservation.datedebut >= getdatenowamn18h(reservation.datedebut)) OR ((reservation.datedebut < getdateamn6rahampitso(reservation.datedebut)) AND (tarif.durree <= '00:35:00'::time without time zone)) OR ((reservation.datedebut >= getdatenowamn15h(reservation.datedebut)) OR ((reservation.datedebut < getdateamn16rahampitso(reservation.datedebut)) AND (tarif.durree <= '00:35:00'::time without time zone)))) THEN (reservation.datedebut + ('00:35:00'::time without time zone)::interval)
            ELSE (reservation.datedebut + (tarif.durree)::interval)
        END AS datefin,
        CASE
            WHEN ((reservation.datedebut >= getdatenowamn15h(reservation.datedebut)) OR ((reservation.datedebut < getdateamn16rahampitso(reservation.datedebut)) AND (tarif.durree <= '00:35:00'::time without time zone)) OR ((reservation.datedebut >= getdatenowamn15h(reservation.datedebut)) OR ((reservation.datedebut < getdateamn16rahampitso(reservation.datedebut)) AND (tarif.durree <= '00:35:00'::time without time zone)))) THEN '00:35:00'::time without time zone
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








situationnnnnnnnnnnnnnnn farany

     SELECT sortie.datesortie,
    reservation.idvehicule,
    reservation.datedebut,
    infosparking.type,
    tarif.montant AS montanttarif,
        CASE
            WHEN (((reservation.datedebut >= getdatenowamn18h(reservation.datedebut)) OR ((reservation.datedebut < getdateamn6rahampitso(reservation.datedebut)))) 
			AND (tarif.durree <= '00:35:00'::time without time zone)) 
            then (0.0)
            WHEN
			((reservation.datedebut >= getdatenowamn15h(reservation.datedebut)) AND ((reservation.datedebut < getdateamn16rahampitso(reservation.datedebut)) 
            AND (tarif.durree <= '00:35:00'::time without time zone)))
			 THEN (0.0)::double precision
            ELSE getvolapayena((tarif.durree-'00:35:00')::time without time zone)
        END AS montant,
    tarif.durree AS durreenormaltarif,
        CASE
            WHEN ((((reservation.datedebut >= getdatenowamn18h(reservation.datedebut)) OR ((reservation.datedebut < getdateamn6rahampitso(reservation.datedebut)))) 
            AND (tarif.durree <= '00:35:00'::time without time zone)) 
            OR ((reservation.datedebut >= getdatenowamn15h(reservation.datedebut)) 
            AND (reservation.datedebut < getdateamn16rahampitso(reservation.datedebut)) 
            AND (tarif.durree <= '00:35:00'::time without time zone)))
            THEN (reservation.datedebut + ('00:35:00'::time without time zone)::interval)
            ELSE (reservation.datedebut + (tarif.durree)::interval)
        END AS datefin,
        CASE
            WHEN ((((reservation.datedebut >= getdatenowamn15h(reservation.datedebut)) OR ((reservation.datedebut < getdateamn16rahampitso(reservation.datedebut)))) AND 
            (tarif.durree <= '00:35:00'::time without time zone)) 
            OR ((reservation.datedebut >= getdatenowamn15h(reservation.datedebut)) AND (reservation.datedebut < getdateamn16rahampitso(reservation.datedebut)) 
            AND (tarif.durree <= '00:35:00'::time without time zone))) THEN '00:35:00'::time without time zone
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