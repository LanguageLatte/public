package com.languagelatte.simplechaos.attacks;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import com.languagelatte.simplechaos.attacks.attack.ErrorAttack;
import com.languagelatte.simplechaos.attacks.attack.ExceptionAttack;
import com.languagelatte.simplechaos.attacks.attack.JvmCrashAttack;
import com.languagelatte.simplechaos.attacks.attack.LatencyAttack;
import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.properties.ChaosPropertiesDefaultImpl;
import com.languagelatte.simplechaos.properties.SimpleChaosConstants;
import com.languagelatte.simplechaos.reports.ConsoleLogReporter;
import java.time.Clock;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RandomChaosAttacksTest {

  ConsoleLogReporter consoleLogReporter;
  ExceptionAttack exceptionAttack;
  LatencyAttack latencyAttack;
  ErrorAttack errorAttack;
  JvmCrashAttack jvmcrashAttack;

  @BeforeEach
  public void beforeAll() {
    consoleLogReporter = mock(ConsoleLogReporter.class);
    exceptionAttack = mock(ExceptionAttack.class);
    latencyAttack = mock(LatencyAttack.class);
    errorAttack = mock(ErrorAttack.class);
    jvmcrashAttack = mock(JvmCrashAttack.class);
  }

  @Test
  public void exceptionAttackEnabledTest() {

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
    Map<String, String> props = new HashMap<>();

    props.put(SimpleChaosConstants.ENABLED, "true");
    props.put(
        SimpleChaosConstants.ENABLED_HOURS,
        "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23");
    props.put(
        SimpleChaosConstants.ENABLED_DAYS,
        "MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY");
    props.put(SimpleChaosConstants.EXCEPTION_ATTACK_ENABLED, "true");
    props.put(SimpleChaosConstants.EXCEPTION_ATTACK_CHANCE, "1.0");
    properties.loadProperties(props);

    RandomChaosAttacks r =
        new RandomChaosAttacks(
            consoleLogReporter,
            properties,
            exceptionAttack,
            latencyAttack,
            jvmcrashAttack,
            errorAttack,
            Clock.system(ZoneId.systemDefault()));

    r.exception();
    verify(consoleLogReporter).reportAttack(ChaosAttack.EXCEPTION);
    verify(exceptionAttack).attack(properties);
    verifyNoInteractions(latencyAttack);
    verifyNoInteractions(jvmcrashAttack);
    verifyNoInteractions(errorAttack);
  }

  @Test
  public void exceptionAttackNotEnabledTest() {

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
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
            jvmcrashAttack,
            errorAttack,
            Clock.system(ZoneId.systemDefault()));

    r.exception();
    verifyNoInteractions(consoleLogReporter);
    verifyNoInteractions(exceptionAttack);
    verifyNoInteractions(latencyAttack);
    verifyNoInteractions(jvmcrashAttack);
    verifyNoInteractions(errorAttack);
  }

  @Test
  public void latencyAttackEnabledTest() {

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
    Map<String, String> props = new HashMap<>();

    props.put(SimpleChaosConstants.ENABLED, "true");
    props.put(
        SimpleChaosConstants.ENABLED_HOURS,
        "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23");
    props.put(
        SimpleChaosConstants.ENABLED_DAYS,
        "MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY");
    props.put(SimpleChaosConstants.LATENCY_ATTACK_ENABLED, "true");
    props.put(SimpleChaosConstants.LATENCY_ATTACK_CHANCE, "1.0");
    properties.loadProperties(props);

    RandomChaosAttacks r =
        new RandomChaosAttacks(
            consoleLogReporter,
            properties,
            exceptionAttack,
            latencyAttack,
            jvmcrashAttack,
            errorAttack,
            Clock.system(ZoneId.systemDefault()));

    r.latency();
    verify(consoleLogReporter).reportAttack(ChaosAttack.LATENCY);
    verifyNoInteractions(exceptionAttack);
    verify(latencyAttack).attack(properties);
    verifyNoInteractions(jvmcrashAttack);
    verifyNoInteractions(errorAttack);
  }

  @Test
  public void latencyAttackNotEnabledTest() {

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
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
            jvmcrashAttack,
            errorAttack,
            Clock.system(ZoneId.systemDefault()));

    r.latency();
    verifyNoInteractions(consoleLogReporter);
    verifyNoInteractions(exceptionAttack);
    verifyNoInteractions(latencyAttack);
    verifyNoInteractions(jvmcrashAttack);
    verifyNoInteractions(errorAttack);
  }

  @Test
  public void jvmCrashAttackEnabledTest() {

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
    Map<String, String> props = new HashMap<>();

    props.put(SimpleChaosConstants.ENABLED, "true");
    props.put(
        SimpleChaosConstants.ENABLED_HOURS,
        "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23");
    props.put(
        SimpleChaosConstants.ENABLED_DAYS,
        "MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY");
    props.put(SimpleChaosConstants.JVMCRASH_ATTACK_ENABLED, "true");
    props.put(SimpleChaosConstants.JVMCRASH_ATTACK_CHANCE, "1.0");
    properties.loadProperties(props);

    RandomChaosAttacks r =
        new RandomChaosAttacks(
            consoleLogReporter,
            properties,
            exceptionAttack,
            latencyAttack,
            jvmcrashAttack,
            errorAttack,
            Clock.system(ZoneId.systemDefault()));

    r.jvmCrash();
    verify(consoleLogReporter).reportAttack(ChaosAttack.JVMCRASH);
    verifyNoInteractions(exceptionAttack);
    verifyNoInteractions(latencyAttack);
    verify(jvmcrashAttack).attack(properties);
    verifyNoInteractions(errorAttack);
  }

  @Test
  public void jvmCrashAttackNotEnabledTest() {

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
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
            jvmcrashAttack,
            errorAttack,
            Clock.system(ZoneId.systemDefault()));

    r.jvmCrash();
    verifyNoInteractions(consoleLogReporter);
    verifyNoInteractions(exceptionAttack);
    verifyNoInteractions(latencyAttack);
    verifyNoInteractions(jvmcrashAttack);
    verifyNoInteractions(errorAttack);
  }

  @Test
  public void errorAttackEnabledTest() {

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
    Map<String, String> props = new HashMap<>();

    props.put(SimpleChaosConstants.ENABLED, "true");
    props.put(
        SimpleChaosConstants.ENABLED_HOURS,
        "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23");
    props.put(
        SimpleChaosConstants.ENABLED_DAYS,
        "MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY");
    props.put(SimpleChaosConstants.ERROR_ATTACK_ENABLED, "true");
    props.put(SimpleChaosConstants.ERROR_ATTACK_CHANCE, "1.0");
    properties.loadProperties(props);

    RandomChaosAttacks r =
        new RandomChaosAttacks(
            consoleLogReporter,
            properties,
            exceptionAttack,
            latencyAttack,
            jvmcrashAttack,
            errorAttack,
            Clock.system(ZoneId.systemDefault()));

    r.error();
    verify(consoleLogReporter).reportAttack(ChaosAttack.ERROR);
    verifyNoInteractions(exceptionAttack);
    verifyNoInteractions(latencyAttack);
    verifyNoInteractions(jvmcrashAttack);
    verify(errorAttack).attack(properties);
    ;
  }

  @Test
  public void errorAttackNotEnabledTest() {

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
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
            jvmcrashAttack,
            errorAttack,
            Clock.system(ZoneId.systemDefault()));

    r.error();
    verifyNoInteractions(consoleLogReporter);
    verifyNoInteractions(exceptionAttack);
    verifyNoInteractions(latencyAttack);
    verifyNoInteractions(jvmcrashAttack);
    verifyNoInteractions(errorAttack);
  }
}
