import 'dart:io';
import 'package:my_app/Model/Enqueteur.dart';
import 'package:path/path.dart';
import 'package:path_provider/path_provider.dart';
import 'package:sqflite/sqflite.dart';

class DataBase {
  Future<Database> initializedDB() async {
    Directory documentDirectory = await getApplicationDocumentsDirectory();
    // String path = join(documentDirectory.path, 'enquete.db');
    return openDatabase(
      join(documentDirectory.path, 'enquetes6.db'),
      version: 2,
      onCreate: (Database db, int version) async {
        await db.execute(
          "CREATE TABLE enqueteur(id INTEGER PRIMARY KEY,nom VARCHAR(30),prenom VARCHAR(30),motdepasse VARCHAR(30),matricule VARCHAR(20),etat VARCHAR(20))",
        );
        await db.execute(
          "CREATE TABLE dn(id INTEGER PRIMARY KEY , titre VARCHAR(50),debut TEXT ,fin TEXT ,idunite INTEGER,idcategorie INTEGER)",
        );
        await db.execute(
          "CREATE TABLE produitdn(id INTEGER PRIMARY KEY , marque VARCHAR(30),produit VARCHAR(30) ,idmarque INTEGER ,idproduit INTEGER,iddn INTEGER)",
        );
        await db.execute(
          "CREATE TABLE resultatdn(id VARCHAR(30) PRIMARY KEY,idquartier INTEGER,idenqueteur INTEGER)",
        );
        await db.execute(
          "CREATE TABLE infosresultatdn(id VARCHAR(30) PRIMARY KEY,idproduitdn INTEGER,prix INTEGER,rotation INTEGER,idresultatdn INTEGER)",
        );
        await db.execute(
          "CREATE TABLE emplacement(id VARCHAR(30) PRIMARY KEY,idresultatdn INTEGER,longitude REAL,latitude REAL,idresultatdn INTEGER)",
        );
        await db.execute(
          "CREATE TABLE quartier(id VARCHAR(30) PRIMARY KEY,nom VARCHAR(30),etat VARCHAR(20))",
        );
      },
    );
  }
}
