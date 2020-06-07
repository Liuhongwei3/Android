import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Wrap View'),
        ),
        body: HomeContent(),
      ),
    );
  }
}

class HomeContent extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Wrap(
//      direction: Axis.horizontal,
      spacing: 10.0,
      runSpacing: 5.0,
//      alignment: WrapAlignment.start,
//      runAlignment: WrapAlignment.start,
      children: <Widget>[
        MyButton('Click'),
        MyButton('Tadm'),
        MyButton('LiuHongwei'),
        MyButton('Click'),
        MyButton('Tadm'),
        MyButton('LiuHongwei'),
        MyButton('Click'),
        MyButton('Tadm'),
        MyButton('LiuHongwei'),
        MyButton('Click'),
        MyButton('Tadm'),
        MyButton('LiuHongwei'),
      ],
    );
  }
}

class MyButton extends StatelessWidget {
  final String text;

  const MyButton(this.text, {Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return RaisedButton(
      child: Text(this.text),
      textColor: Theme.of(context).accentColor,
      onPressed: () {},
    );
  }
}
