import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AppBarView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: 3,
      child: Scaffold(
          appBar: AppBar(
            title: Text('routePage'),

//            title: Row(
//              mainAxisAlignment: MainAxisAlignment.center,
//              children: <Widget>[
//                Expanded(
//                  child: TabBar(
//                    labelColor: Colors.pinkAccent,
//                    unselectedLabelColor: Colors.white,
//                    indicatorColor: Colors.blue,
//                    indicatorSize: TabBarIndicatorSize.label,
//                    tabs: <Widget>[
//                      Tab(
//                        text: 'Hot',
//                      ),
//                      Tab(
//                        text: 'Recommend',
//                      ),
//                      Tab(
//                        text: 'About',
//                      )
//                    ],
//                  ),
//                )
//              ],
//            ),
            backgroundColor: Colors.orangeAccent,
            //  为子级页面时会替换掉默认的返回按钮
//            leading: IconButton(
//              icon: Icon(Icons.menu),
//              onPressed: () {
//                print('menu clicked~');
//              },
//            ),
            centerTitle: false,
            actions: <Widget>[
              IconButton(
                icon: Icon(Icons.search),
                onPressed: () {
                  print('search clicked~');
                },
              ),
              IconButton(
                icon: Icon(Icons.settings),
                onPressed: () {
                  print('settings clicked~');
                },
              )
            ],
            bottom: TabBar(
              labelColor: Colors.pinkAccent,
              unselectedLabelColor: Colors.white,
              indicatorColor: Colors.blue,
              indicatorSize: TabBarIndicatorSize.label,
              isScrollable: true,
              tabs: <Widget>[
                Tab(
                  text: 'Hot',
                ),
                Tab(
                  text: 'Recommend',
                ),
                Tab(
                  text: 'About',
                )
              ],
            ),
          ),
          floatingActionButton: FloatingActionButton(
            child: Text('back'),
            onPressed: () {
              Navigator.of(context).pop();
            },
          ),
          body: TabBarView(
            children: <Widget>[
              ListView(
                children: <Widget>[
                  ListTile(
                    title: Text('This is hot~'),
                  ),
                  ListTile(
                    title: Text('This is hot~'),
                  ),
                ],
              ),
              ListView(
                children: <Widget>[
                  ListTile(
                    title: Text('This is Recommend~'),
                  ),
                  ListTile(
                    title: Text('This is Recommend~'),
                  ),
                ],
              ),
              ListView(
                children: <Widget>[
                  ListTile(
                    title: Text('This is About~'),
                  ),
                  ListTile(
                    title: Text('This is About~'),
                  ),
                ],
              )
            ],
          )),
    );
  }
}
