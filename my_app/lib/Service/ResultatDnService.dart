import 'package:my_app/Model/Database.dart';
import 'package:my_app/Model/InfosResultatDn.dart';
import 'package:sqflite/sqflite.dart';

class ResultatDnService {
  DataBase data = new DataBase();

  Future<int> insertInfosResultatDn(InfosResultatDn e) async {
    int result;
    final Database db = await data.initializedDB();
    result = await db.insert('infosresultatdn', e.toMap(),
        conflictAlgorithm: ConflictAlgorithm.replace);
    return result;
  }

  Future<int> insertResultatDn(InfosResultatDn e) async {
    int result;
    final Database db = await data.initializedDB();
    result = await db.insert('resultatdn', e.toMap(),
        conflictAlgorithm: ConflictAlgorithm.replace);
    return result;
  }
}
