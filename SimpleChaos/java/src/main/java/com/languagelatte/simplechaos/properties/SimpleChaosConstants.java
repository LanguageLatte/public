package com.languagelatte.simplechaos.properties;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimpleChaosConstants {

  public static final Map<String, String> defaultValueMap;
  public static final Set<String> propertyNames;

  public static final String ENABLED = "com.languagelatte.simplechaos.attacks.enabled";
  public static final String ENABLED_DEFAULT_VALUE = "false";

  public static final String REPORTER_CLASS = "com.languagelatte.simplechaos.reporter";
  public static final String ATTACKER_CLASS = "com.languagelatte.simplechaos.attacker";

  public static final String EXCEPTION_ATTACK_ENABLED =
      "com.languagelatte.simplechaos.attacks.exception.enabled";
  public static final String EXCEPTION_ATTACK_ENABLED_DEFAULT_VALUE = "false";

  public static final String EXCEPTION_ATTACK_CHANCE =
      "com.languagelatte.simplechaos.attacks.exception.chance";
  public static final String EXCEPTION_ATTACK_CHANCE_DEFAULT_VALUE = "0.0";

  public static final String LATENCY_ATTACK_ENABLED =
      "com.languagelatte.simplechaos.attacks.latency.enabled";
  public static final String LATENCY_ATTACK_ENABLED_DEFAULT_VALUE = "false";

  public static final String LATENCY_ATTACK_CHANCE =
      "com.languagelatte.simplechaos.attacks.latency.chance";
  public static final String LATENCY_ATTACK_CHANCE_DEFAULT_VALUE = "0.0";

  public static final String LATENCY_ATTACK_MINTIME =
      "com.languagelatte.simplechaos.attacks.latency.mintime";
  public static final String LATENCY_ATTACK_MINTIME_DEFAULT_VALUE = "1000";

  public static final String LATENCY_ATTACK_MAXTIME =
      "com.languagelatte.simplechaos.attacks.latency.maxtime";
  public static final String RANDOM_CHAOS_LATENCY_MAXTIME_DEFAULT_VALUE = "25000";

  public static final String ERROR_ATTACK_ENABLED =
      "com.languagelatte.simplechaos.attacks.error.enabled";
  public static final String ERROR_ATTACK_ENABLED_DEFAULT_VALUE = "false";

  public static final String ERROR_ATTACK_CHANCE =
      "com.languagelatte.simplechaos.attacks.error.chance";
  public static final String ERROR_ATTACK_CHANCE_DEFAULT_VALUE = "0.0";

  public static final String JVMCRASH_ATTACK_ENABLED =
      "com.languagelatte.simplechaos.attacks.jvmcrash.enabled";
  public static final String JVMCRASH_ATTACK_ENABLED_DEFAULT_VALUE = "false";

  public static final String JVMCRASH_ATTACK_MODE =
      "com.languagelatte.simplechaos.attacks.jvmcrash.mode";
  public static final String JVMCRASH_ATTACK_MODE_DEFAULT_VALUE = "SYSTEMEXIT";

  public static final String JVMCRASH_ATTACK_CHANCE =
      "com.languagelatte.simplechaos.attacks.jvmcrash.chance";
  public static final String JVMCRASH_ATTACK_CHANCE_DEFAULT_VALUE = "0.0";

  static {
    defaultValueMap = new HashMap<>();
    propertyNames = new HashSet<>();

    propertyNames.add(ENABLED);
    propertyNames.add(EXCEPTION_ATTACK_ENABLED);
    propertyNames.add(EXCEPTION_ATTACK_CHANCE);
    propertyNames.add(LATENCY_ATTACK_ENABLED);
    propertyNames.add(LATENCY_ATTACK_CHANCE);
    propertyNames.add(LATENCY_ATTACK_MINTIME);
    propertyNames.add(LATENCY_ATTACK_MAXTIME);
    propertyNames.add(ERROR_ATTACK_ENABLED);
    propertyNames.add(ERROR_ATTACK_CHANCE);
    propertyNames.add(JVMCRASH_ATTACK_ENABLED);
    propertyNames.add(JVMCRASH_ATTACK_CHANCE);
    propertyNames.add(JVMCRASH_ATTACK_MODE);

    defaultValueMap.put(ENABLED, ENABLED_DEFAULT_VALUE);
    defaultValueMap.put(EXCEPTION_ATTACK_ENABLED, EXCEPTION_ATTACK_ENABLED_DEFAULT_VALUE);
    defaultValueMap.put(EXCEPTION_ATTACK_CHANCE, EXCEPTION_ATTACK_CHANCE_DEFAULT_VALUE);
    defaultValueMap.put(LATENCY_ATTACK_ENABLED, LATENCY_ATTACK_ENABLED_DEFAULT_VALUE);
    defaultValueMap.put(LATENCY_ATTACK_CHANCE, LATENCY_ATTACK_CHANCE_DEFAULT_VALUE);
    defaultValueMap.put(LATENCY_ATTACK_MINTIME, LATENCY_ATTACK_MINTIME_DEFAULT_VALUE);
    defaultValueMap.put(LATENCY_ATTACK_MAXTIME, RANDOM_CHAOS_LATENCY_MAXTIME_DEFAULT_VALUE);
    defaultValueMap.put(ERROR_ATTACK_ENABLED, ERROR_ATTACK_ENABLED_DEFAULT_VALUE);
    defaultValueMap.put(ERROR_ATTACK_CHANCE, ERROR_ATTACK_CHANCE_DEFAULT_VALUE);
    defaultValueMap.put(JVMCRASH_ATTACK_ENABLED, JVMCRASH_ATTACK_ENABLED_DEFAULT_VALUE);
    defaultValueMap.put(JVMCRASH_ATTACK_CHANCE, JVMCRASH_ATTACK_CHANCE_DEFAULT_VALUE);
    defaultValueMap.put(JVMCRASH_ATTACK_MODE, JVMCRASH_ATTACK_MODE_DEFAULT_VALUE);
  }
}
