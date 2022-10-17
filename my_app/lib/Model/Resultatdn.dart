class Resultatdn {
  final int id;
  final int idquartier;
  final int idenqueteur;

  Resultatdn(
      {required this.id, required this.idquartier, required this.idenqueteur});

  Resultatdn.fromJson(Map<String, dynamic> result)
      : id = result["id"],
        idquartier = result["idquartier"],
        idenqueteur = result["idenqueteur"];

  Map<String, Object> toMap() {
    return {
      'id': id,
      'idquartier': idquartier,
      'idenqueteur': idenqueteur,
    };
  }
}
