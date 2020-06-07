import 'package:flutter/material.dart';

class UserPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('User Center'),
      ),
      body: Center(
        child: Text('This is user page~'),
      ),
    );
  }
}
