import 'package:my_app/Model/Database.dart';
import 'package:my_app/Model/Enqueteur.dart';
import 'package:sqflite/sqflite.dart';

class EnqueteService {
  DataBase data = new DataBase();
  Future<int> insertPlanets(List<Enqueteur> enqueteurs) async {
    int result = 0;
    final Database db = await data.initializedDB();
    for (var enqueteur in enqueteurs) {
      result = await db.insert('enqueteur', enqueteur.toMap(),
          conflictAlgorithm: ConflictAlgorithm.replace);
    }
    return result;
  }

  Future<int> insertEnquete(Enqueteur e) async {
    int result;
    final Database db = await data.initializedDB();
    result = await db.insert('enqueteur', e.toMap(),
        conflictAlgorithm: ConflictAlgorithm.replace);
    return result;
  }

  // retrieve data
  Future<List<Enqueteur>> retrievePlanets() async {
    final Database db = await data.initializedDB();
    final List<Map<String, Object?>> queryResult = await db.query('enqueteur');
    return queryResult.map((e) => Enqueteur.fromMap(e)).toList();
  }

  // delete user
  Future<void> deletePlanet(int id) async {
    final db = await data.initializedDB();
    await db.delete(
      'enqueteur',
      where: "id = ?",
      whereArgs: [id],
    );
  }
}
