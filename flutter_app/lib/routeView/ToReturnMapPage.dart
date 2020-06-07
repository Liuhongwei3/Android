import 'package:flutter/material.dart';

import '../RouteView.dart';

class ToRouteReturnMapPage extends StatefulWidget {
  Map arguments;

  ToRouteReturnMapPage({this.arguments});

  @override
  _ToRouteReturnMapPageState createState() =>
      _ToRouteReturnMapPageState(arguments: this.arguments);
}

class _ToRouteReturnMapPageState extends State<ToRouteReturnMapPage> {
  Map arguments;

  _ToRouteReturnMapPageState({this.arguments});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('named route return map value'),
      ),
      body: Container(
        child: Column(
          children: <Widget>[
            Text(
              "${arguments['id']}---${arguments['name']}",
              style: TextStyle(fontSize: 26, color: Colors.pink),
            ),
            RaisedButton(
              child: Text('backToRoot'),
              onPressed: () {
                Navigator.of(context).pushAndRemoveUntil(
                    new MaterialPageRoute(builder: (context) => RouteView()),
                        (route) => route == null);
              },
            ),
          ],
        ),
      ),
    );
  }
}
