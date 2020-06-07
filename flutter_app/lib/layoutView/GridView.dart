import 'package:flutter/material.dart';
import 'package:flutter_app/res/listData.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('GridView'),
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
            Image.network(value['imageUrl']),
            SizedBox(height: 12),
            Text(
              value['title'],
              textAlign: TextAlign.center,
              style: TextStyle(fontSize: 20),
            ),
          ],
        ),
        decoration: BoxDecoration(
            border: Border.all(
                color: Color.fromRGBO(233, 233, 233, 0.9), width: 1)),
      );
    }).toList();
    return tempList;
  }

  @override
  Widget build(BuildContext context) {
    return GridView.count(
      crossAxisCount: 3,
      crossAxisSpacing: 10.0,
      //  水平子 widget 间距
      mainAxisSpacing: 10.0,
      //  垂直子 widget 间距
      padding: EdgeInsets.all(10),
      childAspectRatio: 0.8,
      //  宽高比例

      children: this._getListData(),
    );
  }
}
