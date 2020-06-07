import 'package:flutter/material.dart';

class ToRouteReturnPage extends StatelessWidget {
  String string;

  ToRouteReturnPage(this.string);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('routeReturnPage'),
        ),
        floatingActionButton: FloatingActionButton(
          child: Text('back'),
          onPressed: () {
            Navigator.of(context).pop();
          },
        ),
        body: ListView(
          children: <Widget>[
            ListTile(
              title: Text("${this.string!=null ? this.string : 'null'}"),
            ),
            ListTile(
              title: Text('This is a form page1'),
            ),
            ListTile(
              title: Text('This is a form page2'),
            ),
            ListTile(
              title: Text('This is a form page3'),
            ),
          ],
        ));
  }
}
