package com.languagelatte.simplechaos.reports;

import com.languagelatte.simplechaos.ChaosAttack;

public interface Reporter {
  public void reportAttack(ChaosAttack chaosAttack, Boolean wasRunSucessfully);

  public void publishReport();
}
