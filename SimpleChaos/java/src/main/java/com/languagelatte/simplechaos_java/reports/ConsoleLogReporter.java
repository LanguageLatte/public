package com.languagelatte.simplechaos_java.reports;

import com.languagelatte.simplechaos_java.ChaosAttack;
import java.util.HashMap;
import java.util.Map;

public class ConsoleLogReporter implements Reporter {

  Map<ChaosAttack, Integer> totalAttackCount;
  Map<ChaosAttack, Integer> successAttackCount;
  Map<ChaosAttack, Integer> failedAttackCount;
  Long startTimeMillis;

  public ConsoleLogReporter() {
    this.totalAttackCount = new HashMap<>();
    this.successAttackCount = new HashMap<>();
    this.failedAttackCount = new HashMap<>();
    startTimeMillis = System.currentTimeMillis();
  }

  @Override
  public void reportAttack(ChaosAttack chaosAttack, Boolean wasRunSucessfully) {

    if (totalAttackCount.get(chaosAttack) == null) {
      totalAttackCount.put(chaosAttack, 1);
    } else {
      totalAttackCount.put(chaosAttack, totalAttackCount.get(chaosAttack) + 1);
    }

    if (wasRunSucessfully) {

      if (successAttackCount.get(chaosAttack) == null) {
        successAttackCount.put(chaosAttack, 1);
      } else {
        successAttackCount.put(chaosAttack, successAttackCount.get(chaosAttack) + 1);
      }

    } else {

      if (failedAttackCount.get(chaosAttack) == null) {
        failedAttackCount.put(chaosAttack, 1);
      } else {
        failedAttackCount.put(chaosAttack, failedAttackCount.get(chaosAttack) + 1);
      }
    }
  }

  @Override
  public void publishReport() {

    // Long currentTimeMillis = System.currentTimeMillis();
    // LocalDateTime startTime = LocalDateTime.ofEpochSecond(startTimeMillis, 0, ZoneOffset.UTC);
    // LocalDateTime currentTime = LocalDateTime.ofEpochSecond(currentTimeMillis, 0,
    // ZoneOffset.UTC);

    // log total attacks between start time and current time.

    startTimeMillis = System.currentTimeMillis();
    totalAttackCount.clear();
    successAttackCount.clear();
    failedAttackCount.clear();
  }
}
