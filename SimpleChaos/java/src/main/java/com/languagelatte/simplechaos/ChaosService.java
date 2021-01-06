package com.languagelatte.simplechaos;

import com.languagelatte.simplechaos.attacks.ChaosAttacks;
import com.languagelatte.simplechaos.attacks.RandomChaosAttacks;
import com.languagelatte.simplechaos.attacks.attack.ErrorAttack;
import com.languagelatte.simplechaos.attacks.attack.ExceptionAttack;
import com.languagelatte.simplechaos.attacks.attack.JvmCrashAttack;
import com.languagelatte.simplechaos.attacks.attack.LatencyAttack;
import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.properties.ChaosPropertiesDefaultImpl;
import com.languagelatte.simplechaos.reports.ConsoleLogReporter;
import com.languagelatte.simplechaos.reports.Reporter;

public class ChaosService implements ChaosServiceInterface {

  private final ChaosProperties chaosProperties;
  private final Reporter reporter;
  private final ChaosAttacks chaosAttacks;

  public ChaosService() {
    this.chaosProperties = new ChaosPropertiesDefaultImpl();
    this.chaosProperties.loadProperties();
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

  public ChaosService(ChaosProperties chaosProperties) {

    this.chaosProperties = chaosProperties;
    this.chaosProperties.loadProperties();
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
  public void loadProperties() {
    chaosProperties.loadProperties();
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
}
