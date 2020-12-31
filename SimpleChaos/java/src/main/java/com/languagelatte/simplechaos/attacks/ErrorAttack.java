package com.languagelatte.simplechaos.attacks;

import com.languagelatte.simplechaos.ChaosProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorAttack implements Attack {

  private static final Logger LOGGER = LoggerFactory.getLogger(ErrorAttack.class);

  @Override
  public void attack(ChaosProperties properties) {
    LOGGER.info("Starting Error Attack");
    throw new Error("Chaos Attack Error");
  }
}
