package com.languagelatte.simplechaos.properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChaosPropertiesDefaultImpl implements ChaosProperties {

  private final Map<String, String> properties = new HashMap<>();
  private static final Logger LOGGER = LoggerFactory.getLogger(ChaosPropertiesDefaultImpl.class);

  @Override
  public void loadProperties(Map<String, String> properties) {

    for (Map.Entry<String, String> e : properties.entrySet()) {

      this.properties.put(e.getKey(), e.getValue());
    }
  }

  @Override
  public void loadProperties(Properties properties) {

    for (Map.Entry<Object, Object> e : properties.entrySet()) {

      this.properties.put(e.getKey().toString(), e.getValue().toString());
    }
  }

  @Override
  public Boolean getBooleanProperty(String key) {
    return Boolean.valueOf(properties.get(key));
  }

  @Override
  public Double getDoubleProperty(String key) {
    Double d = 0.0;

    try {
      d = Double.valueOf(properties.get(key));
    } catch (Exception e) {
      LOGGER.trace("No defined value for " + key + "Defaulting to '0.0'");
    }
    return d;
  }

  @Override
  public Integer getIntProperty(String key) {
    Integer i = 0;

    try {
      i = Integer.valueOf(properties.get(key));
    } catch (Exception e) {
      LOGGER.trace("No defined value for " + key + "Defaulting to '0'");
    }
    return i;
  }

  @Override
  public String getStringProperty(String key) {
    String s = String.valueOf(properties.get(key));
    return "null".equals(s) ? "" : s;
  }

  @Override
  public Boolean isPropertyValuePresent(String key) {
    return properties.get(key) == null || "".equals(properties.get(key).trim())
        ? Boolean.FALSE
        : Boolean.TRUE;
  }
}
