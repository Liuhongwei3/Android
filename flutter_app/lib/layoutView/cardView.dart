import 'package:flutter/material.dart';
import 'package:flutter_app/res/listData.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Card View'),
        ),
        body: HomeContent(),
      ),
    );
  }
}

class HomeContent extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ListView(
      children: listData.map((value) {
        return Card(
          margin: EdgeInsets.all(10),
          child: Column(
            children: <Widget>[
              AspectRatio(
                aspectRatio: 20 / 9,
                child: Image.network(
                  value['imageUrl'],
                  fit: BoxFit.cover,
                ),
              ),
              ListTile(
                leading: CircleAvatar(
                  backgroundImage: NetworkImage(
                    value['imageUrl'],
                  ),
                ),
                title: Text(
                  value['title'],
                  style: TextStyle(fontSize: 24),
                ),
                subtitle: Text(
                  value['description'],
                  overflow: TextOverflow.ellipsis,
                ),
              ),
            ],
          ),
        );
      }).toList(),
//      }),
//      children: <Widget>[
//        Card(
//          margin: EdgeInsets.all(10),
//          child: Column(
//            children: <Widget>[
//              ListTile(
//                title: Text('Tadm',style: TextStyle(fontSize: 26),),
//                subtitle: Text('前端工程师'),
//              ),
//              ListTile(
//                title: Text('phone: 12344566'),
//              ),
//              ListTile(
//                title: Text('address: China'),
//              )
//            ],
//          ),
//        ),
//        Card(
//          margin: EdgeInsets.all(10),
//          child: Column(
//            children: <Widget>[
//              AspectRatio(
//                aspectRatio: 20/9,
//                child: Image.network(listData[1]['imageUrl'],fit: BoxFit.cover,),
//              ),
//              ListTile(
////                leading: ClipOval(
////                  child: Image.network(listData[0]['imageUrl'],fit: BoxFit.cover,width: 50,height: 50,),
////                ),
//                leading: CircleAvatar(
//                  backgroundImage: NetworkImage(listData[0]['imageUrl'],),
//                ),
//                title: Text('Tadm2',style: TextStyle(fontSize: 24),),
//                subtitle: Text('Student'),
//              ),
//            ],
//          ),
//        ),
//      ],
    );
  }
}
