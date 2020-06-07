import 'package:flutter/material.dart';

void main() =>runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text(''),
        ),
        body: HomeContent(),
      ),
    );
  }
}

class HomeContent extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return FormDemo();
  }
}

class FormDemo extends StatefulWidget {
  @override
  _FormDemoState createState() => _FormDemoState();
}

class _FormDemoState extends State<FormDemo> {
  @override
  Widget build(BuildContext context) {
    return Form(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          TextFormField(
            decoration: InputDecoration(
                icon: Icon(Icons.people),
                labelText: "用户名或手机号"
            ),
          ),
          TextFormField(
            obscureText: true,
            decoration: InputDecoration(
                icon: Icon(Icons.lock),
                labelText: "密码"
            ),
          ),
          SizedBox(height: 16,),
          Container(
            width: double.infinity,
            height: 44,
            child: RaisedButton(
              color: Colors.lightGreen,
              child: Text("注 册", style: TextStyle(fontSize: 20, color: Colors.white),),
              onPressed: () {
                print("点击了注册按钮");
              },
            ),
          )
        ],
      ),
    );
  }
}
