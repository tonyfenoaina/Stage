import 'package:flutter/material.dart';
import 'package:my_app/Model/Database.dart';
import 'package:my_app/Model/Enqueteur.dart';
import 'package:my_app/Model/ProduitDn.dart';
import 'package:my_app/Service/DnService.dart';
import 'package:my_app/Service/EnqueteurService.dart';
import 'package:my_app/State/Page2.dart';
import 'package:sqflite/sqflite.dart';

class MyStatefulWidget extends StatefulWidget {
  const MyStatefulWidget({Key? key}) : super(key: key);
  @override
  State<MyStatefulWidget> createState() => _MyStatefulWidgetState();
}

class _MyStatefulWidgetState extends State<MyStatefulWidget> {
  TextEditingController nameController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  static bool isObscure = true;

  void _toggle() {
    setState(() {
      isObscure = !isObscure;
    });
  }

  static const String _title = 'Authentification';
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          _title,
          style: TextStyle(fontSize: 20, fontStyle: FontStyle.normal),
        ),
        backgroundColor: Color.fromARGB(255, 8, 105, 185),
      ),
      body: Container(
          decoration: BoxDecoration(
            image: DecorationImage(
              image: AssetImage("assets/tb.jpg"),
              fit: BoxFit.cover,
              // colorFilter: new ColorFilter.mode(
              //     Colors.black.withOpacity(0.6), BlendMode.dstATop),
            ),
          ),
          padding: const EdgeInsets.all(10),
          child: ListView(
            children: <Widget>[
              Container(
                alignment: Alignment.center,
                padding: const EdgeInsets.all(10),
                child: ClipRRect(
                  borderRadius: BorderRadius.circular(50.0),
                  child: Image.asset('assets/logo1.png'),
                ),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(10),
                ),
              ),
              Container(
                  alignment: Alignment.center,
                  padding: const EdgeInsets.all(10),
                  child: const Text(
                    'Le meilleur pour vous',
                    style: TextStyle(
                        fontSize: 20,
                        fontStyle: FontStyle.italic,
                        color: Colors.black),
                  )),
              Container(
                constraints: BoxConstraints(
                    minHeight: 20, //minimum height
                    minWidth: 20 // minimum width
                    ),
                padding: const EdgeInsets.all(10),
                child: TextField(
                  style: TextStyle(color: Colors.black),
                  cursorColor: Colors.black,
                  controller: nameController,
                  decoration: const InputDecoration(
                    // fillColor: Color.fromARGB(64, 255, 255, 255),
                    // filled: false,
                    border: OutlineInputBorder(),
                    labelText: 'Matricule',
                  ),
                ),
              ),
              Container(
                padding: const EdgeInsets.all(10),
                child: TextField(
                  obscureText: isObscure,
                  style: TextStyle(color: Colors.black),
                  cursorColor: Colors.black,

                  // controller: passwordController,
                  decoration: const InputDecoration(
                    // fillColor: Color.fromARGB(64, 255, 255, 255),
                    // filled: true,
                    border: OutlineInputBorder(),
                    labelText: 'Mot de passe',
                  ),
                ),
              ),
              Container(
                  height: 50,
                  padding: const EdgeInsets.fromLTRB(10, 0, 10, 0),
                  child: ElevatedButton(
                    style: ElevatedButton.styleFrom(
                        primary: Color.fromARGB(255, 11, 110, 159)),
                    child: const Text('Se Connecter'),
                    onPressed: () async {
                      //Navigator.of(context).pushNamed('/home');

                      Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => Page2(),
                          ));
                      print(nameController.text);
                      print(passwordController.text);
                      var tony = Enqueteur(
                          id: 1,
                          nom: 'Tony',
                          prenom: 'Fenoaina',
                          matricule: 'stg-29',
                          motdepasse: 'root',
                          etat: 'active');

                      EnqueteService d = new EnqueteService();
                      DnService sserv = new DnService();
                      //var test = d.insertEnquete(tony);
                      Future<List<Enqueteur>> haha = d.retrievePlanets();
                      List<Enqueteur> liste = await haha;

                      Future<List<ProduitDn>> flistproduit =
                          sserv.ProduitDnparId(1);
                      List<ProduitDn> listeproduit = await flistproduit;

                      print("ty le produit isany" +
                          listeproduit.length.toString());

                      for (var i = 0; i < liste.length; i++) {
                        print("haha" + liste[i].nom);
                        print("length" + liste.length.toString());
                      }
                    },
                  )),
            ],
          )),
    );
  }
}

Route _createRoute() {
  return PageRouteBuilder(
    pageBuilder: (BuildContext context,
        Animation<double> animation, //
        Animation<double> secondaryAnimation) {
      return Page2();
    },
    transitionsBuilder: (BuildContext context,
        Animation<double> animation, //
        Animation<double> secondaryAnimation,
        Widget child) {
      return SlideTransition(
        position: new Tween<Offset>(
          begin: const Offset(-1.0, 0.0),
          end: Offset.zero,
        ).animate(animation),
        child: new SlideTransition(
          position: new Tween<Offset>(
            begin: Offset.zero,
            end: const Offset(-1.0, 0.0),
          ).animate(secondaryAnimation),
          child: child,
        ),
      );
    },
  );
}
