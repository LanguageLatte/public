package dev.noblehouse.side_effect;

import dev.noblehouse.side_effect.annotations.SideEffect;
import dev.noblehouse.side_effect.annotations.SideEffectIgnore;
import java.util.ArrayList;
import java.util.List;

public class Example {

  double x = Math.random();

  public double f1() {
    double w = Math.random();
    return Math.random();
  }

  @SideEffect
  public int f2(int x, int y) {
    double w = Math.random();
    return x + y;
  }

  @SideEffectIgnore
  public int f3(int x, int y) {
    double w = Math.random();
    return x + y;
  }

  @SideEffect
  public double f4() {
    return Math.random();
  }
  ;

  public double f5(int x, int y) {
    var w = f4();
    return x + y + w;
  }

  public void f6(List<String> list) {
    list.add("this is a sideEffect");
  }

  public List<String> f6() {
    var list = new ArrayList<String>();
    list.add("this is not a sideEffect");
    return list;
  }

  public List<String> f7(List<String> list) {
    var newList = new ArrayList<String>(list);
    newList.add("this is not a sideEffect");
    return newList;
  }
}
