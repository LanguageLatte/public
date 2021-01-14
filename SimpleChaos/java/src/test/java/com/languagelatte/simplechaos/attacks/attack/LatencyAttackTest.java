package com.languagelatte.simplechaos.attacks.attack;

import static org.junit.Assert.assertTrue;

import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.properties.ChaosPropertiesDefaultImpl;
import com.languagelatte.simplechaos.properties.SimpleChaosConstants;
import java.util.HashMap;
import java.util.Map;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

public class LatencyAttackTest {

  @Test
  public void testLatency1000() {

    Integer min = 1000;
    Integer max = 1000;

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
    Map<String, String> props = new HashMap<>();
    props.put(SimpleChaosConstants.LATENCY_ATTACK_MINTIME, min.toString());
    props.put(SimpleChaosConstants.LATENCY_ATTACK_MAXTIME, max.toString());
    properties.loadProperties(props);

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

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
    Map<String, String> props = new HashMap<>();
    props.put(SimpleChaosConstants.LATENCY_ATTACK_MINTIME, min.toString());
    props.put(SimpleChaosConstants.LATENCY_ATTACK_MAXTIME, max.toString());
    properties.loadProperties(props);

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

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
    Map<String, String> props = new HashMap<>();
    props.put(SimpleChaosConstants.LATENCY_ATTACK_MINTIME, min.toString());
    props.put(SimpleChaosConstants.LATENCY_ATTACK_MAXTIME, max.toString());
    properties.loadProperties(props);

    Attack latencyAttack = new LatencyAttack();

    long start = System.currentTimeMillis();
    latencyAttack.attack(properties);
    long timeTaken = System.currentTimeMillis() - start;

    assertTrue((timeTaken < min + 50) && (timeTaken > min - 50));
  }

  @Test
  @Ignore
  public void testDefault() {

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
    Map<String, String> props = new HashMap<>();
    properties.loadProperties(props);

    Attack latencyAttack = new LatencyAttack();

    long start = System.currentTimeMillis();
    latencyAttack.attack(properties);
    long timeTaken = System.currentTimeMillis() - start;

    assertTrue((timeTaken < 25_000 + 50) && (timeTaken > 1_000 - 50));
  }
}
