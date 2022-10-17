import 'package:flutter/material.dart';
import 'package:flutter_session_manager/flutter_session_manager.dart';
import 'package:my_app/Model/ProduitDn.dart';
import 'package:my_app/Service/DnService.dart';
import 'package:my_app/State/Fiche_Dn.dart';
import '../Model/Dn.dart';
import 'NavBar.dart';

///Liste des Dn disponoible
class Detail_Dn extends StatefulWidget {
  @override
  DetailDn_State createState() => new DetailDn_State();
}

class DetailDn_State extends State<Detail_Dn> {
  DnService dnservice = new DnService();
  List<ProduitDn> dnList = <ProduitDn>[];

  getAllDnDetails() async {
    int iddn = await SessionManager().get("iddn");
    var dns = await dnservice.ProduitDnparId(iddn);
    dnList = <ProduitDn>[];
    dns.forEach((produitdn) {
      setState(() {
        var userModel = ProduitDn(
            produitdn.id,
            produitdn.marque,
            produitdn.produit,
            produitdn.iddn,
            produitdn.idproduit,
            produitdn.idmarque);
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
      appBar: AppBar(
        title: Text("Liste des produits"),
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
      floatingActionButtonLocation: FloatingActionButtonLocation.miniEndFloat,
      floatingActionButton: FloatingActionButton(
          // isExtended: true,
          child: Icon(Icons.arrow_forward_ios),
          backgroundColor: Colors.green,
          onPressed: () async {
            await SessionManager().set("indexProduit", 0);
            int indexproduit = await SessionManager().get("indexProduit");
            int iddn = await SessionManager().get("iddn");
            DnService sserv = new DnService();
            Future<List<ProduitDn>> flistproduit = sserv.ProduitDnparId(iddn);
            List<ProduitDn> listeproduit = await flistproduit;
            //  await SessionManager().set("listproduit", flistproduit);
            Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => FicheDnWidget(
                    textaa: listeproduit[indexproduit].marque,
                    nomProduit: listeproduit[0].marque,
                  ),
                ));
            setState(() {});
          }),
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
                            MaterialPageRoute(
                              builder: (context) => FicheDnWidget(
                                textaa: listeproduit[indexproduit].marque,
                                nomProduit: listeproduit[0].marque,
                              ),
                            ));
                      },
                      title: Text(dnList[index].marque),
                      subtitle: Text('Liste des enquetes de DN courante.'),
                      textColor: Colors.white,
                      iconColor: Colors.white,
                      // subtitle: Text(
                      //     'Pour faire les enquetes de distribution numeriques'),
                      leading: Icon(Icons.fact_check),
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
