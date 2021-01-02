package com.languagelatte.simplechaos.attacks.attack;

import com.languagelatte.simplechaos.properties.ChaosProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionAttack implements Attack {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAttack.class);

  @Override
  public void attack(ChaosProperties properties) {
    LOGGER.info("Starting Exception Attack");
    throw new RuntimeException("Chaos Attack Exception");
  }
}
