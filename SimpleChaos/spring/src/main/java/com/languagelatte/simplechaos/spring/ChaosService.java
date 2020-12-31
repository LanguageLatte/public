package com.languagelatte.simplechaos.spring;

import com.languagelatte.simplechaos.ChaosAttacks;
import com.languagelatte.simplechaos.ChaosProperties;
import com.languagelatte.simplechaos.RandomChaosAttacks;
import org.springframework.stereotype.Service;

@Service
public class ChaosService implements ChaosAttacks {

  ChaosAttacks chaosAttacks;
  ChaosProperties chaosProperties;

  public ChaosService(ChaosProperties chaosProperties) {
    this.chaosAttacks = new RandomChaosAttacks(chaosProperties);
    this.chaosProperties = chaosProperties;
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
