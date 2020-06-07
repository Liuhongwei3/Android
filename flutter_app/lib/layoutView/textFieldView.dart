import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('TextField View'),
        ),
        body: HomeContent(),
      ),
    );
  }
}

class HomeContent extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(20),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[TextFieldDemo()],
      ),
    );
  }
}

class TextFieldDemo extends StatefulWidget {
  @override
  _TextFieldDemoState createState() => _TextFieldDemoState();
}

class _TextFieldDemoState extends State<TextFieldDemo> {
  var _username = new TextEditingController();
  var _password;
  var flag1 = true;
  var flag2 = true;
  var flag3 = true;
  var sex = 1;
  var like = 1;
  List hobby = [
    {'checked': true, 'title': 'coding'},
    {'checked': false, 'title': 'study'},
    {'checked': true, 'title': 'music'},
  ];

  List<Widget> _getHobbies() {
    List<Widget> tempList = [];
    for (var i = 0; i < hobby.length; i++) {
      tempList.add(Row(
        children: <Widget>[
          Text(this.hobby[i]['title'] + ':'),
          Checkbox(
            value: this.hobby[i]['checked'],
            onChanged: (value) {
              setState(() {
                this.hobby[i]['checked'] = value;
              });
            },
          )
        ],
      ));
    }
    print(tempList.length);
    return tempList;
  }

  void _sexChanged(value) {
    setState(() {
      this.sex = value;
    });
  }

  void _likeChanged(value) {
    setState(() {
      this.like = value;
    });
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _username.text = 'tadm';
  }

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: ListView(
        children: <Widget>[
          Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              TextField(
                decoration: InputDecoration(
                    icon: Icon(Icons.people),
                    labelText: "username",
                    hintText: "please input username",
                    border: InputBorder.none,
                    filled: true,
                    fillColor: Colors.orangeAccent),
                controller: this._username,
                onChanged: _sexChanged,
                onSubmitted: (value) {
                  print("onSubmitted:$value");
                },
              ),
              SizedBox(
                height: 5,
              ),
              TextField(
                obscureText: true,
                decoration: InputDecoration(
                    icon: Icon(Icons.pageview),
                    labelText: "password",
                    hintText: "please input pwd",
                    border: InputBorder.none,
                    filled: true,
                    fillColor: Colors.greenAccent),
                onChanged: _sexChanged,
              ),
              Divider(),
              Row(
                children: <Widget>[
                  Checkbox(
                    value: this.flag1,
                    activeColor: Colors.blue,
                    onChanged: (value) {
                      setState(() {
                        this.flag1 = value;
                      });
                    },
                  ),
                ],
              ),
              CheckboxListTile(
                value: this.flag2,
                activeColor: Colors.blue,
                onChanged: (value) {
                  setState(() {
                    this.flag2 = value;
                  });
                },
                title: Text('this is a checkboxtile'),
                subtitle: Text('some descriptions~'),
              ),
              Divider(),
              CheckboxListTile(
                value: this.flag3,
                activeColor: Colors.blue,
                onChanged: (value) {
                  setState(() {
                    this.flag3 = value;
                  });
                },
                title: Text('this is a checkboxtile'),
                subtitle: Text('some descriptions~'),
                secondary: Icon(Icons.monochrome_photos),
              ),
              Divider(),
              Row(
                children: <Widget>[
                  Text('Male'),
                  Radio(
                    value: 1,
                    groupValue: this.sex,
                    onChanged: _sexChanged,
                  ),
                  Text('Female'),
                  Radio(
                    value: 0,
                    groupValue: this.sex,
                    onChanged: _sexChanged,
                  )
                ],
              ),
              Divider(),
              RadioListTile(
                value: 1,
                groupValue: this.like,
                selected: this.like == 1,
                onChanged: _likeChanged,
                title: Text('Prontend'),
                subtitle: Text('prontend descriptions~'),
//          secondary: Icon(Icons.flight),
                secondary: CircleAvatar(
                  child: Image.network(
                      'https://www.itying.com/images/flutter/2.png'),
                ),
              ),
              RadioListTile(
                value: 0,
                groupValue: this.like,
                selected: this.like == 0,
                onChanged: _likeChanged,
                title: Text('Backend'),
                subtitle: Text('backend descriptions~'),
                secondary: Icon(Icons.camera_roll),
              ),
              Divider(),
              Switch(
                value: true,
                onChanged: (value) {},
              ),
              Divider(),
              Column(
                children: this._getHobbies(),
              )
            ],
          ),
        ],
      ),
    );
  }
}
