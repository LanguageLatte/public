package com.languagelatte.simplechaos.spring;

import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.properties.SimpleChaosConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ChaosSpringPropertiesImpl implements ChaosProperties {

  private final Environment environment;
  private final Map<String, String> properties = new HashMap<>();

  public ChaosSpringPropertiesImpl(Environment environment) {
    this.environment = environment;
  }

  @Override
  public Boolean getBooleanProperty(String key) {
    return isValidPropertyValue(key, environment.getProperty(key))
        ? Boolean.valueOf(environment.getProperty(key))
        : Boolean.valueOf(properties.get(key));
  }

  @Override
  public Double getDoubleProperty(String key) {
    return isValidPropertyValue(key, environment.getProperty(key))
        ? Double.valueOf(environment.getProperty(key))
        : Double.valueOf(properties.get(key));
  }

  @Override
  public void loadProperties() {
    // Load From system variables.
    // If not existing, then load default values
    for (Entry<String, String> entry : SimpleChaosConstants.defaultValueMap.entrySet()) {

      String systemValue = System.getProperty(entry.getKey());

      if (isValidPropertyValue(entry.getKey(), systemValue)) {
        this.properties.put(entry.getKey(), systemValue);
      } else {
        this.properties.put(entry.getKey(), entry.getValue());
      }
    }
  }

  @Override
  public Integer getIntProperty(String key) {
    return isValidPropertyValue(key, environment.getProperty(key))
        ? Integer.valueOf(environment.getProperty(key))
        : Integer.valueOf(properties.get(key));
  }

  @Override
  public String getStringProperty(String key) {
    return isValidPropertyValue(key, environment.getProperty(key))
        ? String.valueOf(environment.getProperty(key))
        : String.valueOf(properties.get(key));
  }
}
