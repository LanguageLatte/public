package com.languagelatte.simplechaos_java;

import java.util.Map;
import java.util.Properties;

import com.languagelatte.simplechaos_java.attacks.Attack;
import com.languagelatte.simplechaos_java.attacks.ExceptionAttack;

public class RandomChaosAttacks implements ChaosAttacks {

    // 1. Is Simple Chaos Enabled?
    // 2. Is Random Chaos Enabled?
    // 3. Is correct Day of week, and Hour
    // 4. Is the specific method Enabled?
    // 5. Id the random number <= than the chance?

  ChaosProperties properties;
  Attack exception;

  public RandomChaosAttacks() {
    this.exception = new ExceptionAttack();
    this.properties = new ChaosPropertiesDefaultImpl();
    this.properties.loadProperties();
  }

  public RandomChaosAttacks(ChaosProperties chaosProperties) {
    this.exception = new ExceptionAttack();
    this.properties = chaosProperties;
    this.properties.loadProperties();
  }

  public RandomChaosAttacks(Map<String, String> properties) {
    this.exception = new ExceptionAttack();
    this.properties = new ChaosPropertiesDefaultImpl();
    this.properties.loadProperties(properties);
  }

  public RandomChaosAttacks(Properties properties) {
    this.exception = new ExceptionAttack();
    this.properties = new ChaosPropertiesDefaultImpl();
    this.properties.loadProperties(properties);
  }

  public RandomChaosAttacks(Attack exception) {
    this.exception = exception;
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
      exception.attack();
    }
  }

  @Override
  public void error() {
    // TODO Auto-generated method stub

  }

  @Override
  public void jvmCrash() {
    // TODO Auto-generated method stub

  }

  @Override
  public void latency() {
    // TODO Auto-generated method stub

  }
}
