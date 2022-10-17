import 'package:my_app/Model/Database.dart';
import 'package:my_app/Model/Emplacement.dart';
import 'package:sqflite/sqflite.dart';

class EmplacementService {
  DataBase data = new DataBase();
  Future<int> insertDn(Emplacement e) async {
    int result;
    final Database db = await data.initializedDB();
    result = await db.insert('emplacement', e.toMap(),
        conflictAlgorithm: ConflictAlgorithm.replace);
    return result;
  }
}
