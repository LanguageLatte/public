package com.languagelatte.simplechaos.spring;

import com.languagelatte.simplechaos.attacks.ChaosAttacks;
import com.languagelatte.simplechaos.attacks.RandomChaosAttacks;
import com.languagelatte.simplechaos.attacks.attack.ErrorAttack;
import com.languagelatte.simplechaos.attacks.attack.ExceptionAttack;
import com.languagelatte.simplechaos.attacks.attack.JvmCrashAttack;
import com.languagelatte.simplechaos.attacks.attack.LatencyAttack;
import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.reports.ConsoleLogReporter;
import org.springframework.stereotype.Service;

@Service
public class ChaosService implements ChaosAttacks {

  private final ChaosAttacks chaosAttacks;
  // private final ChaosProperties chaosProperties;

  public ChaosService(ChaosProperties chaosProperties) {
    // this.chaosProperties = chaosProperties;

    this.chaosAttacks =
        new RandomChaosAttacks(
            new ConsoleLogReporter(),
            chaosProperties,
            new ExceptionAttack(),
            new LatencyAttack(),
            new JvmCrashAttack(),
            new ErrorAttack());
  }

  @Override
  public void exception() {
    chaosAttacks.exception();
  }

  @Override
  public void error() {
    chaosAttacks.error();
  }

  @Override
  public void jvmCrash() {
    chaosAttacks.jvmCrash();
  }

  @Override
  public void latency() {
    chaosAttacks.latency();
  }
}
