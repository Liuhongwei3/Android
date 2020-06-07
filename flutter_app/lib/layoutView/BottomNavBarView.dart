import 'package:flutter/material.dart';
import 'package:flutter_app/UserPage.dart';
import 'package:flutter_app/generated/l10n.dart';
import 'package:flutter_localizations/flutter_localizations.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
//      home: Scaffold(
//        appBar: AppBar(
//          title: Text('BottomNavigationBar View'),
//        ),
//        body: Text('Nav Bar'),
////        bottomNavigationBar: HomeContent(),
//        bottomNavigationBar: MyNavBar(),
//      ),

      home: MyNavBar(),
      routes: {
        '/userPage': (context) => UserPage(),
      },
      localizationsDelegates: [
        GlobalMaterialLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        S.delegate
      ],
      supportedLocales: [
        const Locale('zh','CH'),
        const Locale('en','US'),
      ],
    );
  }
}

class HomeContent extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return BottomNavigationBar(
      currentIndex: 0,
      items: [
        BottomNavigationBarItem(icon: Icon(Icons.home), title: Text("home")),
        BottomNavigationBarItem(
            icon: Icon(Icons.category), title: Text('category')),
        BottomNavigationBarItem(
            icon: Icon(Icons.settings), title: Text("settings")),
      ],
    );
  }
}

class MyNavBar extends StatefulWidget {
  @override
  _MyNavBarState createState() => _MyNavBarState();
}

class _MyNavBarState extends State<MyNavBar> {
  int _currentIndex = 0;
  List _pageList = [
    Center(
      child: Text('home'),
    ),
//    Center(
//      child: Text('message'),
//    ),
    Center(
      child: Text('category'),
    ),
//    Center(
//      child: Text('share'),
//    ),
    Center(
      child: Text('settings'),
    ),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
//        title: Text('Nav Bar View'),
        title: Text(S.of(context).title),
      ),
      body: this._pageList[this._currentIndex],
      floatingActionButton: Container(
        width: 60,
        height: 60,
        padding: EdgeInsets.all(8),
        margin: EdgeInsets.only(top: 10),
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(40.0),
          color: Colors.white,
        ),
        child: FloatingActionButton(
          child: Icon(Icons.add),
          backgroundColor:
              this._currentIndex == 1 ? Colors.blue : Colors.orangeAccent,
          onPressed: () {
            setState(() {
              this._currentIndex = 1;
            });
          },
        ),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _currentIndex,
        iconSize: 24.0,
        fixedColor: Colors.blue,
        onTap: (int index) {
          setState(() {
            this._currentIndex = index;
          });
        },
        items: [
          BottomNavigationBarItem(icon: Icon(Icons.home), title: Text("home")),
//          BottomNavigationBarItem(icon: Icon(Icons.message), title: Text("message")),
          BottomNavigationBarItem(
              icon: Icon(Icons.category), title: Text('category')),
//          BottomNavigationBarItem(icon: Icon(Icons.share), title: Text("share")),
          BottomNavigationBarItem(
              icon: Icon(Icons.settings), title: Text("settings")),
        ],
      ),

      //  left
      drawer: Drawer(
        child: Column(
          children: <Widget>[
            Row(
              children: <Widget>[
                Expanded(
//                  child: DrawerHeader(
//                    child: Text('hello flutter~',style: TextStyle(fontSize: 26,color: Colors.pinkAccent),),
//                    decoration: BoxDecoration(
////                        color: Colors.cyan,
//                        image:DecorationImage(
//                          image: NetworkImage('https://www.itying.com/images/flutter/2.png'),
//                          fit: BoxFit.cover
//                        ),
//                    ),
//                  ),

                  child: UserAccountsDrawerHeader(
                    accountName: Text('Tadm'),
                    accountEmail: Text('2873126657@qq.com'),
                    currentAccountPicture: CircleAvatar(
                      backgroundImage: NetworkImage(
                          'https://www.itying.com/images/flutter/3.png'),
                    ),
                    otherAccountsPictures: <Widget>[
                      Image.network(
                          'https://www.itying.com/images/flutter/1.png'),
                      Image.network(
                          'https://www.itying.com/images/flutter/4.png'),
                    ],
                    decoration: BoxDecoration(
                      image: DecorationImage(
                          image: NetworkImage(
                              'https://www.itying.com/images/flutter/2.png'),
                          fit: BoxFit.cover),
                    ),
                  ),
                )
              ],
            ),
            ListTile(
              leading: CircleAvatar(
                child: Icon(Icons.home),
              ),
              title: Text('Personal'),
            ),
            Divider(),
            ListTile(
              leading: CircleAvatar(
                child: Icon(Icons.people),
              ),
              title: Text('Users'),
              onTap: () {
                Navigator.of(context).pop(); //  hidden drawer
                Navigator.pushNamed(context, '/userPage');
              },
            ),
            Divider(),
            ListTile(
              leading: CircleAvatar(
                child: Icon(Icons.settings),
              ),
              title: Text('Settings'),
            )
          ],
        ),
      ),

      //  right
//      endDrawer: Drawer(
//        child: Text('this is right drawer'),
//      ),
    );
  }
}
