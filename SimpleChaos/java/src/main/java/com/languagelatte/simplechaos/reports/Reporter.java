package com.languagelatte.simplechaos.reports;

import com.languagelatte.simplechaos.attacks.ChaosAttack;

public interface Reporter {
  public void reportAttack(ChaosAttack chaosAttack);

  public void publishReport();
}
