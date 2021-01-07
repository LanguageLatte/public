package com.languagelatte.simplechaos.spring;

import com.languagelatte.simplechaos.properties.ChaosProperties;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ChaosSpringPropertiesImpl implements ChaosProperties {

  private Environment environment;
  private final Map<String, String> properties = new HashMap<>();
  // private static final Logger LOGGER =
  // LoggerFactory.getLogger(ChaosSpringPropertiesImpl.class);

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

  @Override
  public Boolean isPropertyValuePresent(String key) {

    if (environment.getProperty(key) == null && properties.get(key) == null) {
      return Boolean.FALSE;
    } else {
      return Boolean.TRUE;
    }
  }

  @Override
  public void loadProperties(Map<String, String> properties) {

    for (Map.Entry<String, String> e : properties.entrySet()) {
      if (isValidPropertyValue(e.getKey(), e.getValue())) {
        this.properties.put(e.getKey(), e.getValue());
      }
    }
  }

  @Override
  public void loadProperties(Properties properties) {

    for (Map.Entry<Object, Object> e : properties.entrySet()) {

      if (isValidPropertyValue(e.getKey().toString(), e.getValue().toString())) {
        this.properties.put(e.getKey().toString(), e.getValue().toString());
      }
    }
  }
}
