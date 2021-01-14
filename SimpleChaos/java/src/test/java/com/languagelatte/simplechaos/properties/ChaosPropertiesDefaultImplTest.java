package com.languagelatte.simplechaos.properties;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.junit.jupiter.api.Test;

public class ChaosPropertiesDefaultImplTest {

  @Test
  public void loadPropertiesMapTest() {
    Map<String, String> props = new HashMap<>();
    props.put("key", "value");

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();

    assertFalse(properties.isPropertyValuePresent("key"));
    properties.loadProperties(props);
    assertTrue(properties.isPropertyValuePresent("key"));
    assertEquals("value", properties.getStringProperty("key"));
  }

  @Test
  public void loadPropertiesPropertiesTest() {

    Properties props = new Properties();
    props.setProperty("key", "value");
    ChaosProperties properties = new ChaosPropertiesDefaultImpl();

    assertFalse(properties.isPropertyValuePresent("key"));
    properties.loadProperties(props);
    assertTrue(properties.isPropertyValuePresent("key"));
    assertEquals("value", properties.getStringProperty("key"));
  }

  @Test
  public void getBooleanPropertyTest() {

    Map<String, String> props = new HashMap<>();
    props.put("exists", "true");
    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
    properties.loadProperties(props);

    assertEquals(false, properties.getBooleanProperty("does not exist"));
    assertEquals(true, properties.getBooleanProperty("exists"));
  }

  @Test
  public void getDoublePropertyTest() {

    Map<String, String> props = new HashMap<>();
    props.put("exists", "1.0");
    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
    properties.loadProperties(props);

    assertEquals(0.0, properties.getDoubleProperty("does not exist"));
    assertEquals(1.0, properties.getDoubleProperty("exists"));
  }

  @Test
  public void getIntPropertyTest() {

    Map<String, String> props = new HashMap<>();
    props.put("exists", "1");
    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
    properties.loadProperties(props);

    assertEquals(0, properties.getIntProperty("does not exist"));
    assertEquals(1, properties.getIntProperty("exists"));
  }

  @Test
  public void getStringPropertyTest() {

    Map<String, String> props = new HashMap<>();
    props.put("exists", "value");
    props.put("null", "null");
    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
    properties.loadProperties(props);

    assertEquals("", properties.getStringProperty("does not exist"));
    assertEquals("", properties.getStringProperty("null"));
    assertEquals("value", properties.getStringProperty("exists"));
  }
}
