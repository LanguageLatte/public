package com.languagelatte.simplechaos;

import com.languagelatte.simplechaos.attacks.ChaosAttacks;
import com.languagelatte.simplechaos.attacks.RandomChaosAttacks;
import com.languagelatte.simplechaos.attacks.attack.ErrorAttack;
import com.languagelatte.simplechaos.attacks.attack.ExceptionAttack;
import com.languagelatte.simplechaos.attacks.attack.JvmCrashAttack;
import com.languagelatte.simplechaos.attacks.attack.LatencyAttack;
import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.reports.ConsoleLogReporter;
import com.languagelatte.simplechaos.reports.Reporter;
import java.util.Map;
import java.util.Properties;

public class ChaosService implements ChaosServiceInterface {

  private final ChaosProperties chaosProperties;
  private final Reporter reporter;
  private final ChaosAttacks chaosAttacks;

  public ChaosService(ChaosProperties chaosProperties) {

    this.chaosProperties = chaosProperties;
    this.reporter = new ConsoleLogReporter();
    this.chaosAttacks =
        new RandomChaosAttacks(
            reporter,
            chaosProperties,
            new ExceptionAttack(),
            new LatencyAttack(),
            new JvmCrashAttack(),
            new ErrorAttack());
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

  @Override
  public Boolean getBooleanProperty(String key) {
    return chaosProperties.getBooleanProperty(key);
  }

  @Override
  public Double getDoubleProperty(String key) {
    return chaosProperties.getDoubleProperty(key);
  }

  @Override
  public Integer getIntProperty(String key) {
    return chaosProperties.getIntProperty(key);
  }

  @Override
  public String getStringProperty(String key) {
    return chaosProperties.getStringProperty(key);
  }

  @Override
  public Boolean isTodayEnabled() {
    return chaosProperties.isTodayEnabled();
  }

  @Override
  public Boolean isThisHourEnabled() {
    return chaosProperties.isThisHourEnabled();
  }

  @Override
  public Boolean isPropertyValuePresent(String key) {
    return chaosProperties.isPropertyValuePresent(key);
  }

  @Override
  public void loadProperties(Map<String, String> properties) {
    chaosProperties.loadProperties(properties);
  }

  @Override
  public void loadProperties(Properties properties) {
    chaosProperties.loadProperties(properties);
  }
}
