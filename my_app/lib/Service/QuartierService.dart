import 'package:my_app/Model/Database.dart';
import 'package:my_app/Model/Quartier.dart';
import 'package:sqflite/sqflite.dart';

class QuartierService {
  DataBase data = new DataBase();

  Future<int> insertResultatDn(Quartier e) async {
    int result;
    final Database db = await data.initializedDB();
    result = await db.insert('quartier', e.toMap(),
        conflictAlgorithm: ConflictAlgorithm.replace);
    return result;
  }

  Future<List<Quartier>> QuartierDnparNom(var nom) async {
    final Database db = await data.initializedDB();
    // final List<Map<String, Object?>> queryResult = await db.query('dn');
    final List<Map<String, Object?>> queryResult =
        await db.rawQuery('SELECT * FROM quartier where nom=?', [nom]);
    // print("ty leiwyyyyyyyyyyyyyyyy" + date.toString());
    return queryResult.map((e) => Quartier.fromMap(e)).toList();
  }
}
