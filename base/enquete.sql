-- phpMyAdmin SQL Dump
-- version 4.0.2
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 02 Novembre 2022 à 17:11
-- Version du serveur: 5.6.11-log
-- Version de PHP: 5.3.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `enquete`
--
CREATE DATABASE IF NOT EXISTS `enquete` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `enquete`;

DELIMITER $$
--
-- Procédures
--
DROP PROCEDURE IF EXISTS `getEvolutionPrix`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getEvolutionPrix`(idm INT,m Varchar(10))
BEGIN                 
                SELECT COALESCE(prixavg,0) nbpointdevente          
                FROM (
                SELECT id,idmarque,idproduit,
                        floor(avg(prix)) prixavg
                FROM `detailresultatdn`
                WHERE mois = m and existence = true group by produit
                ) AS tony
                RIGHT JOIN produit ON idproduit = produit.id
                RIGHT JOIN marque ON produit.idmarque = marque.id
                WHERE produit.idmarque =idm
                GROUP BY produit.id;
    END$$

DROP PROCEDURE IF EXISTS `getEvolutionRotation`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getEvolutionRotation`(idm INT,m Varchar(10))
BEGIN                 
                SELECT COALESCE(rotationavg,0) nbpointdevente          
                FROM (
                SELECT id,idmarque,idproduit,
                        floor(avg(rotation)) rotationavg
                FROM `detailresultatdn`
                WHERE mois = m and existence = true group by produit
                ) AS tony
                RIGHT JOIN produit ON idproduit = produit.id
                RIGHT JOIN marque ON produit.idmarque = marque.id
                WHERE produit.idmarque =idm
                GROUP BY produit.id;
    END$$

DROP PROCEDURE IF EXISTS `getnbpdevente`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getnbpdevente`(idm INT,m Varchar(10))
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

DROP PROCEDURE IF EXISTS `getnbpdeventeparCategorie`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getnbpdeventeparCategorie`(idcategorie INT,m Varchar(10))
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

DROP PROCEDURE IF EXISTS `getnomParCategorie`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getnomParCategorie`(idcategorie INT,m Varchar(10))
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

DROP PROCEDURE IF EXISTS `getnomparmarque`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getnomparmarque`(idm INT,m Varchar(10))
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

DROP PROCEDURE IF EXISTS `getnomStatConcurrenceParCategorie`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getnomStatConcurrenceParCategorie`(idcategorie INT,m Varchar(10))
BEGIN
             SELECT  
                produit.nom        
            FROM (
            SELECT id,idmarque,idproduit,
                    count(*) pointdeventemisy
            FROM `detailresultatdn`
            WHERE mois = m  group by produit
            ) AS tony
            RIGHT JOIN produit ON idproduit = produit.id
            RIGHT JOIN marque ON produit.idmarque = marque.id
            where marque.idcategorie = idcategorie
            GROUP BY produit.id;
                END$$

DROP PROCEDURE IF EXISTS `getPrixConcurrenceCategorie`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getPrixConcurrenceCategorie`(idcategorie INT,m Varchar(10))
BEGIN
            SELECT  
            COALESCE(prixavg,0) prixavg          
            FROM (
            SELECT id,idmarque,idproduit,
                    floor(avg(prix)) prixavg
            FROM `detailresultatdn`
            WHERE mois = m and existence = true group by produit
            ) AS tony
            RIGHT JOIN produit ON idproduit = produit.id
            RIGHT JOIN marque ON produit.idmarque = marque.id
            where marque.idcategorie = idcategorie
            GROUP BY produit.id;
    END$$

DROP PROCEDURE IF EXISTS `getRotationConcurrenceCategorie`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getRotationConcurrenceCategorie`(idcategorie INT,m Varchar(10))
BEGIN
            SELECT  
            COALESCE(rotationavg,0) rotation          
            FROM (
            SELECT id,idmarque,idproduit,
                    floor(avg(rotation)) rotationavg
            FROM `detailresultatdn`
            WHERE mois = m and existence = true group by produit
            ) AS tony
            RIGHT JOIN produit ON idproduit = produit.id
            RIGHT JOIN marque ON produit.idmarque = marque.id
            where marque.idcategorie = idcategorie
            GROUP BY produit.id;
    END$$

DROP PROCEDURE IF EXISTS `getStatConcurrenceCategorie`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getStatConcurrenceCategorie`(idcategorie INT,m Varchar(10))
BEGIN
            SELECT  
            COALESCE(pointdeventemisy,0) nbpointdevente          
            FROM (
            SELECT id,idmarque,idproduit,
                    count(*) pointdeventemisy
            FROM `detailresultatdn`
            WHERE mois = m and existence = true group by produit
            ) AS tony
            RIGHT JOIN produit ON idproduit = produit.id
            RIGHT JOIN marque ON produit.idmarque = marque.id
            where marque.idcategorie = idcategorie
            GROUP BY produit.id;
    END$$

DROP PROCEDURE IF EXISTS `getTotalPointdeVente`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getTotalPointdeVente`(idm INT,m Varchar(10))
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

DROP PROCEDURE IF EXISTS `getTotalPointdeVenteParCategorie`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getTotalPointdeVenteParCategorie`(idcategorie INT,m Varchar(10))
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

