import 'package:flutter/material.dart';

void main() =>runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('ScrollController View'),
        ),
        body: HomeContent(),
      ),
    );
  }
}

class HomeContent extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MyHomePage();
  }
}

class MyHomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => MyHomePageState();
}

class MyHomePageState extends State<MyHomePage> {
  ScrollController _controller;
  bool _isShowTop = false;

  @override
  void initState() {
    // 初始化ScrollController
    _controller = ScrollController();

    // 监听滚动
    _controller.addListener(() {
      var tempSsShowTop = _controller.offset >= 600;
      if (tempSsShowTop != _isShowTop) {
        setState(() {
          _isShowTop = tempSsShowTop;
        });
      }
    });

    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView.builder(
          itemCount: 50,
          itemExtent: 60,
          controller: _controller,
          itemBuilder: (BuildContext context, int index) {
            return ListTile(title: Text("item$index"));
          }
      ),
      floatingActionButton: !_isShowTop ? null : FloatingActionButton(
        child: Icon(Icons.arrow_upward),
        onPressed: () {
          _controller.animateTo(0, duration: Duration(milliseconds: 1000), curve: Curves.ease);
        },
      ),
    );
  }
}
