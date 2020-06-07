import 'package:flutter/material.dart';

import 'package:flutter_app/RouteView.dart';
import 'package:flutter_app/layoutView/AppBarView.dart';
import 'package:flutter_app/routeView/NamedRoutePage.dart';
import 'package:flutter_app/routeView/ToRoutePage.dart';
import 'package:flutter_app/routeView/ToRouteReturn.dart';
import 'package:flutter_app/routeView/ToReturnMapPage.dart';
import 'package:flutter_app/TabController.dart';
import 'package:flutter_app/UserPage.dart';

//配置路由
final routes = {
  '/': (context) => HomeContentJump(),
  '/route': (context) => ToRoutePage(),
  '/appBar': (context) => AppBarView(),
  '/userPage': (context) => UserPage(),
  '/tabController': (context) => TabControllerPage(),
//  'routeReturn': (context) => ToRouteReturnPage('Named route'),
  '/routeReturnMap': (context, {arguments}) =>
      ToRouteReturnMapPage(arguments: arguments)
};

var onGenerateRoute = (RouteSettings settings) {
  // 统一处理
  final String name = settings.name;
  final Function pageContentBuilder = routes[name];

  if (pageContentBuilder != null) {
    if (settings.arguments != null) {
      final Route route = MaterialPageRoute(
          builder: (context) =>
              pageContentBuilder(context, arguments: settings.arguments));
      return route;
    } else {
      final Route route =
          MaterialPageRoute(builder: (context) => pageContentBuilder(context));
      return route;
    }
  }
};
