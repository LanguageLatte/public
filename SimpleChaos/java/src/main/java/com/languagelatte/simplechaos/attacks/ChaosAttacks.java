package com.languagelatte.simplechaos.attacks;

import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.properties.SimpleChaosConstants;

public interface ChaosAttacks {

  @SuppressWarnings("fallthrough")
  public default void randomAttack(ChaosProperties properties) {
    switch (ChaosAttack.getRandomAttack()) {
      case ERROR:
        if (properties.getBooleanProperty(SimpleChaosConstants.ERROR_ATTACK_ENABLED)) {
          error();
          break;
        }
      case EXCEPTION:
        if (properties.getBooleanProperty(SimpleChaosConstants.EXCEPTION_ATTACK_ENABLED)) {
          exception();
          break;
        }
      case JVMCRASH:
        if (properties.getBooleanProperty(SimpleChaosConstants.JVMCRASH_ATTACK_ENABLED)) {
          jvmCrash();
          break;
        }
      case LATENCY:
        if (properties.getBooleanProperty(SimpleChaosConstants.LATENCY_ATTACK_ENABLED)) {
          latency();
          break;
        }
    }
  }

  public void exception();

  public void error();

  public void jvmCrash();

  public void latency();
  // public void highCPU();
  // public void highMemory();
  // public void highDisk();
  // public void highNetwork();
  // public void abnormalLogs(LogLevel logLevel);
}
