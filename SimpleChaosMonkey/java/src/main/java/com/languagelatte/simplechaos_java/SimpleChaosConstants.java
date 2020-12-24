package com.languagelatte.simplechaos_java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimpleChaosConstants {

  public static final Map<String, String> defaultValueMap;
  public static final Set<String> propertyNames;

  public static final String ENABLED = "com.languagelatte.simplechaos.enabled";
  public static final String ENABLED_DEFAULT_VALUE = "false";

  public static final String RANDOM_CHAOS_EXCEPTION_ENABLED =
      "com.languagelatte.simplechaos.randomchaos.exception.enabled";
  public static final String RANDOM_CHAOS_EXCEPTION_ENABLED_DEFAULT_VALUE = "false";

  public static final String RANDOM_CHAOS_EXCEPTION_CHANCE =
      "com.languagelatte.simplechaos.randomchaos.exception.chance";
  public static final String RANDOM_CHAOS_EXCEPTION_CHANCE_DEFAULT_VALUE = "0.0";

  static {
    defaultValueMap = new HashMap<>();
    propertyNames = new HashSet<>();

    propertyNames.add(ENABLED);
    propertyNames.add(RANDOM_CHAOS_EXCEPTION_ENABLED);
    propertyNames.add(RANDOM_CHAOS_EXCEPTION_CHANCE);

    defaultValueMap.put(ENABLED, ENABLED_DEFAULT_VALUE);
    defaultValueMap.put(
        RANDOM_CHAOS_EXCEPTION_ENABLED, RANDOM_CHAOS_EXCEPTION_ENABLED_DEFAULT_VALUE);
    defaultValueMap.put(RANDOM_CHAOS_EXCEPTION_CHANCE, RANDOM_CHAOS_EXCEPTION_CHANCE_DEFAULT_VALUE);
  }
}
