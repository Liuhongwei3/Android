import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text(''),
        ),
        body: HomeContent(),
      ),
    );
  }
}

class HomeContent extends StatefulWidget {
  @override
  _HomeContentState createState() => _HomeContentState();
}

class _HomeContentState extends State<HomeContent> {
  int countNum = 0;

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        SizedBox(
          height: 200,
        ),
        Chip(
          label: Text('${this.countNum}'),
        ),
        RaisedButton(
          child: Text('click'),
          onPressed: () {
            setState(() {
              this.countNum++;
            });
          },
        )
      ],
    );
  }
}
