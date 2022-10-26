--detailquartier
CREATE VIEW ${schema}.${name} AS
 select `enquete`.`quartier`.`id` AS `id`,`enquete`.`quartier`.`etat` AS `etat`,`enquete`.`quartier`.`nom` AS `nom`,`enquete`.`quartier`.`idville` AS `idville`,`enquete`.`ville`.`nom` AS `villenom` 
 from (`enquete`.`quartier` join `enquete`.`ville` on((`enquete`.`quartier`.`idville` = `enquete`.`ville`.`id`)))

--detailmarque
    select marque.id,marque.nom,marque.etat,categorie.nom categorie,categorie.id idcategorie,societe.nom societe,societe.id idsociete from marque join societe on societe.id = marque.idsociete 
    join categorie on categorie.id = marque.idcategorie 

--detailProduit
    select  produit.id,produit.description,
            produit.nom,
            produit.etat,marque.nom marque,categorie.nom categorie,societe.nom societe,societe.id idsociete,categorie.id idcategorie,marque.id idmarque
    from produit join marque on marque.id = produit.idmarque join  societe on societe.id = marque.idsociete
    join categorie on categorie.id = marque.idcategorie     

--detailutilisateur
    select utilisateur.nom,utilisateur.id, email, utilisateur.etat, matricule, motdepasse, prenom, utilisateur.role, fonction.nom fonction,fonction.id idfonction
FROM utilisateur join fonction on utilisateur.fonction = fonction.id

--detaildn
    select dn.id,dn.titre,dn.debut,dn.fin,dn.idunite,unite.nom unite,categorie.nom categorie,idcategorie 
    from dn join categorie on dn.idcategorie = categorie.id join unite on dn.idunite = unite.id

--detailproduitdn
CREATE or replace view detailproduitdn as 
    select produitdn.id id,marque.nom marque,produit.nom produit,produitdn.iddn,coalesce(produit.id,0) idproduit,marque.id idmarque
    from produitdn join marque on produitdn.idmarque = marque.id 
                   left join produit on produitdn.idproduit = produit.id 
                   left join dn on dn.id = produitdn.id

--detailproduitdegustation
    CREATE or replace view detailproduitdegustation as 
        select produitdegustation.id id,marque.nom marque,produit.nom produit,produitdegustation.iddegustation,coalesce(produit.id,0) idproduit,marque.id idmarque
        from produitdegustation join marque on produitdegustation.idmarque = marque.id 
                    left join produit on produitdegustation.idproduit = produit.id 
                    left join dn on dn.id = produitdegustation.id

--detailquestion
 CREATE or replace view detailquestion as 
        select question.id , question.question,question.idcible,question.iddegustation,cible.nom nomcible
        from question
        join cible on question.idcible = cible.id

-- detailresultatdn
CREATE or replace view detailresultatdn as
    select infosresultatdn.id,Concat(YEAR(resultatDn.dateresultat),"-",MONTH(resultatDn.dateresultat)) mois, YEAR(resultatDn.dateresultat) annee,produitdn.idmarque,produitdn.idproduit,
            infosresultatdn.prix,infosresultatdn.rotation,infosresultatdn.existence,
            marque.nom marque,produit.nom produit
    FROM resultatDn join infosresultatdn on infosresultatdn.idresultatdn = resultatDn.id
                    right join produitdn on produitdn.id = infosresultatdn.idproduitdn
                    right join produit on produitdn.idproduit = produit.id
                    right join marque on produit.idmarque = marque.id


-- Maka vue par mois   
    DELIMITER $$
CREATE PROCEDURE get_Par_mois(m Varchar(10)) 
    BEGIN
    Select marque.nom marque,produit.nom produit,count(*) from (SELECT * FROM `detailresultatdn` where mois = m) as tony right join produit on idproduit = produit.id
    right join marque on produit.idmarque = marque.id where idmarque = 5;             
    END$$
DELIMITER;

-- isan point de vente misy produit par marque par mois
SELECT  marque.nom marque ,produit.nom produit, 
           COALESCE(pointdeventemisy,0) nbpointdevente          
FROM (
SELECT *,
        count(*) pointdeventemisy
FROM `detailresultatdn`
WHERE mois = "2022-10" and existence = true group by produit
) AS tony
RIGHT JOIN produit ON idproduit = produit.id
RIGHT JOIN marque ON produit.idmarque = marque.id
WHERE produit.idmarque =5
GROUP BY produit.id
LIMIT 0 , 30

-- isan total point de vente nanaovana enquete par marque par mois
SELECT  marque.nom marque ,produit.nom produit, 
           COALESCE(pointdeventemisy,0) nbpointdevente          
