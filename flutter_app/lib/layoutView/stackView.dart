import 'package:flutter/material.dart';
import 'package:flutter_app/res/listData.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('StackView'),
        ),
        body: HomeContent(),
      ),
    );
  }
}

class HomeContent extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Container(
        width: 400,
        height: 300,
        color: Colors.blueGrey,
        child: Stack(
          children: <Widget>[
            Align(
              alignment: Alignment.topLeft,
              child: Icon(
                Icons.home,
                size: 40,
                color: Colors.cyan,
              ),
            ),
            Positioned(
              left: 0,
              bottom: 0,
              child: Icon(
                Icons.map,
                size: 50,
                color: Colors.pinkAccent,
              ),
            ),
            Positioned(
                top: 0,
                right: 50,
                child: Container(
                    child: Image.network(
                      listData[0]['imageUrl'],
                      width: 200,
                      height: 200,
                    ),
                ),
            ),
            Positioned(
              right: 50,
              top: 200,
              child: Text('This is a text~',style: TextStyle(fontSize: 30,color: Colors.purpleAccent),),
            )
          ],
        ),
      ),
    );

//    Stack(
//      alignment: Alignment.center,
////      alignment: Alignment(0,0),
//      children: <Widget>[
//        Container(
//          width: 400,
//          height: 300,
//          color: Colors.blueAccent,
//        ),
//        Text(
//          'this is a text~',
//          style: TextStyle(fontSize: 30, color: Colors.white),
//        ),
//      ],
//    );
  }
}
