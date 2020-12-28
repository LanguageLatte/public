package com.languagelatte.simplechaos_java.reports;

import com.languagelatte.simplechaos_java.ChaosAttack;

public interface Reporter {
  public void reportAttack(ChaosAttack chaosAttack, Boolean wasRunSucessfully);

  public void publishReport();
}
