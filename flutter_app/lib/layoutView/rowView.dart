import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('RowView'),
        ),
        body: HomeContent(),
      ),
    );
  }
}

class HomeContent extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
//    return Row(
////    return Column(
//      mainAxisAlignment: MainAxisAlignment.spaceAround,
////      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
////      mainAxisAlignment: MainAxisAlignment.spaceBetween,
//      children: <Widget>[
//        IconContainer(Icons.search,color: Colors.blue,),
//        IconContainer(Icons.home,color: Colors.orangeAccent,),
//        IconContainer(Icons.security,color: Colors.redAccent,),
//
//        //  Expanded 类似于 flex 布局
////        Expanded(
////          flex: 1,
////          child: IconContainer(Icons.search,color: Colors.blue,),
////        ),
////        Expanded(
////          flex: 2,
////          child: IconContainer(Icons.home,color: Colors.orangeAccent,),
////        ),
////        Expanded(
////          flex: 1,
////          child: IconContainer(Icons.security,color: Colors.redAccent,),
////        )
//      ],
//    );

  //  Usage row/column/expanded
    return Column(
      children: <Widget>[
        Row(
          children: <Widget>[
            Expanded(
              child: Container(
                height: 180,
                color: Colors.black,
                padding: EdgeInsets.all(50.0),
                child: Text(
                  'Hello Flutter~',
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    color: Colors.white,
                    fontSize: 30.0,
                  ),
                ),
              ),
            )
          ],
        ),
        SizedBox(height: 10),
        Row(
          children: <Widget>[
            Expanded(
                flex: 2,
                child: Container(
                  height: 180,
                  child: Image.network(
                      "https://www.itying.com/images/flutter/2.png",
                      fit: BoxFit.cover),
                )),
            SizedBox(width: 10),
            Expanded(
                flex: 1,
                child: Container(
                    height: 180,
                    child: ListView(
                      children: <Widget>[
                        Container(
                          height: 85,
                          child: Image.network(
                              "https://www.itying.com/images/flutter/3.png",
                              fit: BoxFit.cover),
                        ),
                        SizedBox(height: 10),
                        Container(
                          height: 85,
                          child: Image.network(
                              "https://www.itying.com/images/flutter/4.png",
                              fit: BoxFit.cover),
                        )
                      ],
                    ))),
          ],
        )
      ],
    );
  }
}

class IconContainer extends StatelessWidget {
  double size = 32.0;
  Color color = Colors.orangeAccent;
  IconData iconData;

  IconContainer(this.iconData, {this.size, this.color});

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 100.0,
      height: 100.0,
      color: this.color,
//      decoration: BoxDecoration(
//        borderRadius: BorderRadius.circular(5.0),
//      ),
      child: Center(
        child: Icon(this.iconData, size: this.size, color: Colors.white),
      ),
    );
  }
}
