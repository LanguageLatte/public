package com.languagelatte.simplechaos;

import com.languagelatte.simplechaos.attacks.Attack;
import com.languagelatte.simplechaos.attacks.ErrorAttack;
import com.languagelatte.simplechaos.attacks.ExceptionAttack;
import com.languagelatte.simplechaos.attacks.JvmCrashAttack;
import com.languagelatte.simplechaos.attacks.LatencyAttack;
import com.languagelatte.simplechaos.reports.ConsoleLogReporter;
import com.languagelatte.simplechaos.reports.Reporter;

public class RandomChaosAttacks implements ChaosAttacks {

  // 1. Is Simple Chaos Enabled?
  // 2. Is Random Chaos Enabled?
  // 3. Is correct Day of week, and Hour
  // 4. Is the specific method Enabled?
  // 5. Id the random number <= than the chance?

  Reporter reporter;
  ChaosProperties properties;
  Attack exception;
  Attack latency;
  Attack jvmCrash;
  Attack error;

  public RandomChaosAttacks() {
    this.exception = new ExceptionAttack();
    this.error = new ErrorAttack();
    this.latency = new LatencyAttack();
    this.jvmCrash = new JvmCrashAttack();
    this.properties = new ChaosPropertiesDefaultImpl();
    this.properties.loadProperties();
    this.reporter = new ConsoleLogReporter();
  }

  public RandomChaosAttacks(ChaosProperties chaosProperties) {
    this.exception = new ExceptionAttack();
    this.error = new ErrorAttack();
    this.latency = new LatencyAttack();
    this.jvmCrash = new JvmCrashAttack();
    this.properties = chaosProperties;
    this.properties.loadProperties();
    this.reporter = new ConsoleLogReporter();
  }

  @Override
  public void exception() {
    if (!properties.getBooleanProperty(SimpleChaosConstants.ENABLED)) {
      return;
    }

    if (!properties.getBooleanProperty(SimpleChaosConstants.RANDOM_CHAOS_EXCEPTION_ENABLED)) {
      return;
    }
    if (Math.random()
        > 1 - properties.getDoubleProperty(SimpleChaosConstants.RANDOM_CHAOS_EXCEPTION_CHANCE)) {
      reporter.reportAttack(ChaosAttack.EXCEPTION, true);
      exception.attack(properties);
    }
  }

  @Override
  public void error() {
    if (!properties.getBooleanProperty(SimpleChaosConstants.ENABLED)) {
      return;
    }

    if (!properties.getBooleanProperty(SimpleChaosConstants.RANDOM_CHAOS_ERROR_ENABLED)) {
      return;
    }
    if (Math.random()
        > 1 - properties.getDoubleProperty(SimpleChaosConstants.RANDOM_CHAOS_ERROR_CHANCE)) {
      reporter.reportAttack(ChaosAttack.ERROR, true);
      exception.attack(properties);
    }
  }

  @Override
  public void jvmCrash() {
    if (!properties.getBooleanProperty(SimpleChaosConstants.ENABLED)) {
      return;
    }

    if (!properties.getBooleanProperty(SimpleChaosConstants.RANDOM_CHAOS_JVMCRASH_ENABLED)) {
      return;
    }
    if (Math.random()
        > 1 - properties.getDoubleProperty(SimpleChaosConstants.RANDOM_CHAOS_JVMCRASH_CHANCE)) {
      reporter.reportAttack(ChaosAttack.JVMCRASH, true);
      exception.attack(properties);
    }
  }

  @Override
  public void latency() {
    if (!properties.getBooleanProperty(SimpleChaosConstants.ENABLED)) {
      return;
    }

    if (!properties.getBooleanProperty(SimpleChaosConstants.RANDOM_CHAOS_LATENCY_ENABLED)) {
      return;
    }
    if (Math.random()
        > 1 - properties.getDoubleProperty(SimpleChaosConstants.RANDOM_CHAOS_LATENCY_CHANCE)) {
      reporter.reportAttack(ChaosAttack.LATENCY, true);
      exception.attack(properties);
    }
  }
}
