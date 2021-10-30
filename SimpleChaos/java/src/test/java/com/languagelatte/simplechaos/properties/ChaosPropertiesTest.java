package com.languagelatte.simplechaos.properties;


import com.languagelatte.simplechaos.attacks.ChaosAttack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChaosPropertiesTest {

  @BeforeEach
  public void beforeEach(){
    ChaosProperties.INSTANCE.clearProperties();
  }

  @Test
  public void isTodayEnabledTest() {

    // Clock clock = mock(Clock.class);
    // This was a friday
    LocalDate friday = LocalDate.of(2021, 1, 1);
    LocalDate saturday = LocalDate.of(2021, 1, 2);

    Clock fridayClock =
        Clock.fixed(
            friday.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

    Clock saturdayClock =
        Clock.fixed(
            saturday.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

    ChaosProperties properties = ChaosProperties.INSTANCE;

    assertTrue(properties.isTodayEnabled(fridayClock));
    assertFalse(properties.isTodayEnabled(saturdayClock));

    Map<String, String> props = new HashMap<>();
    props.put(
        SimpleChaosConstants.ENABLED_DAYS,
        LocalDate.now(ZoneId.systemDefault()).getDayOfWeek().toString());
    properties.loadProperties(props);
    assertTrue(properties.isTodayEnabled(Clock.system(ZoneId.systemDefault())));
  }

  @Test
  public void isThisHourEnabledTest() {

    // Clock clock = mock(Clock.class);
    // This was a friday
    LocalDateTime friday9am = LocalDateTime.of(LocalDate.of(2021, 1, 1), LocalTime.of(9, 0));

    Clock fridayClock =
        Clock.fixed(
            friday9am.toInstant(OffsetDateTime.now(ZoneId.systemDefault()).getOffset()),
            ZoneId.systemDefault());

    ChaosProperties properties = ChaosProperties.INSTANCE;

    assertTrue(properties.isThisHourEnabled(fridayClock));

    Map<String, String> props = new HashMap<>();
    props.put(
        SimpleChaosConstants.ENABLED_HOURS,
        String.valueOf(LocalTime.now(ZoneId.systemDefault()).getHour()));
    properties.loadProperties(props);
    assertTrue(properties.isThisHourEnabled(Clock.system(ZoneId.systemDefault())));
  }

  @Test
  public void isAttackEnabledTest() {
    ChaosProperties properties = ChaosProperties.INSTANCE;

    Map<String, String> props = new HashMap<>();
    props.put(SimpleChaosConstants.LATENCY_ATTACK_ENABLED, "true");

    assertFalse(properties.isAttackEnabled(ChaosAttack.LATENCY));
    properties.loadProperties(props);
    assertTrue(properties.isAttackEnabled(ChaosAttack.LATENCY));
  }

  @Test
  public void loadPropertiesMapTest() {
    Map<String, String> props = new HashMap<>();
    props.put("key", "value");

    ChaosProperties properties = ChaosProperties.INSTANCE;

    assertFalse(properties.isPropertyValuePresent("key"));
    properties.loadProperties(props);
    assertTrue(properties.isPropertyValuePresent("key"));
    assertEquals("value", properties.getStringProperty("key"));
  }

  @Test
  public void loadPropertiesPropertiesTest() {

    Properties props = new Properties();
    props.setProperty("key", "value");
    ChaosProperties properties = ChaosProperties.INSTANCE;

    assertFalse(properties.isPropertyValuePresent("key"));
    properties.loadProperties(props);
    assertTrue(properties.isPropertyValuePresent("key"));
    assertEquals("value", properties.getStringProperty("key"));
  }

  @Test
  public void getBooleanPropertyTest() {

    Map<String, String> props = new HashMap<>();
    props.put("exists", "true");
    ChaosProperties properties = ChaosProperties.INSTANCE;
    properties.loadProperties(props);

    assertEquals(false, properties.getBooleanProperty("does not exist"));
    assertEquals(true, properties.getBooleanProperty("exists"));
  }

  @Test
  public void getDoublePropertyTest() {

    Map<String, String> props = new HashMap<>();
    props.put("exists", "1.0");
    ChaosProperties properties = ChaosProperties.INSTANCE;
    properties.loadProperties(props);

    assertEquals(0.0, properties.getDoubleProperty("does not exist"));
    assertEquals(1.0, properties.getDoubleProperty("exists"));
  }

  @Test
  public void getIntPropertyTest() {

    Map<String, String> props = new HashMap<>();
    props.put("exists", "1");
    ChaosProperties properties = ChaosProperties.INSTANCE;
    properties.loadProperties(props);

    assertEquals(0, properties.getIntProperty("does not exist"));
    assertEquals(1, properties.getIntProperty("exists"));
  }

  @Test
  public void getStringPropertyTest() {

    Map<String, String> props = new HashMap<>();
    props.put("exists", "value");
    props.put("null", "null");
    ChaosProperties properties = ChaosProperties.INSTANCE;
    properties.loadProperties(props);

    assertEquals("", properties.getStringProperty("does not exist"));
    assertEquals("", properties.getStringProperty("null"));
    assertEquals("value", properties.getStringProperty("exists"));
  }
}
