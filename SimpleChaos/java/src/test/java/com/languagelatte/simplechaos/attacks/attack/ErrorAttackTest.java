package com.languagelatte.simplechaos.attacks.attack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.languagelatte.simplechaos.properties.ChaosProperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ErrorAttackTest {

  @BeforeEach
  public void beforeEach(){
    ChaosProperties.INSTANCE.clearProperties();
  }

  @Test()
  public void attackTest() {

    ErrorAttack errorAttack = new ErrorAttack();

    Error error =
        assertThrows(Error.class, () -> errorAttack.attack(ChaosProperties.INSTANCE));
    assertEquals("Chaos Attack Error", error.getMessage());
  }
}
