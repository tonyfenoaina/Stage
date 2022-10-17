class Quartier {
  final int id;
  final String nom;
  final String etat;

  Quartier({required this.id, required this.nom, required this.etat});

  Quartier.fromMap(Map<String, dynamic> result)
      : id = result["id"],
        nom = result["nom"],
        etat = result["etat"];

  Quartier.fromJson(Map<String, dynamic> result)
      : id = result["id"],
        nom = result["nom"],
        etat = result["etat"];

  Map<String, Object> toMap() {
    return {'id': id, 'nom': nom, 'etat': etat};
  }
}
