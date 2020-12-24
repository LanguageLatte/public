package com.languagelatte.simplechaos_java.implementations;

import com.languagelatte.simplechaos_java.customerrorandexception.SimpleChaosException;

public class DefaultChaosException implements ChaosAttack {

  @Override
  public void execute() {
    throw new SimpleChaosException();
  }
}
