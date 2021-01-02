package com.languagelatte.simplechaos.attacks.attack;

import static org.junit.Assert.assertTrue;

import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.properties.ChaosPropertiesDefaultImpl;
import com.languagelatte.simplechaos.properties.SimpleChaosConstants;
import org.junit.jupiter.api.Test;

public class LatencyAttackTest {

  @Test
  public void testLatency1000() {

    Integer min = 999;
    Integer max = 1000;

    System.setProperty(SimpleChaosConstants.LATENCY_ATTACK_MINTIME, min.toString());
    System.setProperty(SimpleChaosConstants.LATENCY_ATTACK_MAXTIME, max.toString());

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
    properties.loadProperties();

    Attack latencyAttack = new LatencyAttack();

    long start = System.currentTimeMillis();
    latencyAttack.attack(properties);
    long timeTaken = System.currentTimeMillis() - start;

    assertTrue((timeTaken < max + 50) && (timeTaken > min - 50));
  }

  @Test
  public void testLatency0() {

    Integer min = 0;
    Integer max = 0;

    System.setProperty(SimpleChaosConstants.LATENCY_ATTACK_MINTIME, min.toString());
    System.setProperty(SimpleChaosConstants.LATENCY_ATTACK_MAXTIME, max.toString());

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
    properties.loadProperties();

    Attack latencyAttack = new LatencyAttack();

    long start = System.currentTimeMillis();
    latencyAttack.attack(properties);
    long timeTaken = System.currentTimeMillis() - start;

    assertTrue((timeTaken < max + 50) && (timeTaken > min - 50));
  }

  @Test
  // When min is greater than the max, the min will be used.
  public void testLatencyNegative() {

    Integer min = 1000;
    Integer max = 100;

    System.setProperty(SimpleChaosConstants.LATENCY_ATTACK_MINTIME, min.toString());
    System.setProperty(SimpleChaosConstants.LATENCY_ATTACK_MAXTIME, max.toString());

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
    properties.loadProperties();

    Attack latencyAttack = new LatencyAttack();

    long start = System.currentTimeMillis();
    latencyAttack.attack(properties);
    long timeTaken = System.currentTimeMillis() - start;

    assertTrue((timeTaken < min + 50) && (timeTaken > min - 50));
  }
}
