import 'package:flutter/material.dart';

import '../res/listData.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('ListView'),
        ),
        body: HomeContent(),
      ),
    );
  }
}

//class HomeContent extends StatelessWidget {
//  @override
//  Widget build(BuildContext context) {
//    return ListView(
//      children: <Widget>[
//        ListTile(
//          title: Text('this is a text'),
//        ),
//        ListTile(
//          title: Text('this is a text'),
//        ),
//        ListTile(
//          title: Text('this is a text'),
//        )
//      ],
//    );
//  }
//}

class HomeContent extends StatelessWidget {
  //  ListView.builder
  Widget _getListData(context, index) {
    return ListTile(
      leading: Image.network(listData[index]['imageUrl']),
      title: Text(listData[index]['title']),
      subtitle: Text(listData[index]['author']),
    );
  }

  List<Widget> _getData() {
    List<Widget> list = new List();
    list = listData.map((value) {
      return ListTile(
        leading: Image.network(value['imageUrl']),
        title: Text(value['title']),
        subtitle: Text(value['author']),
      );
    }).toList();

//    for (int i = 0; i < 16; i++) {
//      list.add(ListTile(
//        title: Text('this is a $i text'),
//      ));
//    }

//    return[
//      ListTile(
//        title: Text('this is a text'),
//      ),
//      ListTile(
//        title: Text('this is a text'),
//      ),
//      ListTile(
//        title: Text('this is a text'),
//      )
//    ];

    return list;
  }

  @override
  Widget build(BuildContext context) {
//    return ListView(
//      children: <Widget>[
//        ListTile(
//          title: Text('this is a text'),
//        ),
//        ListTile(
//          title: Text('this is a text'),
//        ),
//        ListTile(
//          title: Text('this is a text'),
//        )
//      ],
//    );

//    return ListView(
//      children: this._getData(),
//    );

    //  use ListView.builder
    return ListView.builder(
        itemCount: listData.length, itemBuilder: _getListData);
  }
}
