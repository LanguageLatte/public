package com.languagelatte.simplechaos.properties;

import com.languagelatte.simplechaos.attacks.ChaosAttack;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public interface ChaosProperties {

  public void loadProperties(Map<String, String> properties);

  public void loadProperties(Properties properties);

  public Boolean isPropertyValuePresent(String key);

  public Boolean getBooleanProperty(String key);

  public Double getDoubleProperty(String key);

  public Integer getIntProperty(String key);

  public String getStringProperty(String key);

  default Boolean isTodayEnabled(Clock clock) {
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

  default Boolean isThisHourEnabled(Clock clock) {
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

  default Boolean isAttackEnabled(ChaosAttack attack) {
    return getBooleanProperty(attack.getAttackEnabledKey());
  }

  default Boolean shouldAttackRun(ChaosAttack attack, Clock clock) {
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
