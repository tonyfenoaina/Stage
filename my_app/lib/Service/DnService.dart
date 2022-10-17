import 'dart:convert';
import 'dart:ffi';
import 'package:geolocator/geolocator.dart';
import 'package:my_app/Model/Database.dart';
import 'package:my_app/Model/Dn.dart';
import 'package:my_app/Model/ProduitDn.dart';
import 'package:sqflite/sqflite.dart';
import 'package:json_helpers/json_helpers.dart';
import 'package:http/http.dart' as http;

//Service de mise a jours ato
class DnService {
  DataBase data = new DataBase();

  Future<int> insertDn(Dn e) async {
    int result;
    final Database db = await data.initializedDB();
    result = await db.insert('dn', e.toMap(),
        conflictAlgorithm: ConflictAlgorithm.replace);
    return result;
  }

  Future<int> insertProduitDn(ProduitDn e) async {
    int result;
    final Database db = await data.initializedDB();
    result = await db.insert('produitdn', e.toMap(),
        conflictAlgorithm: ConflictAlgorithm.replace);
    return result;
  }

  // Lister dn
  Future<List<Dn>> list() async {
    final Database db = await data.initializedDB();
    // final List<Map<String, Object?>> queryResult = await db.query('dn');
    final List<Map<String, Object?>> queryResult =
        await db.rawQuery('SELECT * FROM dn where debut < Date()');
    final List<Map<String, Object?>> date = await db.rawQuery('SELECT  Date()');

    // print("ty leiwyyyyyyyyyyyyyyyy" + date.toString());
    return queryResult.map((e) => Dn.fromMap(e)).toList();
  }

  Future<List<ProduitDn>> ProduitDnparId(var id) async {
    final Database db = await data.initializedDB();
    // final List<Map<String, Object?>> queryResult = await db.query('dn');
    final List<Map<String, Object?>> queryResult =
        await db.rawQuery('SELECT * FROM produitdn where iddn=?', [id]);
    // print("ty leiwyyyyyyyyyyyyyyyy" + date.toString());
    return queryResult.map((e) => ProduitDn.fromMap(e)).toList();
  }

  Future<List<ProduitDn>> listpProduitDn() async {
    final Database db = await data.initializedDB();
    // final List<Map<String, Object?>> queryResult = await db.query('dn');
    final List<Map<String, Object?>> queryResult =
        await db.rawQuery('SELECT * FROM produitdn');
    //final List<Map<String, Object?>> date = await db.rawQuery('SELECT  Date()');
    // print("ty leiwyyyyyyyyyyyyyyyy" + date.toString());
    return queryResult.map((e) => ProduitDn.fromMap(e)).toList();
  }

  // delete user
  Future<void> deleteDn(int id) async {
    final db = await data.initializedDB();
    await db.delete(
      'dn',
      where: "id = ?",
      whereArgs: [id],
    );
  }

////////Mise a jours de donnees

  List<Dn> parseProducts(String responseBody) {
    final parsed = json.decode(responseBody).cast<Map<String, dynamic>>();
    return parsed.map<Dn>((json) => Dn.fromJson(json)).toList();
  }

  List<ProduitDn> parseProduitDn(String responseBody) {
    final parsed = json.decode(responseBody).cast<Map<String, dynamic>>();
    return parsed.map<ProduitDn>((json) => ProduitDn.fromJson(json)).toList();
  }

  final String stockURL = "http://192.168.43.178:8181/miseajours_Dn";
  var ProduitDnURL = "http://192.168.43.178:8181/miseajours_ProduitDn";

  //Mise a jours des donnees Dn Myswl vers flutter
  void update_Data_Dn() async {
    //print("ambony");
    print("tyoooooooo");
    try {
      var resPdn = await http.get(Uri.parse(ProduitDnURL));
      var res = await http.get(Uri.parse(stockURL));
      print("ambony" + res.statusCode.toString());
      print("status" + resPdn.statusCode.toString());

      // final obj = jsonDecode(res.body);

      List<Dn> list = parseProducts(res.body);
      for (var i = 0; i < list.length; i++) {
        await insertDn(list[i]);
      }
      List<ProduitDn> listPdn = parseProduitDn(resPdn.body);
      for (var ind = 0; ind < listPdn.length; ind++) {
        await insertProduitDn(listPdn[ind]);
      }
      //int isany = await insertPlusieurDn(list);
      //print("isanle tafiditra"+);
      //print("aonaaa " + list.length.toString());
    } catch (e) {
      print(" tsy tafiditra");
      print(e);
    }
  }

  void ValiderResultatDn() async {
    bool servicestatus = await Geolocator.isLocationServiceEnabled();
    if (servicestatus) {
      print("GPS service is enabled");
    } else {
      print("GPS service is disabled.");
    }
    LocationPermission permission = await Geolocator.checkPermission();
    if (permission == LocationPermission.denied) {
      permission = await Geolocator.requestPermission();
      if (permission == LocationPermission.denied) {
        print('Location permissions are denied');
      } else if (permission == LocationPermission.deniedForever) {
        print("'Location permissions are permanently denied");
      } else {
        print("GPS Location service is granted");
      }
    } else {
      print("GPS Location permission granted.");
    }
    Position position = await Geolocator.getCurrentPosition(
        desiredAccuracy: LocationAccuracy.high);
    print(position.longitude); //Output: 80.24599079
    print(position.latitude);
  }
}
