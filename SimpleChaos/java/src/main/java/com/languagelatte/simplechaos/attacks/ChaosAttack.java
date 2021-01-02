package com.languagelatte.simplechaos.attacks;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ChaosAttack {
  EXCEPTION,
  ERROR,
  JVMCRASH,
  LATENCY;

  private static final List<ChaosAttack> ATTACK_VALUES =
      Collections.unmodifiableList(Arrays.asList(ChaosAttack.values()));
  private static final int SIZE = ATTACK_VALUES.size();
  private static final Random RANDOM = new Random();

  public static ChaosAttack getRandomAttack() {
    return ATTACK_VALUES.get(RANDOM.nextInt(SIZE));
  }
}
