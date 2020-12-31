package com.languagelatte.simplechaos.attacks;

import com.languagelatte.simplechaos.ChaosProperties;
import com.languagelatte.simplechaos.SimpleChaosConstants;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LatencyAttack implements Attack {

  private static final Logger LOGGER = LoggerFactory.getLogger(LatencyAttack.class);

  @Override
  public void attack(ChaosProperties properties) {
    int min = properties.getIntProperty(SimpleChaosConstants.RANDOM_CHAOS_LATENCY_MINTIME);
    int max = properties.getIntProperty(SimpleChaosConstants.RANDOM_CHAOS_LATENCY_MAXTIME);
    Random r = new Random();
    int latency = r.nextInt(max - min) + min;

    try {
      LOGGER.info("Starting Latency Attack. Will run for " + latency + "milliseconds");
      Thread.sleep((long) latency);
    } catch (Exception e) {
      LOGGER.info("Latency Attack was intrupted.");
    }
  }
}
