//  https://flutterchina.club/

import 'dart:math';

import 'package:flutter/material.dart';

//  第三方库：https://pub.dev/packages
//  为解决库冲突：import sth as sthName
//  只导入库的一部分：import sth show/hide sthFunc

void main() {
  print("hello dart");

  var text = "hello dart";
  //  区分大小写
  String Text = "Upper text";
  //  '''sth''',""sth""; 格式化字符串

  int number = 123;
  //  number = "123"; //  error
  //  123 != '123'  不进行类型转换

  const PI = 3.14;
  final PIM = 3.14;
//  PI = 2; //  error
//  区别：
//  const date1 = new DateTime.now(); //  error
  final date2 = new DateTime.now(); //  right

  bool flag = true;

  var a = [2, 3, 4];
  a.forEach((value) {
    print(value);
  });
  var aTwo = a.map((value) {
    return value * 2;
  });
  var aMoreFive = aTwo.where((value) {
    return value > 5;
  });
  //  forEach map where any every
  var b = new List<int>();
  b.add(1);
  b.add(6);

  //  Map
  var person = {'name': 'tadm', 'age': 20};
  person.forEach((key, value) {
    print("$key --- $value");
  });
  var person1 = new Map();
  person1['name'] = 'tadm1';
  person1['age'] = 20;

  String text2 = '123';
  var changeToNum = int.parse(text2);

  int number1 = 23;
  var changeToString = number1.toString();
  var changeToDouble = number1.toDouble();

  List list = ['tadm', 20, 'student'];
  list.add('frontend');
  list.addAll(['apple', 'banana', 'grape']);
//  list.insert();  list.insertAll();
  list.remove('grape');
  list.removeAt(2);
  list.fillRange(0, 1, 'tadm1');
  var listToString = list.join('-');
  //  listToString.split('-') --- toList

  Set set = {'tadm', 20, 'student'};
  set.add('tadm');

//  print(text);
//
//  print(Text);
//  print(text + Text);
//  print("$text $Text");
//
//  print(number);
//  print(PI);
//  print(PIM);
//
//  print(date2);
//
//  print(flag);
//
//  print(a);
//  print(a.length);
//  print(aTwo);
//  print(aMoreFive.toList());
//  print(b);
//
//  print(person);
//  print(person['name']);
//  print(person.keys);
//  print(person.values.toList());
//  print(person.containsKey('name'));
//  print(person.containsValue('tadm'));
//  print(person1);
//  print(person1['age']);
//
//  //  judge type
//  print(text is String);
//
//  print(changeToNum);
//  print(changeToNum is int);
//
//  print(changeToString);
//  print(changeToString is String);
//  print(changeToDouble);
//  print(changeToDouble is double);
//
//  print(text.isEmpty);
//  print(text.isNotEmpty);
//  print(number.isNaN);
//
//  print(list);
//  print(list.length);
//  print(list.reversed.toList());
//  print(list.indexOf('tadm'));
//  print(listToString);
//  print(listToString is String);
//
//  print(set);

  String returnInfo(String name, [int age = 20, String sex]) {
    if (sex != null) {
      return "$name --- $age --- $sex";
    } else {
      return "$name --- $age";
    }
  }

//  print(returnInfo('tadm'));
//  print(returnInfo('tadm', 20));
//  print(returnInfo('tadm', 20, 'male'));

  Person person2 = new Person('tadm', 20);
  person2.printInfo();
  print(person2 is Person);
  //  convert type
  (person2 as Person).printInfo();
  //  级联操作
  person2
    ..name = 'tadm2'
    ..age = 19
    ..printInfo();

//  NewPerson newPerson = NewPerson('newTadm', 20, 'frontend');
  NewPerson newPerson = NewPerson.more('newTadm', 20, 'frontend');
  newPerson.printInfo();
  newPerson.newPrintInfo();

  Dog dog = Dog();
  dog.eat();
  dog.run();

  D d = D('newD', 18);
  d.printInfo();
  d.printM();
  d.printN();
//  print(d is Person);
//  print(d is M);
//  print(d is N);

  print(getData(123));
  print(getData('text'));
  print(getData<num>(23));
//  print(getData<num>('23'));  //  error

  //  import math lib and usage
  print(max(2, 6));

  asyncMethod() async {
    return 'async method';
  }

  doAsync() async {
    print(await asyncMethod());
  }

  doAsync();

  List<Widget> tempList = [];
  print(tempList);
}

class Person {
  String name;
  num age;

  Person(this.name, this.age);

  //  命名构造函数
  Person.more(this.name, this.age);

  void printInfo() {
    print("${this.name} --- ${this.age}");
  }
}

class NewPerson extends Person {
  String job;

  NewPerson(String name, num age, String job) : super(name, age) {
    this.job = job;
  }

  NewPerson.more(String name, num age, String job) : super.more(name, age) {
    this.job = job;
  }

  @override
  void printInfo() {
    super.printInfo();
    print("override");
  }

  void newPrintInfo() {
    print("${this.name} --- ${this.age} --- ${this.job}");
  }
}

//  抽象类无法直接被实例化,一般用来定义接口(公共标准)
abstract class Animal {
  eat(); //  abstract method
  run(); //  abstract method
}

class Dog extends Animal {
  @override
  eat() {
    // TODO: implement eat
    print('dog eatting bones');
  }

  @override
  run() {
    // TODO: implement run
    print('dog running');
  }
}

//  实现接口
class Cat implements Animal {
  @override
  eat() {
    // TODO: implement eat
    return null;
  }

  @override
  run() {
    // TODO: implement run
    return null;
  }
}

abstract class A {
  printA();
}

abstract class B {
  printB();
}

//  实现多个接口但不能多继承
class C implements A, B {
  @override
  printA() {
    // TODO: implement printA
    return null;
  }

  @override
  printB() {
    // TODO: implement printB
    return null;
  }
}

class M {
  void printM() {
    print('M');
  }
}

class N {
  void printN() {
    print('N');
  }
}

//  mixins
//  M,N 只能继承自 Object 且不能有构造函数,顺序则会影响同名函数,后面的则会覆盖前面的
class D extends Person with M, N {
  D(String name, num age) : super(name, age);
}

T getData<T>(T value) {
  return value;
}

List list1 = new List<String>();
List list2 = new List<int>();
