package com.languagelatte.simplechaos_spring;

import com.languagelatte.simplechaos_java.ChaosAttacks;
import com.languagelatte.simplechaos_java.RandomChaosAttacks;
import org.springframework.stereotype.Service;

@Service
public class ChaosService implements ChaosAttacks {

  RandomChaosAttacks randomChaosAttacks;
  ChaosSpringPropertiesImpl chaosProperties;

  public ChaosService(ChaosSpringPropertiesImpl chaosProperties) {
    this.randomChaosAttacks = new RandomChaosAttacks(chaosProperties);
    this.chaosProperties = chaosProperties;
  }

  @Override
  public void exception() {
    randomChaosAttacks.exception();
  }

  @Override
  public void error() {
    randomChaosAttacks.error();
  }

  @Override
  public void jvmCrash() {
    randomChaosAttacks.jvmCrash();
  }

  @Override
  public void latency() {
    randomChaosAttacks.latency();
  }
}
