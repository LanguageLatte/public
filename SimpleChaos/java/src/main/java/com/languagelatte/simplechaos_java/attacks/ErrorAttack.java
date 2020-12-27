package com.languagelatte.simplechaos_java.attacks;

import com.languagelatte.simplechaos_java.ChaosProperties;

public class ErrorAttack implements Attack {

  @Override
  public void attack(ChaosProperties properties) {
    throw new Error("Chaos Attack Error");
  }
}