DROP PROCEDURE IF EXISTS `getTotalStatConcurrenceParCategorie`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getTotalStatConcurrenceParCategorie`(idcategorie INT,m Varchar(10))
BEGIN
             SELECT  
                COALESCE(pointdeventemisy,0) nbpointdevente          
            FROM (
            SELECT id,idmarque,idproduit,
                    count(*) pointdeventemisy
            FROM `detailresultatdn`
            WHERE mois = m  group by produit
            ) AS tony
            RIGHT JOIN produit ON idproduit = produit.id
            RIGHT JOIN marque ON produit.idmarque = marque.id
            where marque.idcategorie = idcategorie
            GROUP BY produit.id;
                END$$

--
-- Fonctions
--
DROP FUNCTION IF EXISTS `current_dn`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `current_dn`( idp INTEGER ) RETURNS int(11)
    DETERMINISTIC
BEGIN
	declare nbpointdevente INTEGER;
        declare total INTEGER;
        declare resultat INTEGER;
        select pointdeventemisy from dncourant where idproduit =idp order by mois desc limit 1 into nbpointdevente;
        SELECT count(*) pointdeventemisy
                FROM `detailresultatdn`
        WHERE idproduit =idp group by produit,mois order by mois desc limit 1 into total;
        return (100*nbpointdevente/total);
    END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `appreciation`
--

DROP TABLE IF EXISTS `appreciation`;
CREATE TABLE IF NOT EXISTS `appreciation` (
  `id` int(11) NOT NULL,
  `idproduitdegustation` int(11) DEFAULT NULL,
  `idresultatdegustation` int(11) DEFAULT NULL,
  `valeur` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idresultatdegustation` (`idresultatdegustation`),
  KEY `idproduitdegustation` (`idproduitdegustation`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `etat` varchar(10) DEFAULT NULL,
  `nom` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `categorie`
--

INSERT INTO `categorie` (`id`, `etat`, `nom`) VALUES
(1, 'active', 'Lait en poudre'),
(2, 'active', 'Huiles'),
(3, 'active', 'Lait en brique');

-- --------------------------------------------------------

--
-- Structure de la table `choixcible`
--

DROP TABLE IF EXISTS `choixcible`;
CREATE TABLE IF NOT EXISTS `choixcible` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `etat` varchar(10) DEFAULT NULL,
  `idcible` int(11) DEFAULT NULL,
  `nom` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idcible` (`idcible`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `choixcible`
--

INSERT INTO `choixcible` (`id`, `etat`, `idcible`, `nom`) VALUES
(1, 'active', 1, ' tres intense'),
(2, 'active', 1, 'intense'),
(3, 'active', 1, 'normal');

-- --------------------------------------------------------

--
-- Structure de la table `cible`
--

DROP TABLE IF EXISTS `cible`;
CREATE TABLE IF NOT EXISTS `cible` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `etat` varchar(10) DEFAULT NULL,
  `nom` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `cible`
--

INSERT INTO `cible` (`id`, `etat`, `nom`) VALUES
(1, 'active', 'Couleur');

-- --------------------------------------------------------

--
-- Structure de la table `degustation`
--

DROP TABLE IF EXISTS `degustation`;
CREATE TABLE IF NOT EXISTS `degustation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `debut` date DEFAULT NULL,
  `fin` date DEFAULT NULL,
  `idcategorie` int(11) DEFAULT NULL,
  `titre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idcategorie` (`idcategorie`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Contenu de la table `degustation`
--

INSERT INTO `degustation` (`id`, `debut`, `fin`, `idcategorie`, `titre`) VALUES
(1, '2022-10-18', '2022-10-18', 1, 'Degustation de Lait en poudre'),
(7, '2022-10-19', '2022-10-19', 2, 'Enquete'),
(8, '2022-10-19', '2022-10-19', 1, 'Degustation de Lait en poudre farany'),
(9, '2022-10-19', '2022-10-19', 1, 'Degustation de Lait en poudre farany2'),
(10, '2022-10-19', '2022-10-19', 1, 'tesyt'),
(11, '2022-10-19', '2022-10-19', 1, 'vaovao');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `detaildn`
--
DROP VIEW IF EXISTS `detaildn`;
CREATE TABLE IF NOT EXISTS `detaildn` (
`id` int(11)
,`titre` varchar(100)
,`debut` date
,`fin` date
,`idunite` int(11)
,`unite` varchar(50)
,`categorie` varchar(30)
,`idcategorie` int(11)
);
-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `detailmarque`
--
DROP VIEW IF EXISTS `detailmarque`;
CREATE TABLE IF NOT EXISTS `detailmarque` (
`id` int(11)
,`nom` varchar(50)
,`etat` varchar(10)
,`categorie` varchar(30)
,`idcategorie` int(11)
,`societe` varchar(50)
,`idsociete` int(11)
);
-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `detailproduit`
--
DROP VIEW IF EXISTS `detailproduit`;
CREATE TABLE IF NOT EXISTS `detailproduit` (
`id` int(11)
,`description` varchar(70)
,`nom` varchar(30)
,`etat` varchar(10)
,`marque` varchar(50)
,`categorie` varchar(30)
,`societe` varchar(50)
,`idsociete` int(11)
,`idcategorie` int(11)
,`idmarque` int(11)
);
-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `detailproduitdegustation`
--
DROP VIEW IF EXISTS `detailproduitdegustation`;
CREATE TABLE IF NOT EXISTS `detailproduitdegustation` (
`id` int(11)
,`marque` varchar(50)
,`produit` varchar(30)
,`iddegustation` int(11)
,`idproduit` bigint(11)
,`idmarque` int(11)
);
-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `detailproduitdn`
--
DROP VIEW IF EXISTS `detailproduitdn`;
CREATE TABLE IF NOT EXISTS `detailproduitdn` (
`id` int(11)
,`marque` varchar(50)
,`produit` varchar(30)
,`iddn` int(11)
,`idproduit` bigint(11)
,`idmarque` int(11)
);
-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `detailquartier`
--
DROP VIEW IF EXISTS `detailquartier`;
CREATE TABLE IF NOT EXISTS `detailquartier` (
`id` int(11)
,`etat` varchar(20)
,`nom` varchar(30)
,`idville` int(11)
,`villenom` varchar(30)
);
-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `detailquestion`
--
DROP VIEW IF EXISTS `detailquestion`;
CREATE TABLE IF NOT EXISTS `detailquestion` (
`id` int(11)
,`question` varchar(100)
,`idcible` int(11)
,`iddegustation` int(11)
,`nomcible` varchar(30)
);
-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `detailresultatdn`
--
DROP VIEW IF EXISTS `detailresultatdn`;
CREATE TABLE IF NOT EXISTS `detailresultatdn` (
`id` varchar(20)
,`mois` varchar(7)
,`annee` int(4)
,`idmarque` int(11)
,`idproduit` int(11)
,`prix` double
,`rotation` double
,`existence` bit(1)
,`marque` varchar(50)
,`produit` varchar(30)
,`idcategorie` int(11)
);
-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `detailutilisateur`
--
DROP VIEW IF EXISTS `detailutilisateur`;
CREATE TABLE IF NOT EXISTS `detailutilisateur` (
`nom` varchar(50)
,`id` int(11)
,`email` varchar(50)
,`etat` varchar(10)
,`matricule` varchar(30)
,`motdepasse` varchar(100)
,`prenom` varchar(50)
,`role` varchar(30)
,`fonction` varchar(20)
,`idfonction` int(11)
);
-- --------------------------------------------------------

--
-- Structure de la table `dn`
--

DROP TABLE IF EXISTS `dn`;
CREATE TABLE IF NOT EXISTS `dn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `debut` date DEFAULT NULL,
  `fin` date DEFAULT NULL,
  `titre` varchar(100) DEFAULT NULL,
  `idunite` int(11) DEFAULT NULL,
  `idcategorie` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idunite` (`idunite`),
  KEY `idcategorie` (`idcategorie`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `dn`
--

INSERT INTO `dn` (`id`, `debut`, `fin`, `titre`, `idunite`, `idcategorie`) VALUES
(1, '2022-09-14', '2022-09-14', 'titre', 1, 1),
(2, '2022-09-13', '2022-09-19', 'Enquete', 1, 1),
(3, '2022-10-20', '2022-10-27', 'Lait en brique Dn', 1, 3),
(4, '2022-11-01', '2022-11-01', 'ddd', 1, 3);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `dncourant`
--
DROP VIEW IF EXISTS `dncourant`;
CREATE TABLE IF NOT EXISTS `dncourant` (
`idproduit` int(11)
,`produit` varchar(30)
,`mois` varchar(7)
,`pointdeventemisy` bigint(21)
);
-- --------------------------------------------------------

--
-- Structure de la table `emplacement`
--

DROP TABLE IF EXISTS `emplacement`;
CREATE TABLE IF NOT EXISTS `emplacement` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `idresultatdn` varchar(20) DEFAULT NULL,
  `latitude` varchar(20) DEFAULT NULL,
  `longitude` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idresultatdn` (`idresultatdn`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Contenu de la table `emplacement`
--

INSERT INTO `emplacement` (`id`, `idresultatdn`, `latitude`, `longitude`) VALUES
(7, '2', '-18.63.36', '46.05.63'),
(8, '2', '-19.38.33', '47.41.66'),
(9, '2', '-13.20.36', '49.66.23'),
(10, '2', '-18.95.00', '46.95.00'),
(11, '2', '-15.20.00', '48.40.00'),
(12, '2', '-16.30.00', ' 46.10.00');

-- --------------------------------------------------------

--
-- Structure de la table `enqueteur`
--

DROP TABLE IF EXISTS `enqueteur`;
CREATE TABLE IF NOT EXISTS `enqueteur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `etat` varchar(10) DEFAULT NULL,
  `matricule` varchar(30) DEFAULT NULL,
  `motdepasse` varchar(100) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `enqueteur`
--

INSERT INTO `enqueteur` (`id`, `email`, `etat`, `matricule`, `motdepasse`, `nom`, `prenom`) VALUES
(5, NULL, 'active', 'stg29', 'root', 'Randria', 'Tony');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `ficheenqueteur`
--
DROP VIEW IF EXISTS `ficheenqueteur`;
CREATE TABLE IF NOT EXISTS `ficheenqueteur` (
`id` int(20)
,`quartier` varchar(30)
,`iddn` int(11)
,`idenqueteur` int(11)
,`longitude` varchar(20)
,`latitude` varchar(20)
,`dateresultat` date
);
-- --------------------------------------------------------

--
-- Structure de la table `fonction`
--

DROP TABLE IF EXISTS `fonction`;
CREATE TABLE IF NOT EXISTS `fonction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `etat` varchar(10) DEFAULT NULL,
  `nom` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `fonction`
--

INSERT INTO `fonction` (`id`, `etat`, `nom`) VALUES
(1, 'active', 'Marketing');

-- --------------------------------------------------------

--
-- Structure de la table `infosresultatdegustation`
--

DROP TABLE IF EXISTS `infosresultatdegustation`;
CREATE TABLE IF NOT EXISTS `infosresultatdegustation` (
  `id` int(11) NOT NULL,
  `idproduitdegustation` int(11) DEFAULT NULL,
  `idresultatdegustation` int(11) DEFAULT NULL,
  `idchoixcible` int(11) DEFAULT NULL,
  `idquestion` int(11) DEFAULT NULL,
  `remarque` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idproduitdegustation` (`idproduitdegustation`),
  KEY `idresultatdegustation` (`idresultatdegustation`,`idchoixcible`,`idquestion`),
  KEY `idchoixcible` (`idchoixcible`),
  KEY `idquestion` (`idquestion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `infosresultatdn`
--

DROP TABLE IF EXISTS `infosresultatdn`;
CREATE TABLE IF NOT EXISTS `infosresultatdn` (
  `id` varchar(20) NOT NULL,
  `idproduitdn` int(11) DEFAULT NULL,
  `idresultatdn` varchar(20) DEFAULT NULL,
  `prix` double DEFAULT NULL,
  `rotation` double DEFAULT NULL,
  `existence` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idproduitdn` (`idproduitdn`),
  KEY `idresultatdn` (`idresultatdn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `infosresultatdn`
--

INSERT INTO `infosresultatdn` (`id`, `idproduitdn`, `idresultatdn`, `prix`, `rotation`, `existence`) VALUES
('1', 49, '1', 1500, 10, b'1'),
('10', 50, '1', NULL, NULL, b'1'),
('11', 50, '1', 5000, 10, b'1'),
('2', 49, '1', NULL, NULL, b'0'),
('21', 49, '2', NULL, NULL, b'0'),
('22', 49, '2', NULL, NULL, b'0'),
('23', 49, '2', 1500, 10, b'1'),
('24', 49, '2', 2000, 200, b'1'),
('3', 49, '1', NULL, NULL, b'0'),
('4', 49, '1', NULL, NULL, b'1'),
('5', 49, '1', 2000, 200, b'1'),
('6', 50, '1', 1500, 10, b'1'),
('7', 50, '1', 2000, 200, b'1'),
('8', 50, '1', NULL, NULL, b'0'),
('9', 50, '1', NULL, NULL, b'0');

-- --------------------------------------------------------

--
-- Structure de la table `marque`
--

DROP TABLE IF EXISTS `marque`;
CREATE TABLE IF NOT EXISTS `marque` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `etat` varchar(10) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `idcategorie` int(11) DEFAULT NULL,
  `idsociete` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idcategorie` (`idcategorie`),
  KEY `idsociete` (`idsociete`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `marque`
--

INSERT INTO `marque` (`id`, `etat`, `nom`, `idcategorie`, `idsociete`) VALUES
(1, 'active', 'Delice ', 1, 1),
(2, 'active', 'Socolait', 1, 5),
(5, 'active', 'Candia', 3, 6);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(70) DEFAULT NULL,
  `etat` varchar(10) DEFAULT NULL,
  `nom` varchar(30) DEFAULT NULL,
  `idmarque` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idmarque` (`idmarque`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `produit`
--

INSERT INTO `produit` (`id`, `description`, `etat`, `nom`, `idmarque`) VALUES
(1, 'chocolat en poudre', 'active', 'delchs250', 1),
(2, 'Entier', 'active', 'Entier', 5),
(3, 'Demi-Ecremee', 'active', 'Demi-Ecremee', 5),
(4, 'Ecreme', 'active', 'Ecreme', 5),
(5, 'Viva', 'active', 'Viva', 5);

-- --------------------------------------------------------

--
-- Structure de la table `produitdegustation`
--

DROP TABLE IF EXISTS `produitdegustation`;
CREATE TABLE IF NOT EXISTS `produitdegustation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iddegustation` int(11) DEFAULT NULL,
  `idmarque` int(11) DEFAULT NULL,
  `idproduit` int(11) DEFAULT NULL,
  `numeroproduit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idproduit` (`idproduit`),
  KEY `idproduit_2` (`idproduit`),
  KEY `idmarque` (`idmarque`),
  KEY `iddegustation` (`iddegustation`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Contenu de la table `produitdegustation`
--

INSERT INTO `produitdegustation` (`id`, `iddegustation`, `idmarque`, `idproduit`, `numeroproduit`) VALUES
(20, 1, 1, 0, 3);

-- --------------------------------------------------------

--
-- Structure de la table `produitdn`
--

DROP TABLE IF EXISTS `produitdn`;
CREATE TABLE IF NOT EXISTS `produitdn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iddn` int(11) DEFAULT NULL,
  `idmarque` int(11) DEFAULT NULL,
  `idproduit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `iddn` (`iddn`),
  KEY `idmarque` (`idmarque`),
  KEY `iddn_2` (`iddn`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=53 ;

--
-- Contenu de la table `produitdn`
--

INSERT INTO `produitdn` (`id`, `iddn`, `idmarque`, `idproduit`) VALUES
(45, 2, 1, 0),
(47, 1, 1, 1),
(48, 1, 1, 0),
(49, 3, 5, 2),
(50, 3, 5, 3),
(51, 3, 5, 4),
(52, 3, 5, 5);

-- --------------------------------------------------------

--
-- Structure de la table `quartier`
--

DROP TABLE IF EXISTS `quartier`;
CREATE TABLE IF NOT EXISTS `quartier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `etat` varchar(20) DEFAULT NULL,
  `nom` varchar(30) DEFAULT NULL,
  `idville` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idville` (`idville`),
  KEY `idville_2` (`idville`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `quartier`
--

INSERT INTO `quartier` (`id`, `etat`, `nom`, `idville`) VALUES
(1, 'active', 'Analakely', 1),
(2, 'active', 'Anosy', 1);

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

DROP TABLE IF EXISTS `question`;
CREATE TABLE IF NOT EXISTS `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idcible` int(11) DEFAULT NULL,
  `iddegustation` int(11) DEFAULT NULL,
  `question` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idcible` (`idcible`),
  KEY `iddegustation` (`iddegustation`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `question`
--

INSERT INTO `question` (`id`, `idcible`, `iddegustation`, `question`) VALUES
(2, 1, 1, 'Aonaa');

-- --------------------------------------------------------

--
-- Structure de la table `resultatdegustation`
--

DROP TABLE IF EXISTS `resultatdegustation`;
CREATE TABLE IF NOT EXISTS `resultatdegustation` (
  `id` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `frequence` int(11) DEFAULT NULL,
  `iddegustation` int(11) DEFAULT NULL,
  `sexe` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `iddegustation` (`iddegustation`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `resultatdn`
--

DROP TABLE IF EXISTS `resultatdn`;
CREATE TABLE IF NOT EXISTS `resultatdn` (
  `id` varchar(20) NOT NULL,
  `iddn` int(11) DEFAULT NULL,
  `idenqueteur` int(11) DEFAULT NULL,
  `idquartier` int(11) DEFAULT NULL,
  `dateresultat` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idquartier` (`idquartier`),
  KEY `idenqueteur` (`idenqueteur`),
  KEY `iddn` (`iddn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `resultatdn`
--

INSERT INTO `resultatdn` (`id`, `iddn`, `idenqueteur`, `idquartier`, `dateresultat`) VALUES
('1', 3, 5, 1, '2022-10-20'),
('2', 3, 5, 1, '2022-11-11');

-- --------------------------------------------------------

--
-- Structure de la table `resultatparproduit`
--

DROP TABLE IF EXISTS `resultatparproduit`;
CREATE TABLE IF NOT EXISTS `resultatparproduit` (
  `produit` varchar(50) NOT NULL,
  `id` varchar(20) DEFAULT NULL,
  `nbpointdevente` int(11) DEFAULT NULL,
  `nom` varchar(50) NOT NULL,
  PRIMARY KEY (`produit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `resultattotal`
--

DROP TABLE IF EXISTS `resultattotal`;
CREATE TABLE IF NOT EXISTS `resultattotal` (
  `produit` varchar(50) NOT NULL,
  `id` varchar(20) DEFAULT NULL,
  `nbpointdevente` int(11) DEFAULT NULL,
  `nom` varchar(50) NOT NULL,
  PRIMARY KEY (`produit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `resultat_par_produit`
--
DROP VIEW IF EXISTS `resultat_par_produit`;
CREATE TABLE IF NOT EXISTS `resultat_par_produit` (
`idproduit` int(11)
,`produit` varchar(30)
,`nombre` bigint(21)
);
-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `rotation_prix`
--
DROP VIEW IF EXISTS `rotation_prix`;
CREATE TABLE IF NOT EXISTS `rotation_prix` (
`mois` varchar(7)
,`idproduit` int(11)
,`prix` double(17,0)
,`rotation` double(17,0)
);
-- --------------------------------------------------------

--
-- Structure de la table `societe`
--

DROP TABLE IF EXISTS `societe`;
CREATE TABLE IF NOT EXISTS `societe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `etat` varchar(10) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `idcategorie` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idcategorie` (`idcategorie`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `societe`
--

INSERT INTO `societe` (`id`, `etat`, `nom`, `idcategorie`) VALUES
(1, 'active', 'Habibo Group', 1),
(4, 'active', 'Habibo Group', 2),
(5, 'active', 'JB', 1),
(6, 'active', 'Habibo Group', 3);

-- --------------------------------------------------------

--
-- Structure de la table `unite`
--

DROP TABLE IF EXISTS `unite`;
CREATE TABLE IF NOT EXISTS `unite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `etat` varchar(10) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `unite`
--

INSERT INTO `unite` (`id`, `etat`, `nom`) VALUES
(1, 'active', 'Boites');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `etat` varchar(10) DEFAULT NULL,
  `matricule` varchar(30) DEFAULT NULL,
  `motdepasse` varchar(100) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `role` varchar(30) DEFAULT NULL,
  `fonction` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fonction` (`fonction`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `email`, `etat`, `matricule`, `motdepasse`, `nom`, `prenom`, `role`, `fonction`) VALUES
(1, NULL, 'active', 'stg29', 'stg29-mdp', 'Randria', 'Tony Fenoaina', 'responsable', 1);

-- --------------------------------------------------------

--
-- Structure de la table `valeurchoix`
--

DROP TABLE IF EXISTS `valeurchoix`;
CREATE TABLE IF NOT EXISTS `valeurchoix` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `etat` varchar(10) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `valeurchoix`
--

INSERT INTO `valeurchoix` (`id`, `etat`, `nom`) VALUES
(1, 'active', 'Tres Fort '),
(2, 'active', 'Fort'),
(3, 'active', 'Moyen'),
(4, 'active', 'Faible'),
(5, 'active', 'Tres Faible');

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

DROP TABLE IF EXISTS `ville`;
CREATE TABLE IF NOT EXISTS `ville` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `etat` varchar(30) DEFAULT NULL,
  `nom` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `ville`
--

INSERT INTO `ville` (`id`, `etat`, `nom`) VALUES
(1, 'active', 'Antananarivo'),
(2, 'active', 'Mahajanga'),
(4, 'active', 'Toamasina'),
(5, 'active', 'Antsirabe'),
(6, 'active', 'Toliara');

-- --------------------------------------------------------

--
-- Structure de la vue `detaildn`
--
DROP TABLE IF EXISTS `detaildn`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `detaildn` AS select `dn`.`id` AS `id`,`dn`.`titre` AS `titre`,`dn`.`debut` AS `debut`,`dn`.`fin` AS `fin`,`dn`.`idunite` AS `idunite`,`unite`.`nom` AS `unite`,`categorie`.`nom` AS `categorie`,`dn`.`idcategorie` AS `idcategorie` from ((`dn` join `categorie` on((`dn`.`idcategorie` = `categorie`.`id`))) join `unite` on((`dn`.`idunite` = `unite`.`id`)));

-- --------------------------------------------------------

--
-- Structure de la vue `detailmarque`
--
DROP TABLE IF EXISTS `detailmarque`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `detailmarque` AS select `marque`.`id` AS `id`,`marque`.`nom` AS `nom`,`marque`.`etat` AS `etat`,`categorie`.`nom` AS `categorie`,`categorie`.`id` AS `idcategorie`,`societe`.`nom` AS `societe`,`societe`.`id` AS `idsociete` from ((`marque` join `societe` on((`societe`.`id` = `marque`.`idsociete`))) join `categorie` on((`categorie`.`id` = `marque`.`idcategorie`)));

-- --------------------------------------------------------

--
-- Structure de la vue `detailproduit`
--
DROP TABLE IF EXISTS `detailproduit`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `detailproduit` AS select `produit`.`id` AS `id`,`produit`.`description` AS `description`,`produit`.`nom` AS `nom`,`produit`.`etat` AS `etat`,`marque`.`nom` AS `marque`,`categorie`.`nom` AS `categorie`,`societe`.`nom` AS `societe`,`societe`.`id` AS `idsociete`,`categorie`.`id` AS `idcategorie`,`marque`.`id` AS `idmarque` from (((`produit` join `marque` on((`marque`.`id` = `produit`.`idmarque`))) join `societe` on((`societe`.`id` = `marque`.`idsociete`))) join `categorie` on((`categorie`.`id` = `marque`.`idcategorie`)));

-- --------------------------------------------------------

--
-- Structure de la vue `detailproduitdegustation`
--
DROP TABLE IF EXISTS `detailproduitdegustation`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `detailproduitdegustation` AS select `produitdegustation`.`id` AS `id`,`marque`.`nom` AS `marque`,`produit`.`nom` AS `produit`,`produitdegustation`.`iddegustation` AS `iddegustation`,coalesce(`produit`.`id`,0) AS `idproduit`,`marque`.`id` AS `idmarque` from (((`produitdegustation` join `marque` on((`produitdegustation`.`idmarque` = `marque`.`id`))) left join `produit` on((`produitdegustation`.`idproduit` = `produit`.`id`))) left join `dn` on((`dn`.`id` = `produitdegustation`.`id`)));

-- --------------------------------------------------------

--
-- Structure de la vue `detailproduitdn`
--
DROP TABLE IF EXISTS `detailproduitdn`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `detailproduitdn` AS select `produitdn`.`id` AS `id`,`marque`.`nom` AS `marque`,`produit`.`nom` AS `produit`,`produitdn`.`iddn` AS `iddn`,coalesce(`produit`.`id`,0) AS `idproduit`,`marque`.`id` AS `idmarque` from (((`produitdn` join `marque` on((`produitdn`.`idmarque` = `marque`.`id`))) left join `produit` on((`produitdn`.`idproduit` = `produit`.`id`))) left join `dn` on((`dn`.`id` = `produitdn`.`id`)));

-- --------------------------------------------------------

--
-- Structure de la vue `detailquartier`
--
DROP TABLE IF EXISTS `detailquartier`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `detailquartier` AS select `quartier`.`id` AS `id`,`quartier`.`etat` AS `etat`,`quartier`.`nom` AS `nom`,`quartier`.`idville` AS `idville`,`ville`.`nom` AS `villenom` from (`quartier` join `ville` on((`quartier`.`idville` = `ville`.`id`)));

-- --------------------------------------------------------

--
-- Structure de la vue `detailquestion`
--
DROP TABLE IF EXISTS `detailquestion`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `detailquestion` AS select `question`.`id` AS `id`,`question`.`question` AS `question`,`question`.`idcible` AS `idcible`,`question`.`iddegustation` AS `iddegustation`,`cible`.`nom` AS `nomcible` from (`question` join `cible` on((`question`.`idcible` = `cible`.`id`)));

-- --------------------------------------------------------

--
-- Structure de la vue `detailresultatdn`
--
DROP TABLE IF EXISTS `detailresultatdn`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `detailresultatdn` AS select `infosresultatdn`.`id` AS `id`,concat(year(`resultatdn`.`dateresultat`),'-',month(`resultatdn`.`dateresultat`)) AS `mois`,year(`resultatdn`.`dateresultat`) AS `annee`,`produitdn`.`idmarque` AS `idmarque`,`produitdn`.`idproduit` AS `idproduit`,`infosresultatdn`.`prix` AS `prix`,`infosresultatdn`.`rotation` AS `rotation`,`infosresultatdn`.`existence` AS `existence`,`marque`.`nom` AS `marque`,`produit`.`nom` AS `produit`,`marque`.`idcategorie` AS `idcategorie` from ((((`resultatdn` join `infosresultatdn` on((`infosresultatdn`.`idresultatdn` = `resultatdn`.`id`))) join `produitdn` on((`produitdn`.`id` = `infosresultatdn`.`idproduitdn`))) join `produit` on((`produitdn`.`idproduit` = `produit`.`id`))) join `marque` on((`produit`.`idmarque` = `marque`.`id`)));

-- --------------------------------------------------------

--
-- Structure de la vue `detailutilisateur`
--
DROP TABLE IF EXISTS `detailutilisateur`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `detailutilisateur` AS select `utilisateur`.`nom` AS `nom`,`utilisateur`.`id` AS `id`,`utilisateur`.`email` AS `email`,`utilisateur`.`etat` AS `etat`,`utilisateur`.`matricule` AS `matricule`,`utilisateur`.`motdepasse` AS `motdepasse`,`utilisateur`.`prenom` AS `prenom`,`utilisateur`.`role` AS `role`,`fonction`.`nom` AS `fonction`,`fonction`.`id` AS `idfonction` from (`utilisateur` join `fonction` on((`utilisateur`.`fonction` = `fonction`.`id`)));

-- --------------------------------------------------------

--
-- Structure de la vue `dncourant`
--
DROP TABLE IF EXISTS `dncourant`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dncourant` AS select `detailresultatdn`.`idproduit` AS `idproduit`,`detailresultatdn`.`produit` AS `produit`,`detailresultatdn`.`mois` AS `mois`,count(0) AS `pointdeventemisy` from `detailresultatdn` where (`detailresultatdn`.`existence` = 1) group by `detailresultatdn`.`produit`,`detailresultatdn`.`mois`;

-- --------------------------------------------------------

--
-- Structure de la vue `ficheenqueteur`
--
DROP TABLE IF EXISTS `ficheenqueteur`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ficheenqueteur` AS select `emplacement`.`id` AS `id`,`quartier`.`nom` AS `quartier`,`resultatdn`.`iddn` AS `iddn`,`resultatdn`.`idenqueteur` AS `idenqueteur`,`emplacement`.`longitude` AS `longitude`,`emplacement`.`latitude` AS `latitude`,`resultatdn`.`dateresultat` AS `dateresultat` from ((`emplacement` join `resultatdn` on((`emplacement`.`idresultatdn` = `resultatdn`.`id`))) join `quartier` on((`resultatdn`.`idquartier` = `quartier`.`id`)));

-- --------------------------------------------------------

--
-- Structure de la vue `resultat_par_produit`
--
DROP TABLE IF EXISTS `resultat_par_produit`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `resultat_par_produit` AS select `detailresultatdn`.`idproduit` AS `idproduit`,`detailresultatdn`.`produit` AS `produit`,count(0) AS `nombre` from `detailresultatdn` group by `detailresultatdn`.`idproduit`;

-- --------------------------------------------------------

--
-- Structure de la vue `rotation_prix`
--
DROP TABLE IF EXISTS `rotation_prix`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `rotation_prix` AS select `detailresultatdn`.`mois` AS `mois`,`detailresultatdn`.`idproduit` AS `idproduit`,floor(avg(`detailresultatdn`.`prix`)) AS `prix`,floor(avg(`detailresultatdn`.`rotation`)) AS `rotation` from `detailresultatdn` group by `detailresultatdn`.`idproduit`,`detailresultatdn`.`mois`;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `appreciation`
--
ALTER TABLE `appreciation`
  ADD CONSTRAINT `appreciation_ibfk_2` FOREIGN KEY (`idresultatdegustation`) REFERENCES `resultatdegustation` (`id`),
  ADD CONSTRAINT `appreciation_ibfk_1` FOREIGN KEY (`idproduitdegustation`) REFERENCES `produitdegustation` (`id`);

--
-- Contraintes pour la table `choixcible`
--
ALTER TABLE `choixcible`
  ADD CONSTRAINT `choixcible_ibfk_1` FOREIGN KEY (`idcible`) REFERENCES `cible` (`id`);

--
-- Contraintes pour la table `degustation`
--
ALTER TABLE `degustation`
  ADD CONSTRAINT `degustation_ibfk_1` FOREIGN KEY (`idcategorie`) REFERENCES `categorie` (`id`);

--
-- Contraintes pour la table `dn`
--
ALTER TABLE `dn`
  ADD CONSTRAINT `dn_ibfk_2` FOREIGN KEY (`idcategorie`) REFERENCES `categorie` (`id`),
  ADD CONSTRAINT `dn_ibfk_1` FOREIGN KEY (`idunite`) REFERENCES `unite` (`id`);

--
-- Contraintes pour la table `emplacement`
--
ALTER TABLE `emplacement`
  ADD CONSTRAINT `emplacement_ibfk_1` FOREIGN KEY (`idresultatdn`) REFERENCES `resultatdn` (`id`);

--
-- Contraintes pour la table `infosresultatdegustation`
--
ALTER TABLE `infosresultatdegustation`
  ADD CONSTRAINT `infosresultatdegustation_ibfk_4` FOREIGN KEY (`idquestion`) REFERENCES `question` (`id`),
  ADD CONSTRAINT `infosresultatdegustation_ibfk_1` FOREIGN KEY (`idproduitdegustation`) REFERENCES `produitdegustation` (`id`),
  ADD CONSTRAINT `infosresultatdegustation_ibfk_2` FOREIGN KEY (`idresultatdegustation`) REFERENCES `resultatdegustation` (`id`),
  ADD CONSTRAINT `infosresultatdegustation_ibfk_3` FOREIGN KEY (`idchoixcible`) REFERENCES `choixcible` (`idcible`);

--
-- Contraintes pour la table `infosresultatdn`
--
ALTER TABLE `infosresultatdn`
  ADD CONSTRAINT `infosresultatdn_ibfk_2` FOREIGN KEY (`idresultatdn`) REFERENCES `resultatdn` (`id`),
  ADD CONSTRAINT `infosresultatdn_ibfk_1` FOREIGN KEY (`idproduitdn`) REFERENCES `produitdn` (`id`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `produit_ibfk_1` FOREIGN KEY (`idmarque`) REFERENCES `marque` (`id`);

--
-- Contraintes pour la table `produitdegustation`
--
ALTER TABLE `produitdegustation`
  ADD CONSTRAINT `produitdegustation_ibfk_2` FOREIGN KEY (`idmarque`) REFERENCES `marque` (`id`),
  ADD CONSTRAINT `produitdegustation_ibfk_1` FOREIGN KEY (`iddegustation`) REFERENCES `degustation` (`id`);

--
-- Contraintes pour la table `produitdn`
--
ALTER TABLE `produitdn`
  ADD CONSTRAINT `produitdn_ibfk_2` FOREIGN KEY (`idmarque`) REFERENCES `marque` (`id`),
  ADD CONSTRAINT `produitdn_ibfk_1` FOREIGN KEY (`iddn`) REFERENCES `dn` (`id`);

--
-- Contraintes pour la table `quartier`
--
ALTER TABLE `quartier`
  ADD CONSTRAINT `quartier_ibfk_1` FOREIGN KEY (`idville`) REFERENCES `ville` (`id`);

--
-- Contraintes pour la table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `question_ibfk_2` FOREIGN KEY (`iddegustation`) REFERENCES `degustation` (`id`),
  ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`idcible`) REFERENCES `cible` (`id`);

--
-- Contraintes pour la table `resultatdegustation`
--
ALTER TABLE `resultatdegustation`
  ADD CONSTRAINT `resultatdegustation_ibfk_1` FOREIGN KEY (`iddegustation`) REFERENCES `degustation` (`id`);

--
-- Contraintes pour la table `resultatdn`
--
ALTER TABLE `resultatdn`
  ADD CONSTRAINT `resultatdn_ibfk_3` FOREIGN KEY (`idquartier`) REFERENCES `quartier` (`id`),
  ADD CONSTRAINT `resultatdn_ibfk_1` FOREIGN KEY (`iddn`) REFERENCES `dn` (`id`),
  ADD CONSTRAINT `resultatdn_ibfk_2` FOREIGN KEY (`idenqueteur`) REFERENCES `enqueteur` (`id`);

--
-- Contraintes pour la table `societe`
--
ALTER TABLE `societe`
  ADD CONSTRAINT `societe_ibfk_1` FOREIGN KEY (`idcategorie`) REFERENCES `categorie` (`id`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `utilisateur_ibfk_1` FOREIGN KEY (`fonction`) REFERENCES `fonction` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