FROM (
SELECT idproduit,
        count(*) pointdeventemisy
FROM `detailresultatdn`
WHERE mois = "2022-10"  group by produit
) AS tony
RIGHT JOIN produit ON idproduit = produit.id
RIGHT JOIN marque ON produit.idmarque = marque.id
WHERE produit.idmarque =5
GROUP BY produit.id
LIMIT 0 , 30

-- par categorie
SELECT  marque.nom marque , 
           COALESCE(pointdeventemisy,0) nbpointdevente          
FROM (
SELECT idmarque,idproduit,
        count(*) pointdeventemisy
FROM `detailresultatdn`
WHERE mois = "2022-10"  group by marque
) AS tony
RIGHT JOIN produit ON idproduit = produit.id
RIGHT JOIN marque ON produit.idmarque = marque.id
where marque.idcategorie = 1
GROUP BY marque.id

-- par categorie nb point de vente
SELECT  marque.nom marque , 
           COALESCE(pointdeventemisy,0) nbpointdevente          
FROM (
SELECT idmarque,idproduit,
        count(*) pointdeventemisy
FROM `detailresultatdn`
WHERE mois = "2022-10" existence = true group by marque
) AS tony
RIGHT JOIN produit ON idproduit = produit.id
RIGHT JOIN marque ON produit.idmarque = marque.id
where marque.idcategorie = 1
GROUP BY marque.id


-- manisa point de vente par mois
SELECT produit,count(*) isa FROM `detailresultatdn` WHERE idmarque=5 and existence = true group by idproduit 
SELECT produit,count(*) isa FROM `detailresultatdn` WHERE idmarque=5 and  group by idproduit 
-- vue ilaina
create or replace view resultat_par_produit as 
SELECT idproduit, produit, COUNT(*) nombre
FROM `detailresultatdn`
GROUP BY idproduit

-- total point de vente par marque par mois par annee group by produit
DELIMITER $$
CREATE PROCEDURE getnomparmarque(idm INT,m Varchar(10)) 
           BEGIN
                SELECT   
                produit.nom        
                FROM (
                SELECT id,idmarque,idproduit,
                        count(*) pointdeventemisy
                FROM `detailresultatdn`
                WHERE mois = m  group by produit
                ) AS detail
                RIGHT JOIN produit ON idproduit = produit.id
                RIGHT JOIN marque ON produit.idmarque = marque.id
                WHERE produit.idmarque =idm
                GROUP BY produit.id;
    END$$
DELIMITER;

DELIMITER $$
CREATE PROCEDURE getTotalPointdeVente(idm INT,m Varchar(10)) 
           BEGIN
                SELECT   
                COALESCE(pointdeventemisy,0) nbpointdevente          
                FROM (
                SELECT id,idmarque,idproduit,
                        count(*) pointdeventemisy
                FROM `detailresultatdn`
                WHERE mois = m  group by produit
                ) AS detail
                RIGHT JOIN produit ON idproduit = produit.id
                RIGHT JOIN marque ON produit.idmarque = marque.id
                WHERE produit.idmarque =idm
                GROUP BY produit.id;
    END$$
DELIMITER;


-- total point de vente misy entana par marque par mois par annee group by produit

DELIMITER $$
CREATE PROCEDURE getnbpdevente(idm INT,m Varchar(10)) 
     BEGIN
                 
                   SELECT COALESCE(pointdeventemisy,0) nbpointdevente          
                FROM (
                SELECT id,idmarque,idproduit,
                        count(*) pointdeventemisy
                FROM `detailresultatdn`
                WHERE mois = m and existence = true group by produit
                ) AS tony
                RIGHT JOIN produit ON idproduit = produit.id
                RIGHT JOIN marque ON produit.idmarque = marque.id
                WHERE produit.idmarque =idm
                GROUP BY produit.id;
    END$$
DELIMITER;


-- total point de vente par marque par mois par annee group by marque

DELIMITER $$
CREATE PROCEDURE getTotalPointdeVenteParCategorie(idcategorie INT,m Varchar(10)) 
           BEGIN
             SELECT  
                COALESCE(pointdeventemisy,0) nbpointdevente          
            FROM (
            SELECT id,idmarque,idproduit,
                    count(*) pointdeventemisy
            FROM `detailresultatdn`
            WHERE mois = m  group by marque
            ) AS tony
            RIGHT JOIN produit ON idproduit = produit.id
            RIGHT JOIN marque ON produit.idmarque = marque.id
            where marque.idcategorie = idcategorie
            GROUP BY marque.id;
                END$$
DELIMITER;

