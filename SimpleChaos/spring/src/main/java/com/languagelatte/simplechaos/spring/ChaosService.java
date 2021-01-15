package com.languagelatte.simplechaos.spring;

import com.languagelatte.simplechaos.attacks.ChaosAttacks;
import com.languagelatte.simplechaos.attacks.RandomChaosAttacks;
import com.languagelatte.simplechaos.attacks.attack.ErrorAttack;
import com.languagelatte.simplechaos.attacks.attack.ExceptionAttack;
import com.languagelatte.simplechaos.attacks.attack.JvmCrashAttack;
import com.languagelatte.simplechaos.attacks.attack.LatencyAttack;
import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.properties.SimpleChaosConstants;
import com.languagelatte.simplechaos.reports.ConsoleLogReporter;
import com.languagelatte.simplechaos.reports.Reporter;
import java.lang.reflect.Constructor;
import java.time.Clock;
import java.time.ZoneId;
import org.springframework.stereotype.Service;

@Service
public class ChaosService implements ChaosAttacks {

  private final ChaosAttacks chaosAttacks;

  public ChaosService(ChaosProperties chaosProperties) {

    String reporterImplClassName =
        chaosProperties.getStringProperty(SimpleChaosConstants.REPORTER_CLASS);
    String chaosAttacksImplClassName =
        chaosProperties.getStringProperty(SimpleChaosConstants.ATTACKER_CLASS);
    Reporter reporter;
    ChaosAttacks chaosAttacks;

    // Try to instiantiate reporter
    try {
      Class<?> reporterClass = Class.forName(reporterImplClassName);
      Constructor<?> constructor = reporterClass.getConstructor();
      reporter = (Reporter) constructor.newInstance();
    } catch (Exception e) {
      reporter = new ConsoleLogReporter();
    }

    // Try to instiantiate chaosAttacks
    try {
      Class<?> chaosAttacksClass = Class.forName(chaosAttacksImplClassName);
      Constructor<?> constructor =
          chaosAttacksClass.getConstructor(
              Reporter.class,
              ChaosProperties.class,
              ExceptionAttack.class,
              LatencyAttack.class,
              JvmCrashAttack.class,
              ErrorAttack.class,
              Clock.class);
      chaosAttacks =
          (ChaosAttacks)
              constructor.newInstance(
                  reporter,
                  chaosProperties,
                  new ExceptionAttack(),
                  new LatencyAttack(),
                  new JvmCrashAttack(),
                  new ErrorAttack(),
                  Clock.system(ZoneId.systemDefault()));
    } catch (Exception e) {

      chaosAttacks =
          new RandomChaosAttacks(
              reporter,
              chaosProperties,
              new ExceptionAttack(),
              new LatencyAttack(),
              new JvmCrashAttack(),
              new ErrorAttack(),
              Clock.system(ZoneId.systemDefault()));
    }

    this.chaosAttacks = chaosAttacks;
  }

  @Override
  public void exception() {
    chaosAttacks.exception();
  }

  @Override
  public void error() {
    chaosAttacks.error();
  }

  @Override
  public void jvmCrash() {
    chaosAttacks.jvmCrash();
  }

  @Override
  public void latency() {
    chaosAttacks.latency();
  }
}
