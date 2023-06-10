import 'dart:html';

import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.green,
      ),
      home: const Home(),
    );
  }
}

class Home extends StatelessWidget {
  const Home({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Container(
          width: 520,
          height: 440,
          decoration: const BoxDecoration(
              borderRadius: BorderRadius.all(Radius.circular(8))),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            children: [
              Expanded(
                  flex: 3,
                  child: Container(
                      alignment: Alignment.centerLeft,
                      padding: const EdgeInsets.all(16.0),
                      decoration: const BoxDecoration(
                          color: Colors.green,
                          borderRadius: BorderRadius.only(
                              topLeft: Radius.circular(8.0),
                              topRight: Radius.circular(8.0),
                              bottomLeft: Radius.zero,
                              bottomRight: Radius.zero)),
                      child: const Text(
                        "Auth",
                        style: TextStyle(fontSize: 22, color: Colors.white),
                      ))),
              Expanded(
                  flex: 10,
                  child: Container(
                    decoration: BoxDecoration(
                        borderRadius: const BorderRadius.only(
                            bottomLeft: Radius.circular(8.0),
                            bottomRight: Radius.circular(8.0),
                            topLeft: Radius.zero,
                            topRight: Radius.zero),
                        border: Border.all(width: 1, color: Colors.green)),
                    child: Form(
                      child: Column(
                          mainAxisAlignment: MainAxisAlignment.start,
                          children: <Widget>[
                            const Spacer(flex: 2),
                            Padding(
                                padding: const EdgeInsets.all(8),
                                child: TextFormField(
                                    autovalidateMode: AutovalidateMode.always,
                                    validator: (val) {
                                      if (val == null) {
                                        return "Informe o usuário";
                                      }
                                      return null;
                                    },
                                    decoration: InputDecoration(
                                        filled: true,
                                        border: OutlineInputBorder(
                                            borderSide: BorderSide.none,
                                            borderRadius:
                                                BorderRadius.circular(2)),
                                        hintText: "Type username...",
                                        labelText: "Username"))),
                            const Spacer(flex: 1),
                            Padding(
                              padding: const EdgeInsets.all(8.0),
                              child: TextFormField(
                                obscureText: true,
                                textAlign: TextAlign.left,
                                decoration: InputDecoration(
                                    filled: true,
                                    border: OutlineInputBorder(
                                        borderSide: BorderSide.none,
                                        borderRadius: BorderRadius.circular(2)),
                                    labelText: "Password",
                                    hintText: "Type your password..."),
                              ),
                            ),
                            const Spacer(
                              flex: 3,
                            ),
                            SizedBox(
                                width: 120,
                                height: 45,
                                child: ElevatedButton(
                                  onPressed: () {},
                                  child: const Text("L O G I N"),
                                )),
                            const Spacer(
                              flex: 2,
                            )
                          ]),
                    ),
                  ))
            ],
          ),
        ),
      ),
    );
  }
}
