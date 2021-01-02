package com.languagelatte.simplechaos.attacks.attack;

import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.languagelatte.simplechaos.properties.ChaosPropertiesDefaultImpl;
import org.junit.jupiter.api.Test;

public class JvmCrashAttackTest {

  @Test
  public void systemExitWithArbitraryStatusCode() throws Exception {

    Attack jvmAttack = new JvmCrashAttack();

    int statusCode =
        catchSystemExit(
            () -> {
              jvmAttack.attack(new ChaosPropertiesDefaultImpl());
            });
    assertEquals(1, statusCode);
  }
}
