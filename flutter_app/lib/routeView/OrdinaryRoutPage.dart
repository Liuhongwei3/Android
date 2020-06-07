import 'package:flutter/material.dart';

import 'ToRoutePage.dart';
import 'ToRouteReturn.dart';

//  ordinary route
class HomeContentOrdinaryRoute extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        RaisedButton(
          child: Text('jump'),
          onPressed: () {
            Navigator.of(context)
                .push(MaterialPageRoute(builder: (context) => ToRoutePage()));
          },
        ),
        RaisedButton(
          child: Text('jump&return'),
          onPressed: () {
            Navigator.of(context).push(MaterialPageRoute(
                builder: (context) =>
                    ToRouteReturnPage('this is a return value')));
          },
        )
      ],
    );
  }
}