import 'package:flutter/material.dart';
import 'package:my_app/Model/Dn.dart';
import 'package:my_app/Model/ProduitDn.dart';
import 'package:my_app/Service/DnService.dart';

class NavBar extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Drawer(
      backgroundColor: Color.fromARGB(255, 10, 89, 153),
      // Add a ListView to the drawer. This ensures the user can scroll
      // through the options in the drawer if there isn't enough vertical
      // space to fit everything.
      child: ListView(
        // Important: Remove any padding from the ListView.
        padding: EdgeInsets.fromLTRB(0, 10, 0, 10),
        children: [
          // Padding(padding: EdgeInsets.all(10)),
          const DrawerHeader(
            decoration: BoxDecoration(
              color: Color.fromARGB(255, 247, 249, 250),
            ),
            child: Text(
              "APP'IBO",
            ),
          ),
          ListTile(
            title: const Text('Deconnexion'),
            trailing: Icon(Icons.logout),
            tileColor: Color.fromARGB(139, 42, 138, 216),
            iconColor: Colors.white,
            textColor: Colors.white,
            onTap: () async {
              DnService dnService = new DnService();
              dnService.ValiderResultatDn();
              Navigator.of(context).pushNamed('/ficheDn');
              // Update the state of the app.
              // ...
            },
          ),
          ListTile(
            title: const Text('Mise a jours'),
            trailing: Icon(Icons.update),
            tileColor: Color.fromARGB(139, 42, 138, 216),
            iconColor: Colors.white,
            textColor: Colors.white,
            onTap: () async {
              // var dn = Dn(
              //     id: 1,
              //     titre: 'Lait en brique',
              //     debut: new DateTime.now().toString(),
              //     fin: new DateTime.now().toString(),
              //     idcategorie: 1,
              //     idunite: 2);
              try {
                Navigator.of(context).pushNamed('/home');
                DnService dnService = new DnService();
                // dnService.insertDn(dn);
                dnService.update_Data_Dn();
                //dnService.deleteDn(1);
                Future<List<Dn>> fliste = dnService.list();
                Future<List<ProduitDn>> FuturprList =
                    dnService.listpProduitDn();
                List<Dn> list = await fliste;
                List jsonlist = [];

                for (var i = 0; i < list.length; i++) {
                  jsonlist.add(list[i].toMap());
                }

                List<ProduitDn> prList = await FuturprList;
                print("isan'dn ao amzao" + list.length.toString());
                print("tyy" + jsonlist.toString());
                print("isan'Produitdn ao amzao" + prList.length.toString());
                //print("ty le date" + new DateTime.now().toString());
                print("poinsa" + list[1].debut.toString());
                print("poinsa" + list[0].id.toString());
              } catch (exception) {
                print("tsy tafiditra rse ah");
                print(exception.toString());
              }

              // Update the state of the app.
              // ...
            },
          ),
        ],
      ),
    );
  }
}
