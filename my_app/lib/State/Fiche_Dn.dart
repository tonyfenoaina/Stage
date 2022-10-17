import 'package:flutter/material.dart';
import 'package:flutter_session_manager/flutter_session_manager.dart';
import 'package:my_app/Model/ProduitDn.dart';
import 'package:my_app/Service/DnService.dart';
import 'package:my_app/State/Page_Dn.dart';

class FicheDnWidget extends StatefulWidget {
  // const FicheDnWidget({super.key});
  String textaa = "";
  String nomProduit;
  FicheDnWidget({Key? key, required this.textaa, required this.nomProduit})
      : super(key: key);

  @override
  State<FicheDnWidget> createState() =>
      Fiche_dn(nomProduit: nomProduit, textaa: textaa);
}

class Fiche_dn extends State<FicheDnWidget> {
  String textaa = "";
  String nomProduit = "";
  Fiche_dn({Key? key, required this.textaa, required this.nomProduit});
  //ProduitDn pdn;
  // Fiche_dn({Key? key, required this.textaa, required this.nomProduit})
  //     : super(key: key);
  @override
  bool isChecked = false;
  bool _enabled = false;
  TextEditingController nameController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  Widget build(BuildContext context) {
    Color getColor(Set<MaterialState> states) {
      const Set<MaterialState> interactiveStates = <MaterialState>{
        MaterialState.pressed,
        MaterialState.hovered,
        MaterialState.focused,
      };
      if (states.any(interactiveStates.contains)) {
        return Colors.grey;
      }
      return Colors.blue;
    }

    return Scaffold(
      appBar: AppBar(
        title: const Text(
          "_title",
          style: TextStyle(fontSize: 20, fontStyle: FontStyle.normal),
        ),
        backgroundColor: Color.fromARGB(255, 8, 105, 185),
      ),
      body: Container(
          decoration: BoxDecoration(
            image: DecorationImage(
              image: AssetImage("assets/tb.jpg"),
              fit: BoxFit.fitWidth,
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
                  child: Text(
                    textaa,
                    style: TextStyle(fontSize: 20, color: Colors.black),
                  )),
              Container(
                alignment: Alignment.center,
                padding: const EdgeInsets.fromLTRB(60, 10, 50, 0),
                child: Row(
                  children: [
                    Container(
                      padding: EdgeInsets.fromLTRB(10, 0, 10, 0),
                      child: Text(
                        "Vente",
                        style: TextStyle(fontSize: 20, color: Colors.black),
                      ),
                    ),
                    Checkbox(
                      checkColor: Colors.black,
                      fillColor: MaterialStateProperty.resolveWith(getColor),
                      value: isChecked,
                      onChanged: (bool? value) {
                        setState(() {
                          isChecked = value!;
                          _enabled = !_enabled;
                        });
                      },
                    )
                  ],
                ),
              ),
              Container(
                // constraints: BoxConstraints(
                //     minHeight: 10, //minimum height
                //     minWidth: 10 // minimum width
                //     ),
                padding: const EdgeInsets.only(
                    left: 50, right: 50, bottom: 10, top: 10),
                child: TextField(
                  enabled: _enabled,
                  style: TextStyle(color: Colors.black),
                  cursorColor: Colors.black,
                  controller: nameController,
                  keyboardType: TextInputType.number,
                  decoration: const InputDecoration(
                      // fillColor: Color.fromARGB(64, 255, 255, 255),
                      // filled: false,
                      contentPadding: const EdgeInsets.symmetric(
                          vertical: 20.0, horizontal: 20.0),
                      border: UnderlineInputBorder(
                          borderSide: BorderSide(color: Colors.black)),
                      labelText: 'Prix',
                      labelStyle: TextStyle(color: Colors.black)),
                ),
              ),
              Container(
                padding: const EdgeInsets.only(
                    left: 50, right: 50, bottom: 10, top: 10),
                child: TextField(
                  enabled: _enabled,
                  // obscureText: isObscure,
                  style: TextStyle(color: Colors.black),
                  cursorColor: Colors.black,
                  keyboardType: TextInputType.number,

                  // controller: passwordController,
                  decoration: const InputDecoration(
                      contentPadding: const EdgeInsets.symmetric(
                          vertical: 20.0, horizontal: 20.0),
                      focusColor: Colors.black,
                      prefixIconColor: Colors.black,
                      // fillColor: Color.fromARGB(64, 255, 255, 255),
                      // filled: true,
                      border: UnderlineInputBorder(
                        borderSide: BorderSide(color: Colors.black),
                      ),
                      labelText: 'Rotation',
                      labelStyle: TextStyle(color: Colors.black),
                      fillColor: Colors.black),
                ),
              ),
              Container(
                  height: 50,
                  padding: const EdgeInsets.fromLTRB(50, 0, 50, 0),
                  child: ElevatedButton(
                    style: ElevatedButton.styleFrom(
                        primary: Color.fromARGB(255, 11, 110, 159)),
                    child: const Text('Suivant'),
                    onPressed: () async {
                      int indexproduit =
                          await SessionManager().get("indexProduit");
                      indexproduit = indexproduit + 1;
                      await SessionManager().set("indexProduit", indexproduit);
                      int iddn = await SessionManager().get("iddn");
                      DnService sserv = new DnService();
                      Future<List<ProduitDn>> flistproduit =
                          sserv.ProduitDnparId(iddn);
                      List<ProduitDn> listeproduit = await flistproduit;

                      if (listeproduit.length > indexproduit) {
                        Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => FicheDnWidget(
                                textaa:
                                    listeproduit[indexproduit].id.toString(),
                                nomProduit: listeproduit[0].marque,
                              ),
                            ));
                      } else {
                        Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => Page_Dn(),
                            ));
                      }

                      print(nameController.text);
                      print(passwordController.text);
                    },
                  )),
            ],
          )),
    );
  }
}
