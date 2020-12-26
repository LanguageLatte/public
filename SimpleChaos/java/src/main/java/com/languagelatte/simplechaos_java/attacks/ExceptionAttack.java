package com.languagelatte.simplechaos_java.attacks;

public class ExceptionAttack implements Attack {

  @Override
  public void attack() {
    throw new RuntimeException("Chaos Attack Exception");
  }
}
