class ProduitDn {
  final int id;

  final String marque;

  final String produit;

  final int iddn;

  final int idproduit;

  final int idmarque;

  ProduitDn(this.id, this.marque, this.produit, this.iddn, this.idproduit,
      this.idmarque);

  ProduitDn.fromMap(Map<String, dynamic> result)
      : id = result["id"],
        marque = result["marque"],
        produit = result["produit"],
        iddn = result["iddn"],
        idproduit = result["idproduit"],
        idmarque = result["idmarque"];

  ProduitDn.fromJson(Map<String, dynamic> result)
      : id = result["id"],
        marque = result["marque"],
        produit = result["produit"],
        iddn = result["iddn"],
        idproduit = result["idproduit"],
        idmarque = result["idmarque"];

  Map<String, Object> toMap() {
    return {
      'id': id,
      'marque': marque,
      'produit': produit,
      'iddn': iddn,
      'idproduit': idproduit,
      'idmarque': idmarque
    };
  }
}
