package com.languagelatte.simplechaos.attacks;

import com.languagelatte.simplechaos.properties.SimpleChaosConstants;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ChaosAttack {
  EXCEPTION(
      SimpleChaosConstants.EXCEPTION_ATTACK_ENABLED, SimpleChaosConstants.EXCEPTION_ATTACK_CHANCE),
  ERROR(SimpleChaosConstants.ERROR_ATTACK_ENABLED, SimpleChaosConstants.ERROR_ATTACK_CHANCE),
  JVMCRASH(
      SimpleChaosConstants.JVMCRASH_ATTACK_ENABLED, SimpleChaosConstants.JVMCRASH_ATTACK_CHANCE),
  LATENCY(SimpleChaosConstants.LATENCY_ATTACK_ENABLED, SimpleChaosConstants.LATENCY_ATTACK_CHANCE);

  private final String attackEnabledKey;
  private final String attackChanceKey;

  private ChaosAttack(String attackEnabledKey, String attackChanceKey) {
    this.attackEnabledKey = attackEnabledKey;
    this.attackChanceKey = attackChanceKey;
  }

  private static final List<ChaosAttack> ATTACK_VALUES =
      Collections.unmodifiableList(Arrays.asList(ChaosAttack.values()));
  private static final int SIZE = ATTACK_VALUES.size();
  private static final Random RANDOM = new Random();

  public static ChaosAttack getRandomAttack() {
    return ATTACK_VALUES.get(RANDOM.nextInt(SIZE));
  }

  public String getAttackEnabledKey() {
    return attackEnabledKey;
  }

  public String getAttackChanceKey() {
    return attackChanceKey;
  }
}
