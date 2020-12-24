package com.languagelatte.simplechaos_java;

import com.languagelatte.simplechaos_java.implementations.ChaosAttack;
import com.languagelatte.simplechaos_java.implementations.DefaultChaosException;
import java.util.Map;
import java.util.Properties;

public class RandomChaosAttacks implements ChaosAttacks {

  // 1. Is Simple Chaos Enabled?
  // 2. Is Random Chaos Enabled?
  // 3. Is the specific method Enabled?
  // 4. Id the random number <= than the chance?

  ChaosProperties properties;
  ChaosAttack exception;

  public RandomChaosAttacks() {
    this.exception = new DefaultChaosException();
    this.properties = new ChaosPropertiesDefaultImpl();
    this.properties.loadProperties();
  }

  public RandomChaosAttacks(ChaosProperties chaosProperties) {
    this.exception = new DefaultChaosException();
    this.properties = chaosProperties;
    this.properties.loadProperties();
  }

  public RandomChaosAttacks(Map<String, String> properties) {
    this.exception = new DefaultChaosException();
    this.properties = new ChaosPropertiesDefaultImpl();
    this.properties.loadProperties(properties);
  }

  public RandomChaosAttacks(Properties properties) {
    this.exception = new DefaultChaosException();
    this.properties = new ChaosPropertiesDefaultImpl();
    this.properties.loadProperties(properties);
  }

  public RandomChaosAttacks(ChaosAttack exception) {
    this.exception = exception;
  }

  @Override
  public void exception() {
    // 1. Is Simple Chaos Enabled?
    // 2. Is Random Chaos Enabled?
    // 3. Is correct Day of week, and Hour
    // 4. Is the specific method Enabled?
    // 5. Id the random number <= than the chance?

    if (!properties.getBooleanProperty(SimpleChaosConstants.ENABLED)) {
      return;
    }

    if (!properties.getBooleanProperty(SimpleChaosConstants.RANDOM_CHAOS_EXCEPTION_ENABLED)) {
      return;
    }
    if (Math.random()
        > 1 - properties.getDoubleProperty(SimpleChaosConstants.RANDOM_CHAOS_EXCEPTION_CHANCE)) {
      exception.execute();
    }
  }
}
