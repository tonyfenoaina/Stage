create table Produit as select
	row_number() over() as id,
    concat('Produit',row_number() over()) as nom,
	FLOOR(random()*6)+1::integer  as idCategorie,
	FLOOR(random()*1000000)+1::integer  as prix,
    FLOOR(random()*100)+1::integer  as quantite,
    case when random()<0.5 then 'litre' else 'gramme' end  AS unite
from generate_series(1, 1) s(i);

create table categorie as select
	row_number() over() as id ,
    concat('Categorie',row_number() over()) as nom
from generate_series(1, 6) s(i);

create table equivalence as select
	row_number() over() as id ,
    concat('Equivalence',row_number() over()) as nom
from generate_series(1, 6) s(i);

create table stock as select
	row_number() over() as id ,
    FLOOR(random()*50)+1::integer  as idproduit,
    FLOOR(random()*100)+1::integer  as entree,
    FLOOR(random()*10)+1::integer  as sortie,
    current_date - concat((FLOOR(random()*1000)+1)::integer,'day')::interval datestock
from generate_series(1, 100) s(i);


insert into client(id,nom,prenom,email,mdp) values (nextval('seq_client'),'Tony','Fenoaina','tony@gmail.com','root');
	

SELECT pfvirtuelle.idclient as id ,
 client.nom as nom ,
  client.prenom as prenom ,
   pfvirtuelle.montant
    FROM pfvirtuelle join client on pfvirtuelle.idclient = client.id left join pfvalide on pfvalide.idpfvirtuel = pfvirtuelle.id 
	where pfvalide.idpfvirtuel is null ;

CREATE OR REPLACE VIEW pfnonvalide AS SELECT pfvirtuelle.idclient as id ,
 client.nom as nom ,
  client.prenom as prenom ,
   pfvirtuelle.montant
    FROM pfvirtuelle join client on pfvirtuelle.idclient = client.id left join pfvalide on pfvalide.idpfvirtuel = pfvirtuelle.id 
	where pfvalide.idpfvirtuel is null ;


 CREATE VIEW detailstock AS  SELECT 
    coalesce(sum(stock.entree),0) AS entree,
    coalesce(sum(stock.sortie),0) AS sortie,
    stock.datestock,
    produit.id,
    produit.nom,
    produit.prix
   FROM (stock
    RIGHT JOIN produit ON ((stock.idproduit = produit.id)))
  GROUP BY produit.id


  CREATE VIEW detailstock AS  SELECT COALESCE(sum(stock.entree), (0)::double precision) AS entree,
    COALESCE(sum(stock.sortie), (0)::double precision) AS sortie,
    produit.id,
    produit.nom,
    produit.prix
   FROM (stock
     RIGHT JOIN produit ON ((stock.idproduit = produit.id)))
  GROUP BY produit.id

Alter table produit add constraint fk_produit_categorie  FOREIGN_KEY (idcategorie ) references  categorie(id) 














--evaluation
INSERT INTO "public".categorie
	( id, nom) VALUES ( 1, sachet );

INSERT INTO "public".categorie
	( id, nom) VALUES ( 2, bouteille );

INSERT INTO "public".categorie
	( id, nom) VALUES ( 3, pack );

INSERT INTO "public".categorie
	( id, nom) VALUES ( 4, boite );

INSERT INTO "public".categorie
	( id, nom) VALUES ( 5, fromage );

INSERT INTO "public".categorie
	( id, nom) VALUES ( 6, barquette );
