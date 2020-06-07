import 'package:flutter/material.dart';

class TabControllerPage extends StatefulWidget {
  @override
  _TabControllerPageState createState() => _TabControllerPageState();
}

class _TabControllerPageState extends State<TabControllerPage>
    with SingleTickerProviderStateMixin {
  TabController _tabController;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    _tabController = new TabController(vsync: this, length: 3);
    _tabController.addListener((){
      print(_tabController.index);
      setState(() {

      });
    });
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
    _tabController.dispose();
    print('dispose');
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('TabController View'),
        bottom: TabBar(
          controller: this._tabController,
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
      body: TabBarView(
        controller: this._tabController,
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
      ),
    );
  }
}
