import 'package:flutter/material.dart';
import 'package:flutter_app/DartStudy.dart';

import 'package:flutter_swiper/flutter_swiper.dart';

void main() => runApp(new MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'Swiper Demo',
      theme: new ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: new MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => new _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  List<Map> _imageList = [
    {'url': 'https://www.itying.com/images/flutter/1.png'},
    {'url': 'https://www.itying.com/images/flutter/2.png'},
    {'url': 'https://www.itying.com/images/flutter/3.png'},
    {'url': 'https://www.itying.com/images/flutter/4.png'},
    {'url': 'https://www.itying.com/images/flutter/5.png'},
    {'url': 'https://www.itying.com/images/flutter/6.png'},
  ];

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text(widget.title),
      ),
      body: Column(
        children: <Widget>[
          Container(
            child: AspectRatio(
              aspectRatio: 16/9,
              child: Swiper(
                itemBuilder: (BuildContext context, int index) {
                  return new Image.network(
                    _imageList[index]['url'],
                    fit: BoxFit.fill,
                  );
                },
                itemCount: _imageList.length,
                pagination: new SwiperPagination(),
                control: new SwiperControl(),
                loop: true,
                autoplay: true,
              ),
            ),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text('This is a swiper text~')
            ],
          )
        ],
      )
    );
  }
}
