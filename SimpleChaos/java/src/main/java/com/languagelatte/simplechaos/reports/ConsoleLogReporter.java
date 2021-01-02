package com.languagelatte.simplechaos.reports;

import com.languagelatte.simplechaos.attacks.ChaosAttack;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleLogReporter implements Reporter {

  private final Map<ChaosAttack, Integer> totalAttackCount = new HashMap<>();
  private Long startTimeMillis = System.currentTimeMillis();
  private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleLogReporter.class);

  @Override
  public void reportAttack(ChaosAttack chaosAttack, Boolean wasRunSucessfully) {

    if (totalAttackCount.get(chaosAttack) == null) {
      totalAttackCount.put(chaosAttack, 1);
    } else {
      totalAttackCount.put(chaosAttack, totalAttackCount.get(chaosAttack) + 1);
    }

    // If more than 1 day running, then publish report
    if (System.currentTimeMillis() - startTimeMillis > 86_400_000) {
      publishReport();
    }
  }

  @Override
  public void publishReport() {

    Long currentTimeMillis = System.currentTimeMillis();
    LocalDateTime startTime = LocalDateTime.ofEpochSecond(startTimeMillis, 0, ZoneOffset.UTC);
    LocalDateTime currentTime = LocalDateTime.ofEpochSecond(currentTimeMillis, 0, ZoneOffset.UTC);

    // log total attacks between start time and current time.
    LOGGER.info("Attack Report for " + startTime.toString() + " to " + currentTime.toString());
    LOGGER.info("Total attacks = " + totalAttackCount.size());

    for (Entry<ChaosAttack, Integer> attack : totalAttackCount.entrySet()) {
      LOGGER.info(
          "Total Attacks - Attack Type: " + attack.getKey() + " Count: " + attack.getValue());
    }

    startTimeMillis = System.currentTimeMillis();
    totalAttackCount.clear();
  }
}
