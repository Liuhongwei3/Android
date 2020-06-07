import 'package:flutter/material.dart';

import 'package:flutter_app/routeView/NamedRoutePage.dart';
import 'package:flutter_app/routeView/OrdinaryRoutPage.dart';
import 'package:flutter_app/routeView/ToRouteReturn.dart';
import 'package:flutter_app/routes/Routes.dart';

void main() => runApp(RouteView());

class RouteView extends StatelessWidget {
//  final routes = {
//    '/form': (context) => ToRoutePage(),
//    '/formReturn': (context) => ToRouteReturnPage('Named route'),
//  };

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: Text('Route View'),
        ),
//        body: HomeContentOrdinaryRoute(),
        body: HomeContentJump(),
      ),

      //  Named route
      routes: {
//        '/form': (context) => ToRoutePage(),
        '/formReturn': (context) => ToRouteReturnPage('Named route'),
      },

      //  Named route return value
      initialRoute: '/',
      onGenerateRoute: onGenerateRoute,
    );
  }
}