package com.languagelatte.simplechaos.attacks;

import com.languagelatte.simplechaos.attacks.attack.Attack;
import com.languagelatte.simplechaos.attacks.attack.ErrorAttack;
import com.languagelatte.simplechaos.attacks.attack.ExceptionAttack;
import com.languagelatte.simplechaos.attacks.attack.JvmCrashAttack;
import com.languagelatte.simplechaos.attacks.attack.LatencyAttack;
import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.reports.Reporter;

public class RandomChaosAttacks implements ChaosAttacks {

  private final Reporter reporter;
  private final ChaosProperties properties;
  private final Attack exception;
  private final Attack latency;
  private final Attack jvmCrash;
  private final Attack error;
  // private static final Logger LOGGER = LoggerFactory.getLogger(RandomChaosAttacks.class);

  public RandomChaosAttacks(
      Reporter reporter,
      ChaosProperties properties,
      ExceptionAttack exception,
      LatencyAttack latency,
      JvmCrashAttack jvmCrash,
      ErrorAttack error) {
    this.reporter = reporter;
    this.properties = properties;
    this.exception = exception;
    this.error = error;
    this.latency = latency;
    this.jvmCrash = jvmCrash;
  }

  @Override
  public void exception() {
    if (properties.shouldAttackRun(ChaosAttack.EXCEPTION)) {
      reporter.reportAttack(ChaosAttack.EXCEPTION);
      exception.attack(properties);
    }
  }

  @Override
  public void error() {
    if (properties.shouldAttackRun(ChaosAttack.ERROR)) {
      reporter.reportAttack(ChaosAttack.ERROR);
      error.attack(properties);
    }
  }

  @Override
  public void jvmCrash() {
    if (properties.shouldAttackRun(ChaosAttack.JVMCRASH)) {
      reporter.reportAttack(ChaosAttack.JVMCRASH);
      jvmCrash.attack(properties);
    }
  }

  @Override
  public void latency() {
    if (properties.shouldAttackRun(ChaosAttack.LATENCY)) {
      reporter.reportAttack(ChaosAttack.LATENCY);
      latency.attack(properties);
    }
  }
}