DELIMITER $$
CREATE PROCEDURE getnomParCategorie(idcategorie INT,m Varchar(10)) 
           BEGIN
             SELECT  
                marque.nom        
            FROM (
            SELECT id,idmarque,idproduit,
                    count(*) pointdeventemisy
            FROM `detailresultatdn`
            WHERE mois = m  group by marque
            ) AS tony
            RIGHT JOIN produit ON idproduit = produit.id
            RIGHT JOIN marque ON produit.idmarque = marque.id
            where marque.idcategorie = idcategorie
            GROUP BY marque.id;
                END$$
DELIMITER;


-- total point de vente misy entana par categorie par mois par annee group by marque
DELIMITER $$
CREATE PROCEDURE getnbpdeventeparCategorie(idcategorie INT,m Varchar(10)) 
     BEGIN
                 SELECT  
                COALESCE(pointdeventemisy,0) nbpointdevente          
            FROM (
            SELECT id,idmarque,idproduit,
                    count(*) pointdeventemisy
            FROM `detailresultatdn`
            WHERE mois = m and existence = true group by marque
            ) AS tony
            RIGHT JOIN produit ON idproduit = produit.id
            RIGHT JOIN marque ON produit.idmarque = marque.id
            where marque.idcategorie = idcategorie
            GROUP BY marque.id;
    END$$
DELIMITER;

-- Insert resultatDn

INSERT INTO `resultatdn`(id, iddn, idenqueteur, idquartier,dateresultat) VALUES ("2",3,5,1,"2022-11-11");
INSERT INTO `infosresultatdn`(`id`, `idproduitdn`, `idresultatdn`, `prix`, `rotation`, `existence`) VALUES ("21",49,"2",null,null,false);
INSERT INTO `infosresultatdn`(`id`, `idproduitdn`, `idresultatdn`, `prix`, `rotation`, `existence`) VALUES ("22",49,"2",null,null,false);
INSERT INTO `infosresultatdn`(`id`, `idproduitdn`, `idresultatdn`, `prix`, `rotation`, `existence`) VALUES ("23",49,"2",1500,10,true);
INSERT INTO `infosresultatdn`(`id`, `idproduitdn`, `idresultatdn`, `prix`, `rotation`, `existence`) VALUES ("24",49,"2",2000,200,true);

-- insert      
INSERT INTO `resultatdn`(id, iddn, idenqueteur, idquartier,dateresultat) VALUES ("1",3,5,1,now())                                                                 
INSERT INTO `infosresultatdn`(`id`, `idproduitdn`, `idresultatdn`, `prix`, `rotation`, `existence`) VALUES ("1",49,"1",1500,10,true);
INSERT INTO `infosresultatdn`(`id`, `idproduitdn`, `idresultatdn`, `prix`, `rotation`, `existence`) VALUES ("5",49,"1",2000,200,true);
INSERT INTO `infosresultatdn`(`id`, `idproduitdn`, `idresultatdn`, `prix`, `rotation`, `existence`) VALUES ("2",49,"1",null,null,false);
INSERT INTO `infosresultatdn`(`id`, `idproduitdn`, `idresultatdn`, `prix`, `rotation`, `existence`) VALUES ("3",49,"1",null,null,false);
INSERT INTO `infosresultatdn`(`id`, `idproduitdn`, `idresultatdn`, `prix`, `rotation`, `existence`) VALUES ("4",49,"1",null,null,true);

INSERT INTO `infosresultatdn`(`id`, `idproduitdn`, `idresultatdn`, `prix`, `rotation`, `existence`) VALUES ("6",50,"1",1500,10,true);
INSERT INTO `infosresultatdn`(`id`, `idproduitdn`, `idresultatdn`, `prix`, `rotation`, `existence`) VALUES ("11",50,"1",5000,10,true);
INSERT INTO `infosresultatdn`(`id`, `idproduitdn`, `idresultatdn`, `prix`, `rotation`, `existence`) VALUES ("7",50,"1",2000,200,true);
INSERT INTO `infosresultatdn`(`id`, `idproduitdn`, `idresultatdn`, `prix`, `rotation`, `existence`) VALUES ("8",50,"1",null,null,false);
INSERT INTO `infosresultatdn`(`id`, `idproduitdn`, `idresultatdn`, `prix`, `rotation`, `existence`) VALUES ("9",50,"1",null,null,false);
INSERT INTO `infosresultatdn`(`id`, `idproduitdn`, `idresultatdn`, `prix`, `rotation`, `existence`) VALUES ("10",50,"1",null,null,true);


INSERT INTO `infosresultatdn`(`id`, `idproduitdn`, `idresultatdn`, `prix`, `rotation`, `existence`) VALUES ("1",48,"1",1500,10,true)
INSERT INTO `infosresultatdn`(`id`, `idproduitdn`, `idresultatdn`, `prix`, `rotation`, `existence`) VALUES ("1",48,"1",1500,10,true);