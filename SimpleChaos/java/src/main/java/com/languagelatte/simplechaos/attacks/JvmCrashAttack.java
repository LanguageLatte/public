package com.languagelatte.simplechaos.attacks;

import com.languagelatte.simplechaos.ChaosProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JvmCrashAttack implements Attack {

  private static final Logger LOGGER = LoggerFactory.getLogger(JvmCrashAttack.class);

  @Override
  public void attack(ChaosProperties properties) {

    try {
      LOGGER.info("Starting JvmCrash Attack");
      System.exit(1);
    } catch (Exception e) {
      LOGGER.info(
          "JvmCrash Attack failed to run. Security manager likley blocked 'System.exit(1)' call");
    }
  }
}
