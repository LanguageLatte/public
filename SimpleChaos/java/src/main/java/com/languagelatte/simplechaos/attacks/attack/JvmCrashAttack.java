package com.languagelatte.simplechaos.attacks.attack;

import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.properties.SimpleChaosConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JvmCrashAttack implements Attack {

  private static final Logger LOGGER = LoggerFactory.getLogger(JvmCrashAttack.class);

  @Override
  public void attack(ChaosProperties properties) {

    try {

      if ("RUNTIMEHALT"
          .equals(properties.getStringProperty(SimpleChaosConstants.JVMCRASH_ATTACK_MODE))) {
        LOGGER.info("Starting JvmCrash Attack - using runtime halt ");
        Runtime.getRuntime().halt(1);
      } else {
        LOGGER.info("Starting JvmCrash Attack - using system exit");
        System.exit(1);
      }
    } catch (Exception e) {
      LOGGER.info(
          "JvmCrash Attack failed to run. Security manager likely blocked 'System.exit(1)' call");
    }
  }
}
