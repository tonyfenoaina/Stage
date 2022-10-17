import 'package:flutter/material.dart';
import 'package:my_app/Service/DnService.dart';
import 'package:my_app/State/Fiche_Dn.dart';

import '../Model/Dn.dart';
import 'NavBar.dart';

class Page2 extends StatelessWidget {
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: NavBar(),
      appBar: AppBar(
        title: Text("App'IBO"),
        // leading: IconButton(
        //   //menu icon button at start left of appbar
        //   onPressed: () {
        //     new NavBar();
        //     Drawer();
        //     print("haha");
        //     //code to execute when this button is pressed
        //   },
        //   icon: Icon(Icons.menu),
        // ),
        backgroundColor: Color.fromARGB(255, 8, 105, 185),
      ),
      body: Container(
        decoration: BoxDecoration(
          color: Color.fromARGB(0, 158, 158, 158),
          image: DecorationImage(
            image: AssetImage("assets/fond.png"),
            colorFilter: new ColorFilter.mode(
                Colors.black.withOpacity(0.9), BlendMode.dstATop),
            fit: BoxFit.cover,
          ),
        ),
        child: ListView(
          children: <Widget>[
            Container(
              margin: EdgeInsets.only(top: 10),
              alignment: Alignment.center,
              padding: const EdgeInsets.all(10),
              child: ClipRRect(
                borderRadius: BorderRadius.circular(10000.0),
                child: Image.asset('assets/logo1.png'),
              ),
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(10),
              ),
            ),
            GestureDetector(
              onTap: () {
                // Navigator.push(
                //     context,
                //     MaterialPageRoute(
                //       builder: (context) => Fiche_dn(
                //         textaa: 'Hello',
                //       ),
                //     ));

                Navigator.of(context).pushNamed('/dn');
              },
              child: Card(
                child: Container(
                    decoration: BoxDecoration(
                      image: DecorationImage(
                        image: AssetImage("assets/ts.jpg"),
                        fit: BoxFit.fitWidth,
                        alignment: Alignment.topCenter,
                      ),
                    ),
                    child: ListTile(
                      title: Text("DN"),
                      subtitle: Text('Liste des enquetes de DN courante.'),
                      textColor: Colors.white,
                      iconColor: Colors.white,
                      // subtitle: Text(
                      //     'Pour faire les enquetes de distribution numeriques'),
                      leading: Icon(Icons.fact_check),
                      trailing: Icon(Icons.arrow_forward_ios),
                    )),
              ),
            ),
            GestureDetector(
              onTap: () {
                Navigator.of(context).pushNamed('/login');
              },
              child: Card(
                child: Container(
                    decoration: BoxDecoration(
                      image: DecorationImage(
                        image: AssetImage("assets/ts.jpg"),
                        fit: BoxFit.fitWidth,
                        alignment: Alignment.topCenter,
                      ),
                    ),
                    child: ListTile(
                      title: Text("Degustation"),
                      textColor: Colors.white,
                      iconColor: Colors.white,
                      subtitle: Text('Liste des degustation courants.'),
                      leading: Icon(Icons.emoji_emotions),
                      trailing: Icon(Icons.arrow_forward_ios),
                    )),
              ),
            ),
            GestureDetector(
              onTap: () {
                Navigator.of(context).pushNamed('/login');
              },
              child: Card(
                child: Container(
                    decoration: BoxDecoration(
                      image: DecorationImage(
                        image: AssetImage("assets/ts.jpg"),
                        fit: BoxFit.fitWidth,
                        alignment: Alignment.topCenter,
                      ),
                    ),
                    child: ListTile(
                      title: Text("Parametre"),
                      textColor: Colors.white,
                      iconColor: Colors.white,
                      subtitle: Text('Mise a jours / Transfert de donnees '),
                      leading: Icon(Icons.settings),
                    )),
              ),
            ),
            GestureDetector(
              onTap: () {
                Navigator.of(context).pushNamed('/login');
              },
              child: Card(
                child: Container(
                    decoration: BoxDecoration(
                      image: DecorationImage(
                        image: AssetImage("assets/ts.jpg"),
                        fit: BoxFit.fitWidth,
                        alignment: Alignment.topCenter,
                      ),
                    ),
                    child: ListTile(
                      title: Text("A Propos"),
                      textColor: Colors.white,
                      iconColor: Colors.white,
                      subtitle: Text("A Propos de l'App'Ibo"),
                      leading: Icon(Icons.info),
                    )),
              ),
            ),
          ],
          padding: EdgeInsets.all(10),
          itemExtent: 90,
        ),
      ),
    );
  }
}
