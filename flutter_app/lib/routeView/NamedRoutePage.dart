import 'package:flutter/material.dart';

//  named route
class HomeContentJump extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        RaisedButton(
          child: Text('jump'),
          onPressed: () {
//            Navigator.pushNamed(context, '/form');
            Navigator.pushNamed(context, '/route');
          },
        ),
        RaisedButton(
          child: Text('appBar'),
          onPressed: () {
            Navigator.pushNamed(context, '/appBar');
          },
        ),
        RaisedButton(
          child: Text('appBarController'),
          onPressed: () {
            Navigator.pushNamed(context, '/tabController');
          },
        ),
        RaisedButton(
            child: Text('jump&return'),
            onPressed: () {
              Navigator.pushNamed(context, '/formReturn');
//              Navigator.pushNamed(context, '/routeReturn');
            }),
        RaisedButton(
          child: Text('jump&returnMap'),
          onPressed: () {
            Navigator.pushNamed(context, '/routeReturnMap',
                arguments: {'id': 123, 'name': 'tadm'});
          },
        ),
        RaisedButton(
          child: Text('replaceRoute'),
          onPressed: () {
            Navigator.pushNamed(context, '/route');
//            Navigator.of(context).pushReplacementNamed('/route');
          },
        )
      ],
    );
  }
}
