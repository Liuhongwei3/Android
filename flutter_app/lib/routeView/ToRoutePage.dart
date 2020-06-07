import 'package:flutter/material.dart';

import '../RouteView.dart';

class ToRoutePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('routePage'),
          backgroundColor: Colors.orangeAccent,
          //  为子级页面时会替换掉默认的返回按钮
          leading: IconButton(
            icon: Icon(Icons.menu),
            onPressed: (){
              print('menu clicked~');
            },
          ),
          centerTitle: false,
          actions: <Widget>[
            IconButton(
              icon: Icon(Icons.search),
              onPressed: (){
                print('search clicked~');
              },
            ),
            IconButton(
              icon: Icon(Icons.settings),
              onPressed: (){
                print('settings clicked~');
              },
            )
          ],
        ),
        floatingActionButton: FloatingActionButton(
          child: Text('back'),
          onPressed: () {
            Navigator.of(context).pop();
          },
        ),
        body: Center(
            child: Column(
          children: <Widget>[
            Text('this is a route page~'),
            RaisedButton(
              child: Text('replaceRoute'),
              onPressed: () {
//                Navigator.pushNamed(context, '/routeReturnMap',
//                    arguments: {'id': 123, 'name': 'tadm'});
                Navigator.of(context).pushReplacementNamed('/routeReturnMap',
                    arguments: {'id': 123, 'name': 'tadm'});
              },
            ),
            RaisedButton(
              child: Text('backToRoot'),
              onPressed: () {
                Navigator.of(context).pushAndRemoveUntil(
                    new MaterialPageRoute(
                        builder: (context) => RouteView()),
                    (route) => route == null);
              },
            )
          ],
        )));
  }
}
