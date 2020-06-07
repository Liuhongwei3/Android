import 'package:flutter/material.dart';
import 'package:date_format/date_format.dart';
import 'package:flutter_app/generated/l10n.dart';
import 'package:flutter_localizations/flutter_localizations.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('DateView'),
        ),
        body: DateView(),
      ),
//      localizationsDelegates: [
//        GlobalMaterialLocalizations.delegate,
//        GlobalCupertinoLocalizations.delegate,
//        GlobalWidgetsLocalizations.delegate,
//        S.delegate
//      ],
//      supportedLocales: [
//        const Locale('zh','CH'),
//        const Locale('en','US'),
//      ],
    );
  }
}

class DateView extends StatefulWidget {
  @override
  _DateViewState createState() => _DateViewState();
}

class _DateViewState extends State<DateView> {
  DateTime _date = DateTime.now();
  var _time = TimeOfDay(hour: 12, minute: 10);

  _showDatePicker() {
    showDatePicker(
            context: context,
            initialDate: _date,
            firstDate: DateTime(2000),
            lastDate: DateTime(2100))
        .then((onValue) {
      print(onValue);
      setState(() {
        this._date = onValue;
      });
    });
  }

  _showTimePicker() {
    showTimePicker(context: context, initialTime: _time).then((onValue) {
      setState(() {
        this._time = onValue;
      });
    });
  }

//  _showDatePicker() async{
//    var result = await showDatePicker(
//        context: context,
//        initialDate: _date,
//        firstDate: DateTime(2000),
//        lastDate: DateTime(2100)
//    );
//
//    print(result);
//  }

  @override
  void initState() {
    // TODO: implement initStates
    super.initState();
    print(_date); //  2020-04-19 11:26:21.723199
//    print(date.millisecondsSinceEpoch); //  1587295581723
//    print(DateTime.fromMicrosecondsSinceEpoch(1587295581723));

    print(formatDate(_date, [yyyy, '-', mm, '-', dd]));
    print(formatDate(_date, [yyyy, '年', mm, '月', dd]));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('DateTime View'),
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          InkWell(
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Text("${formatDate(this._date, [yyyy, '年', mm, '月', dd])}"),
                Icon(Icons.arrow_drop_down)
              ],
            ),
            onTap: _showDatePicker,
          ),
          InkWell(
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Text("${this._time.format(context)}"),
                Icon(Icons.arrow_drop_down)
              ],
            ),
            onTap: _showTimePicker,
          )
        ],
      ),
    );
  }
}
