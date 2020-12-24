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
  }

  @Override
  public void exception() {
    randomChaosAttacks.exception();
  }
}
