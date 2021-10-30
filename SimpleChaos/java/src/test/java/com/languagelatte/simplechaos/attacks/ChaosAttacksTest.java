package com.languagelatte.simplechaos.attacks;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.languagelatte.simplechaos.attacks.attack.ErrorAttack;
import com.languagelatte.simplechaos.attacks.attack.ExceptionAttack;
import com.languagelatte.simplechaos.attacks.attack.JvmCrashAttack;
import com.languagelatte.simplechaos.attacks.attack.LatencyAttack;
import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.properties.SimpleChaosConstants;
import com.languagelatte.simplechaos.reports.ConsoleLogReporter;
import java.time.Clock;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChaosAttacksTest {

  ConsoleLogReporter consoleLogReporter;
  ExceptionAttack exceptionAttack;
  LatencyAttack latencyAttack;
  ErrorAttack errorAttack;
  JvmCrashAttack jvmCrashAttack;

  @BeforeEach
  public void beforeAll() {
    consoleLogReporter = mock(ConsoleLogReporter.class);
    exceptionAttack = mock(ExceptionAttack.class);
    latencyAttack = mock(LatencyAttack.class);
    errorAttack = mock(ErrorAttack.class);
    jvmCrashAttack = mock(JvmCrashAttack.class);
    ChaosProperties.INSTANCE.clearProperties();
  }

  @Test
  public void randomAttackTest() {

    ChaosProperties properties = ChaosProperties.INSTANCE;
    Map<String, String> props = new HashMap<>();

    props.put(SimpleChaosConstants.ENABLED, "true");

    props.put(SimpleChaosConstants.EXCEPTION_ATTACK_ENABLED, "true");
    props.put(SimpleChaosConstants.EXCEPTION_ATTACK_CHANCE, "1.0");

    props.put(SimpleChaosConstants.LATENCY_ATTACK_ENABLED, "true");
    props.put(SimpleChaosConstants.LATENCY_ATTACK_CHANCE, "1.0");

    props.put(SimpleChaosConstants.JVMCRASH_ATTACK_ENABLED, "true");
    props.put(SimpleChaosConstants.JVMCRASH_ATTACK_CHANCE, "1.0");

    props.put(SimpleChaosConstants.ERROR_ATTACK_ENABLED, "true");
    props.put(SimpleChaosConstants.ERROR_ATTACK_CHANCE, "1.0");
    props.put(
        SimpleChaosConstants.ENABLED_HOURS,
        "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23");
    props.put(
        SimpleChaosConstants.ENABLED_DAYS,
        "MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY");

    properties.loadProperties(props);

    RandomChaosAttacks r =
        new RandomChaosAttacks(
            consoleLogReporter,
            properties,
            exceptionAttack,
            latencyAttack,
            jvmCrashAttack,
            errorAttack,
            Clock.system(ZoneId.systemDefault()));

    // Kind of a weird test. 100 calls should reach all methods.
    for (int x = 0; x < 100; x++) {
      r.randomAttack(properties);
    }

    verify(exceptionAttack, atLeastOnce()).attack(properties);
    verify(latencyAttack, atLeastOnce()).attack(properties);
    verify(jvmCrashAttack, atLeastOnce()).attack(properties);
    verify(errorAttack, atLeastOnce()).attack(properties);
  }

  @Test
  public void randomNotEnabledAttackTest() {

    ChaosProperties properties = ChaosProperties.INSTANCE;
    Map<String, String> props = new HashMap<>();

    props.put(SimpleChaosConstants.ENABLED, "true");

    props.put(
        SimpleChaosConstants.ENABLED_HOURS,
        "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23");
    props.put(
        SimpleChaosConstants.ENABLED_DAYS,
        "MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY");

    properties.loadProperties(props);

    RandomChaosAttacks r =
        new RandomChaosAttacks(
            consoleLogReporter,
            properties,
            exceptionAttack,
            latencyAttack,
            jvmCrashAttack,
            errorAttack,
            Clock.system(ZoneId.systemDefault()));

    // Kind of a weird test. 100 calls should reach all methods.
    for (int x = 0; x < 100; x++) {
      r.randomAttack(properties);
    }

    verify(exceptionAttack, never()).attack(properties);
    verify(latencyAttack, never()).attack(properties);
    verify(jvmCrashAttack, never()).attack(properties);
    verify(errorAttack, never()).attack(properties);
  }
}
