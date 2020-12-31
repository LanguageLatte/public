package com.languagelatte.simplechaos;

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

  public static final String RANDOM_CHAOS_LATENCY_ENABLED =
      "com.languagelatte.simplechaos.randomchaos.latency.enabled";
  public static final String RANDOM_CHAOS_LATENCY_ENABLED_DEFAULT_VALUE = "false";

  public static final String RANDOM_CHAOS_LATENCY_CHANCE =
      "com.languagelatte.simplechaos.randomchaos.latency.chance";
  public static final String RANDOM_CHAOS_LATENCY_CHANCE_DEFAULT_VALUE = "0.0";

  public static final String RANDOM_CHAOS_LATENCY_MINTIME =
      "com.languagelatte.simplechaos.randomchaos.latency.mintime";
  public static final String RANDOM_CHAOS_LATENCY_MINTIME_DEFAULT_VALUE = "1000";

  public static final String RANDOM_CHAOS_LATENCY_MAXTIME =
      "com.languagelatte.simplechaos.randomchaos.latency.maxtime";
  public static final String RANDOM_CHAOS_LATENCY_MAXTIME_DEFAULT_VALUE = "25000";

  public static final String RANDOM_CHAOS_ERROR_ENABLED =
      "com.languagelatte.simplechaos.randomchaos.error.enabled";
  public static final String RANDOM_CHAOS_ERROR_ENABLED_DEFAULT_VALUE = "false";

  public static final String RANDOM_CHAOS_ERROR_CHANCE =
      "com.languagelatte.simplechaos.randomchaos.error.chance";
  public static final String RANDOM_CHAOS_ERROR_CHANCE_DEFAULT_VALUE = "0.0";

  public static final String RANDOM_CHAOS_JVMCRASH_ENABLED =
      "com.languagelatte.simplechaos.randomchaos.jvmcrash.enabled";
  public static final String RANDOM_CHAOS_JVMCRASH_ENABLED_DEFAULT_VALUE = "false";

  public static final String RANDOM_CHAOS_JVMCRASH_CHANCE =
      "com.languagelatte.simplechaos.randomchaos.jvmcrash.chance";
  public static final String RANDOM_CHAOS_JVMCRASH_CHANCE_DEFAULT_VALUE = "0.0";

  static {
    defaultValueMap = new HashMap<>();
    propertyNames = new HashSet<>();

    propertyNames.add(ENABLED);
    propertyNames.add(RANDOM_CHAOS_EXCEPTION_ENABLED);
    propertyNames.add(RANDOM_CHAOS_EXCEPTION_CHANCE);
    propertyNames.add(RANDOM_CHAOS_LATENCY_ENABLED);
    propertyNames.add(RANDOM_CHAOS_LATENCY_CHANCE);
    propertyNames.add(RANDOM_CHAOS_LATENCY_MINTIME);
    propertyNames.add(RANDOM_CHAOS_LATENCY_MAXTIME);
    propertyNames.add(RANDOM_CHAOS_ERROR_ENABLED);
    propertyNames.add(RANDOM_CHAOS_ERROR_CHANCE);
    propertyNames.add(RANDOM_CHAOS_JVMCRASH_ENABLED);
    propertyNames.add(RANDOM_CHAOS_JVMCRASH_CHANCE);

    defaultValueMap.put(ENABLED, ENABLED_DEFAULT_VALUE);
    defaultValueMap.put(
        RANDOM_CHAOS_EXCEPTION_ENABLED, RANDOM_CHAOS_EXCEPTION_ENABLED_DEFAULT_VALUE);
    defaultValueMap.put(RANDOM_CHAOS_EXCEPTION_CHANCE, RANDOM_CHAOS_EXCEPTION_CHANCE_DEFAULT_VALUE);
    defaultValueMap.put(RANDOM_CHAOS_LATENCY_ENABLED, RANDOM_CHAOS_LATENCY_ENABLED_DEFAULT_VALUE);
    defaultValueMap.put(RANDOM_CHAOS_LATENCY_CHANCE, RANDOM_CHAOS_LATENCY_CHANCE_DEFAULT_VALUE);
    defaultValueMap.put(RANDOM_CHAOS_LATENCY_MINTIME, RANDOM_CHAOS_LATENCY_MINTIME_DEFAULT_VALUE);
    defaultValueMap.put(RANDOM_CHAOS_LATENCY_MAXTIME, RANDOM_CHAOS_LATENCY_MAXTIME_DEFAULT_VALUE);
    defaultValueMap.put(RANDOM_CHAOS_ERROR_ENABLED, RANDOM_CHAOS_ERROR_ENABLED_DEFAULT_VALUE);
    defaultValueMap.put(RANDOM_CHAOS_ERROR_CHANCE, RANDOM_CHAOS_ERROR_CHANCE_DEFAULT_VALUE);
    defaultValueMap.put(RANDOM_CHAOS_JVMCRASH_ENABLED, RANDOM_CHAOS_JVMCRASH_ENABLED_DEFAULT_VALUE);
    defaultValueMap.put(RANDOM_CHAOS_JVMCRASH_CHANCE, RANDOM_CHAOS_JVMCRASH_CHANCE_DEFAULT_VALUE);
  }
}
