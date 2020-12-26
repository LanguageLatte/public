package com.languagelatte.simplechaos_java;

import java.util.Map;
import java.util.Properties;

public interface ChaosProperties {



  // Load from system properties
  public void loadProperties();

  public void loadProperties(Map<String, String> properties);

  public void loadProperties(Properties properties);

  public Boolean getBooleanProperty(String key);

  public Double getDoubleProperty(String key);

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
