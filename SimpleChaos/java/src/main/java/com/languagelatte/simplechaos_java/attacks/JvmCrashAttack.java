package com.languagelatte.simplechaos_java.attacks;

import com.languagelatte.simplechaos_java.ChaosProperties;

public class JvmCrashAttack implements Attack {

  @Override
  public void attack(ChaosProperties properties) {

    try {
      System.exit(1);
    } catch (Exception e) {

    }
  }
}
