class Emplacement {
  final String id;
  final int iddn;
  final int idEnqueteur;
  final double longitude;
  final double latitude;
  final int resultatdn;

  Emplacement(
      {required this.id,
      required this.iddn,
      required this.idEnqueteur,
      required this.longitude,
      required this.latitude,
      required this.resultatdn});

  Emplacement.fromMap(Map<String, dynamic> result)
      : id = result["id"],
        iddn = result["iddn"],
        idEnqueteur = result["idEnqueteur"],
        longitude = result["longitude"],
        latitude = result["latitude"],
        resultatdn = result["resultatdn"];

  Emplacement.fromJson(Map<String, dynamic> result)
      : id = result["id"],
        iddn = result["iddn"],
        idEnqueteur = result["idEnqueteur"],
        longitude = result["longitude"],
        latitude = result["latitude"],
        resultatdn = result["resultatdn"];

  Map<String, Object> toMap() {
    return {
      'id': id,
      'iddn': iddn,
      'idEnqueteur': idEnqueteur,
      'longitude': longitude,
      'latitude': latitude,
      'resultatdn': resultatdn
    };
  }
}
