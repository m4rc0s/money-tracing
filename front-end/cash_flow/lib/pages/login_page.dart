import 'package:flutter/material.dart';

class LoginPage extends StatelessWidget {
  const LoginPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Row(
          children: [
            TextFormField(
                textAlign: TextAlign.left,
                decoration: const InputDecoration(labelText: "Username")),
            const Padding(padding: EdgeInsets.all(4.0)),
            TextFormField(
              textAlign: TextAlign.left,
              decoration: const InputDecoration(
                  labelText: "Password", hintText: "Type your password..."),
            ),
            const Padding(padding: EdgeInsets.all(4.0)),
            const ElevatedButton(onPressed: null, child: Text("Login"))
          ],
        )
      ],
    );
  }
}
