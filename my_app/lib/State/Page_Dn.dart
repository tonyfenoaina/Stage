import 'package:flutter/material.dart';
import 'package:flutter_session_manager/flutter_session_manager.dart';
import 'package:my_app/Model/ProduitDn.dart';
import 'package:my_app/Service/DnService.dart';
import 'package:my_app/State/DetailDn.dart';
import 'package:my_app/State/Fiche_Dn.dart';
import '../Model/Dn.dart';
import 'NavBar.dart';

///Liste des Dn disponoible
class Page_Dn extends StatefulWidget {
  @override
  Page_Dn_State createState() => new Page_Dn_State();
}

class Page_Dn_State extends State<Page_Dn> {
  DnService dnservice = new DnService();
  List<Dn> dnList = <Dn>[];

  getAllDnDetails() async {
    var dns = await dnservice.list();
    dnList = <Dn>[];
    dns.forEach((dn) {
      setState(() {
        var userModel = Dn(
            id: dn.id,
            titre: dn.titre,
            debut: dn.debut,
            fin: dn.fin,
            idcategorie: dn.idcategorie,
            idunite: dn.idunite);
        dnList.add(userModel);
      });
    });
  }

  // @override
  // void initState() async {
  //   print("tafiditra");
  //   super.initState();
  //   final data = await dnservice.list();
  //   setState(() {
  //     var dnModel = Dn(titre: );
  //     //_isLoading = false;
  //   });
  // }

  @override
  void initState() {
    getAllDnDetails();
    super.initState();
  }

  @override
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
        child: ListView.builder(
            padding: EdgeInsets.all(10),
            itemExtent: 90,
            itemCount: dnList.length,
            itemBuilder: (ctx, index) {
              print("ty " + dnList.length.toString());
              return Card(
                child: Container(
                    decoration: BoxDecoration(
                      image: DecorationImage(
                        image: AssetImage("assets/ts.jpg"),
                        fit: BoxFit.fitWidth,
                        alignment: Alignment.topCenter,
                      ),
                    ),
                    child: ListTile(
                      onTap: () async {
                        await SessionManager().set("iddn", dnList[index].id);
                        await SessionManager().set("indexProduit", 0);

                        int indexproduit =
                            await SessionManager().get("indexProduit");

                        int iddn = await SessionManager().get("iddn");
                        DnService sserv = new DnService();
                        Future<List<ProduitDn>> flistproduit =
                            sserv.ProduitDnparId(iddn);
                        List<ProduitDn> listeproduit = await flistproduit;
                        //  await SessionManager().set("listproduit", flistproduit);
                        Navigator.push(
                            context,
                            MaterialPageRoute(builder: (context) => Detail_Dn()
                                // Fiche_dn(
                                //   textaa: listeproduit[indexproduit].marque,
                                //   nomProduit: listeproduit[0].marque,
                                // ),
                                ));
                      },
                      title: Text(dnList[index].titre),
                      subtitle: Text('Liste des enquetes de DN courante.'),
                      textColor: Colors.white,
                      iconColor: Colors.white,
                      // subtitle: Text(
                      //     'Pour faire les enquetes de distribution numeriques'),
                      leading: Icon(Icons.fact_check),

                      trailing: Icon(Icons.arrow_forward_ios),
                    )),
              );

              //  ListTile(
              //   title: Text(dnList[index].titre),
              //   leading: Text(dnList[index].id.toString()),
              //   textColor: Colors.white,
              //   onTap: () {},
              //   tileColor: Colors.black,

              //   // title: Text("data"),
              // );
            }),
      ),
    );
  }
}
