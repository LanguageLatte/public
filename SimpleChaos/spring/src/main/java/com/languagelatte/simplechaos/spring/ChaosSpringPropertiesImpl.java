package com.languagelatte.simplechaos.spring;

import com.languagelatte.simplechaos.properties.ChaosProperties;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ChaosSpringPropertiesImpl implements ChaosProperties {

  private Environment environment;
  // private final Map<String, String> properties = new HashMap<>();
  private static final Logger LOGGER = LoggerFactory.getLogger(ChaosSpringPropertiesImpl.class);

  public ChaosSpringPropertiesImpl(Environment environment) {
    this.environment = environment;
  }

  @Override
  public Boolean getBooleanProperty(String key) {
    return Boolean.valueOf(environment.getProperty(key));
  }

  @Override
  public Double getDoubleProperty(String key) {
    Double d = 0.0;

    try {
      d = Double.valueOf(environment.getProperty(key));
    } catch (Exception e) {
      LOGGER.trace("No defined value for " + key + "Defaulting to '0.0'");
    }
    return d;
  }

  @Override
  public Integer getIntProperty(String key) {
    Integer i = 0;

    try {
      i = Integer.valueOf(environment.getProperty(key));
    } catch (Exception e) {
      LOGGER.trace("No defined value for " + key + "Defaulting to '0'");
    }
    return i;
  }

  @Override
  public String getStringProperty(String key) {
    String s = String.valueOf(environment.getProperty(key));
    return "null".equals(s) ? "" : s;
  }

  @Override
  public Boolean isPropertyValuePresent(String key) {
    if (environment.getProperty(key) == null || "".equals(environment.getProperty(key).trim())) {
      return Boolean.FALSE;
    } else {
      return Boolean.TRUE;
    }
  }

  @Override
  @Deprecated
  public void loadProperties(Map<String, String> properties) {
    LOGGER.warn(
        "Do Not use this method. Use your Spring Enviornment for properties. No Implementation of this method loadProperties(Map<String, String> properties)");
  }

  @Override
  @Deprecated
  public void loadProperties(Properties properties) {
    LOGGER.warn(
        "Do Not use this method. Use your Spring Enviornment for properties. No Implementation of this method loadProperties(Properties properties)");
  }
}
