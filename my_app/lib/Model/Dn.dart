class Dn {
  final int id;
  final String titre;
  final String debut;
  final String fin;
  final int idcategorie;
  final int idunite;

  Dn(
      {required this.id,
      required this.titre,
      required this.debut,
      required this.fin,
      required this.idcategorie,
      required this.idunite});

  Dn.fromMap(Map<String, dynamic> result)
      : id = result["id"],
        titre = result["titre"],
        debut = result["debut"],
        fin = result["fin"],
        idcategorie = result["idcategorie"],
        idunite = result["idunite"];

  Dn.fromJson(Map<String, dynamic> result)
      : id = result["id"],
        titre = result["titre"],
        debut = result["debut"],
        fin = result["fin"],
        idcategorie = result["idcategorie"],
        idunite = result["idunite"];

  Map<String, Object> toMap() {
    return {
      'id': id,
      'titre': titre,
      'debut': debut,
      'fin': fin,
      'idcategorie': idcategorie,
      'idunite': idunite
    };
  }
}
