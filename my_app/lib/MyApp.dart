import 'package:http/http.dart';
import 'package:flutter/material.dart';
import 'package:my_app/State/Page2.dart';
import 'package:my_app/State/Page_Dn.dart';
import 'State/Fiche_Dn.dart';
import 'State/_MyStatefulWidgetState.dart';
import 'package:sqflite/sqflite.dart'; //sqflite package
import 'package:path_provider/path_provider.dart'; //path_provider package

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      routes: <String, WidgetBuilder>{
        '/home': (BuildContext context) => Page2(),
        '/login': (BuildContext context) => const MyStatefulWidget(),
        '/dn': (BuildContext context) => Page_Dn(),

        // '/details': (BuildContext context) => Details(),
        // '/about': (BuildContext context) => About(),
      },
      theme: ThemeData(
          inputDecorationTheme: const InputDecorationTheme(
        border: OutlineInputBorder(borderSide: BorderSide(color: Colors.black)),
        labelStyle: TextStyle(color: Colors.black),
        // hintStyle: TextStyle(color: Colors.black)
      )),
      //   routes: {
      //   '/': (context) => MyHomePage(title: "Connexion"),
      //   '/secondPage': (context) => MySecondPage(title: "Deuxième page"),
      // },
      home: const MyStatefulWidget(),
    );
  }
}
