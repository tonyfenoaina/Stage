class Enqueteur {
  final int id;
  final String nom;
  final String prenom;
  final String matricule;
  final String motdepasse;
  final String etat;

  Enqueteur(
      {required this.id,
      required this.nom,
      required this.prenom,
      required this.matricule,
      required this.motdepasse,
      required this.etat});

  Enqueteur.fromMap(Map<String, dynamic> result)
      : id = result["id"],
        nom = result["nom"],
        prenom = result["prenom"],
        matricule = result["matricule"],
        motdepasse = result["motdepasse"],
        etat = result["etat"];

  Map<String, Object> toMap() {
    return {
      'id': id,
      'nom': nom,
      'prenom': prenom,
      'matricule': matricule,
      'motdepasse': motdepasse,
      'etat': etat
    };
  }
}
