package com.languagelatte.simplechaos.properties;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChaosPropertiesDefaultImpl implements ChaosProperties {

  private final Map<String, String> properties = new HashMap<>();
  private static final Logger LOGGER = LoggerFactory.getLogger(ChaosPropertiesDefaultImpl.class);

  @Override
  public void loadProperties() {

    for (Map.Entry<String, String> entry : SimpleChaosConstants.defaultValueMap.entrySet()) {

      String systemValue = System.getProperty(entry.getKey());

      if (isValidPropertyValue(entry.getKey(), systemValue)) {
        this.properties.put(entry.getKey(), systemValue);
      } else {
        this.properties.put(entry.getKey(), entry.getValue());
      }
    }
  }

  // @Override
  // public void loadProperties(Map<String, String> properties) {
  // loadProperties();

  // for (Entry<String, String> e : properties.entrySet()) {
  // if (isValidPropertyValue(e.getKey(), e.getValue())) {
  // this.properties.put(e.getKey(), e.getValue());
  // }
  // }
  // }

  // @Override
  // public void loadProperties(Properties properties) {
  // loadProperties();

  // for (Entry<Object, Object> e : properties.entrySet()) {

  // if (isValidPropertyValue(e.getKey().toString(), e.getValue().toString())) {
  // this.properties.put(e.getKey().toString(), e.getValue().toString());
  // }
  // }
  // }

  @Override
  public Boolean getBooleanProperty(String key) {
    return Boolean.valueOf(properties.get(key));
  }

  @Override
  public Double getDoubleProperty(String key) {
    return Double.valueOf(properties.get(key));
  }

  @Override
  public Integer getIntProperty(String key) {
    return Integer.valueOf(properties.get(key));
  }

  @Override
  public String getStringProperty(String key) {
    return String.valueOf(properties.get(key));
  }
}
