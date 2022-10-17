class InfosResultatDn {
  final String id;

  final int idProduitdn;

  final int prix;

  final int rotation;

  final int idresultatdn;

  InfosResultatDn(
      {required this.id,
      required this.idProduitdn,
      required this.prix,
      required this.rotation,
      required this.idresultatdn});

  InfosResultatDn.fromMap(Map<String, dynamic> result)
      : id = result["id"],
        idProduitdn = result["idProduitdn"],
        prix = result["prix"],
        rotation = result["rotation"],
        idresultatdn = result["idresultatdn"];

  InfosResultatDn.fromJson(Map<String, dynamic> result)
      : id = result["id"],
        idProduitdn = result["idProduitdn"],
        prix = result["prix"],
        rotation = result["rotation"],
        idresultatdn = result["idresultatdn"];

  Map<String, Object> toMap() {
    return {
      'id': id,
      'idProduitdn': idProduitdn,
      'prix': prix,
      'rotation': rotation,
      'idresultatdn': idresultatdn
    };
  }
}
