package com.languagelatte.simplechaos.attacks.attack;

import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.properties.SimpleChaosConstants;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LatencyAttack implements Attack {

  private static final Logger LOGGER = LoggerFactory.getLogger(LatencyAttack.class);

  @Override
  public void attack(ChaosProperties properties) {
    int min;
    int max;

    if (properties.isPropertyValuePresent(SimpleChaosConstants.LATENCY_ATTACK_MINTIME)) {
      min = properties.getIntProperty(SimpleChaosConstants.LATENCY_ATTACK_MINTIME);
    } else {
      min = 1000;
    }

    if (properties.isPropertyValuePresent(SimpleChaosConstants.LATENCY_ATTACK_MAXTIME)) {
      max = properties.getIntProperty(SimpleChaosConstants.LATENCY_ATTACK_MAXTIME);
    } else {
      max = 25000;
    }

    Random r = new Random();

    int bound = (max - min) > 0 ? (max - min) : 1;
    int latency = r.nextInt(bound) + min;

    try {
      LOGGER.info("Starting Latency Attack. Will run for " + latency + "milliseconds");
      Thread.sleep((long) latency);
    } catch (Exception e) {
      LOGGER.info("Latency Attack was interrupted.");
    }
  }
}
