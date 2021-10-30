package com.languagelatte.simplechaos.attacks.attack;

import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.languagelatte.simplechaos.properties.ChaosProperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JvmCrashAttackTest {

  @BeforeEach
  public void beforeEach(){
    ChaosProperties.INSTANCE.clearProperties();
  }

  @Test
  public void systemExitWithArbitraryStatusCode() throws Exception {

    Attack jvmAttack = new JvmCrashAttack();

    int statusCode =
        catchSystemExit(
            () -> {
              jvmAttack.attack(ChaosProperties.INSTANCE);
            });
    assertEquals(1, statusCode);
  }
}
