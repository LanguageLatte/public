package com.languagelatte.simplechaos.properties;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.languagelatte.simplechaos.attacks.ChaosAttack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("ImmutableEnumChecker")
public enum ChaosProperties {

  INSTANCE;

  private final Map<String, String> properties = new HashMap<>();
  private static final Logger LOGGER = LoggerFactory.getLogger(ChaosProperties.class);

  public void clearProperties(){
    properties.clear();
  }

  public void loadProperties(Map<String, String> properties) {

    for (Map.Entry<String, String> e : properties.entrySet()) {

      this.properties.put(e.getKey(), e.getValue());
    }
  }


  public void loadProperties(Properties properties) {

    for (Map.Entry<Object, Object> e : properties.entrySet()) {

      this.properties.put(e.getKey().toString(), e.getValue().toString());
    }
  }


  public Boolean getBooleanProperty(String key) {
    return Boolean.valueOf(properties.get(key));
  }


  public Double getDoubleProperty(String key) {
    Double d = 0.0;

    try {
      d = Double.valueOf(properties.get(key));
    } catch (Exception e) {
      LOGGER.trace("No defined value for " + key + "Defaulting to '0.0'");
    }
    return d;
  }


  public Integer getIntProperty(String key) {
    Integer i = 0;

    try {
      i = Integer.valueOf(properties.get(key));
    } catch (Exception e) {
      LOGGER.trace("No defined value for " + key + "Defaulting to '0'");
    }
    return i;
  }


  public String getStringProperty(String key) {
    String s = String.valueOf(properties.get(key));
    return "null".equals(s) ? "" : s;
  }


  public Boolean isPropertyValuePresent(String key) {
    return properties.get(key) == null || "".equals(properties.get(key).trim())
        ? Boolean.FALSE
        : Boolean.TRUE;
  }


  public Boolean isTodayEnabled(Clock clock) {
    LocalDateTime ldt = LocalDateTime.now(clock.withZone(ZoneId.systemDefault()));
    DayOfWeek today = ldt.getDayOfWeek();
    List<String> days;

    if (isPropertyValuePresent(SimpleChaosConstants.ENABLED_DAYS)) {
      days = Arrays.asList(getStringProperty(SimpleChaosConstants.ENABLED_DAYS).split(","));
    } else {
      days = Arrays.asList("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY");
    }
    return days.contains(today.toString());
  }

  public Boolean isThisHourEnabled(Clock clock) {
    LocalDateTime ldt = LocalDateTime.now(clock.withZone(ZoneId.systemDefault()));
    Integer hour = ldt.getHour();
    List<String> hours;
    if (isPropertyValuePresent(SimpleChaosConstants.ENABLED_HOURS)) {
      hours = Arrays.asList(getStringProperty(SimpleChaosConstants.ENABLED_HOURS).split(","));
    } else {
      // Default 9am to 4pm (0900 - 1600)
      hours = Arrays.asList("9", "10", "11", "12", "13", "14", "15", "16");
    }
    return hours.contains(hour.toString());
  }

  public Boolean isAttackEnabled(ChaosAttack attack) {
    return getBooleanProperty(attack.getAttackEnabledKey());
  }

  public Boolean shouldAttackRun(ChaosAttack attack, Clock clock) {
    if (!getBooleanProperty(SimpleChaosConstants.ENABLED)) {
      return false;
    }

    if (!isTodayEnabled(clock) || !isThisHourEnabled(clock)) {
      return false;
    }

    if (!getBooleanProperty(attack.getAttackEnabledKey())) {
      return false;
    }

    if (!(Math.random() > 1 - getDoubleProperty(attack.getAttackChanceKey()))) {
      return false;
    }
    return true;
  }
}
