package com.languagelatte.simplechaos.attacks;

import com.languagelatte.simplechaos.attacks.attack.Attack;
import com.languagelatte.simplechaos.attacks.attack.ErrorAttack;
import com.languagelatte.simplechaos.attacks.attack.ExceptionAttack;
import com.languagelatte.simplechaos.attacks.attack.JvmCrashAttack;
import com.languagelatte.simplechaos.attacks.attack.LatencyAttack;
import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.properties.SimpleChaosConstants;
import com.languagelatte.simplechaos.reports.Reporter;

public class RandomChaosAttacks implements ChaosAttacks {

  // 1. Is Simple Chaos Enabled?
  // 2. Is Random Chaos Enabled?
  // 3. Is correct Day of week, and Hour
  // 4. Is the specific method Enabled?
  // 5. Id the random number <= than the chance?

  private final Reporter reporter;
  private final ChaosProperties properties;
  private final Attack exception;
  private final Attack latency;
  private final Attack jvmCrash;
  private final Attack error;

  public RandomChaosAttacks(
      Reporter reporter,
      ChaosProperties properties,
      ExceptionAttack exception,
      LatencyAttack latency,
      JvmCrashAttack jvmCrash,
      ErrorAttack error) {

    this.reporter = reporter;
    this.properties = properties;

    this.exception = exception;
    this.error = error;
    this.latency = latency;
    this.jvmCrash = jvmCrash;

    this.properties.loadProperties();
  }

  @Override
  public void exception() {
    if (!properties.getBooleanProperty(SimpleChaosConstants.ENABLED)) {
      return;
    }

    if (!properties.getBooleanProperty(SimpleChaosConstants.EXCEPTION_ATTACK_ENABLED)) {
      return;
    }
    if (Math.random()
        > 1 - properties.getDoubleProperty(SimpleChaosConstants.EXCEPTION_ATTACK_CHANCE)) {
      reporter.reportAttack(ChaosAttack.EXCEPTION, true);
      exception.attack(properties);
    }
  }

  @Override
  public void error() {
    if (!properties.getBooleanProperty(SimpleChaosConstants.ENABLED)) {
      return;
    }

    if (!properties.getBooleanProperty(SimpleChaosConstants.ERROR_ATTACK_ENABLED)) {
      return;
    }
    if (Math.random()
        > 1 - properties.getDoubleProperty(SimpleChaosConstants.ERROR_ATTACK_CHANCE)) {
      reporter.reportAttack(ChaosAttack.ERROR, true);
      error.attack(properties);
    }
  }

  @Override
  public void jvmCrash() {
    if (!properties.getBooleanProperty(SimpleChaosConstants.ENABLED)) {
      return;
    }

    if (!properties.getBooleanProperty(SimpleChaosConstants.JVMCRASH_ATTACK_ENABLED)) {
      return;
    }
    if (Math.random()
        > 1 - properties.getDoubleProperty(SimpleChaosConstants.JVMCRASH_ATTACK_CHANCE)) {
      reporter.reportAttack(ChaosAttack.JVMCRASH, true);
      jvmCrash.attack(properties);
    }
  }

  @Override
  public void latency() {
    if (!properties.getBooleanProperty(SimpleChaosConstants.ENABLED)) {
      return;
    }

    if (!properties.getBooleanProperty(SimpleChaosConstants.LATENCY_ATTACK_ENABLED)) {
      return;
    }
    if (Math.random()
        > 1 - properties.getDoubleProperty(SimpleChaosConstants.LATENCY_ATTACK_CHANCE)) {
      reporter.reportAttack(ChaosAttack.LATENCY, true);
      latency.attack(properties);
    }
  }
}
