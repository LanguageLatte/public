package com.languagelatte.simplechaos.properties;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

import com.languagelatte.simplechaos.attacks.ChaosAttack;

public interface ChaosProperties {

  // Load from system properties
  public void loadProperties();

  // public void loadProperties(Map<String, String> properties);

  // public void loadProperties(Properties properties);

  public Boolean getBooleanProperty(String key);

  public Double getDoubleProperty(String key);

  public Integer getIntProperty(String key);

  public String getStringProperty(String key);

  default Boolean isTodayEnabled(){
    LocalDateTime ldt = LocalDateTime.now(ZoneId.systemDefault());
    DayOfWeek today = ldt.getDayOfWeek();
    List<String> days = Arrays.asList(getStringProperty(SimpleChaosConstants.ENABLED_DAYS).split(","));
    return days.contains(today.toString());
  }

  default Boolean isThisHourEnabled(){
    LocalDateTime ldt = LocalDateTime.now(ZoneId.systemDefault());
    Integer hour = ldt.getHour();
    List<String> hours = Arrays.asList(getStringProperty(SimpleChaosConstants.ENABLED_HOURS).split(","));
    return hours.contains(hour.toString());
  }

  default Boolean isAttackEnabled(ChaosAttack attack){
    return getBooleanProperty(attack.getAttackEnabledKey());
  }

  default Boolean shouldAttackRun(ChaosAttack attack){
    if (!getBooleanProperty(SimpleChaosConstants.ENABLED)) {
      return false;
    }

    if (!isTodayEnabled() || !isThisHourEnabled()) {
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

  default Boolean isValidPropertyValue(String key, String value) {

    if (key == null || value == null) {
      return false;
    }

    if (!SimpleChaosConstants.propertyNames.contains(key)) {
      return false;
    }
    return true;
  }
}
