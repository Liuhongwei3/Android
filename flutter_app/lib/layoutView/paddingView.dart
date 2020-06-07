import 'package:flutter/material.dart';
import 'package:flutter_app/res/listData.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('PaddingView'),
        ),
        body: HomeContent(),
      ),
    );
  }
}

class HomeContent extends StatelessWidget {
  List<Widget> _getListData() {
    var tempList = listData.map((value) {
      return Container(
        child: Column(
          children: <Widget>[
            Padding(
              padding: EdgeInsets.fromLTRB(10, 10, 0, 0),
              child: Image.network(value['imageUrl'], fit: BoxFit.cover),
            )
          ],
        ),
      );
    }).toList();
    return tempList;
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
        padding: EdgeInsets.fromLTRB(0, 0, 10, 0),
        child: GridView.count(
          crossAxisCount: 2,
          childAspectRatio: 1.3,
          children: _getListData(),
        ));
  }
}
