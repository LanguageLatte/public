package com.languagelatte.simplechaos_java.attacks;

import com.languagelatte.simplechaos_java.ChaosProperties;
import com.languagelatte.simplechaos_java.SimpleChaosConstants;
import java.util.Random;

public class LatencyAttack implements Attack {

  @Override
  public void attack(ChaosProperties properties) {
    int min = properties.getIntProperty(SimpleChaosConstants.RANDOM_CHAOS_LATENCY_MINTIME);
    int max = properties.getIntProperty(SimpleChaosConstants.RANDOM_CHAOS_LATENCY_MAXTIME);
    Random r = new Random();
    int latency = r.nextInt(max - min) + min;

    try {
      Thread.sleep((long) latency);
    } catch (Exception e) {

    }
  }
}
