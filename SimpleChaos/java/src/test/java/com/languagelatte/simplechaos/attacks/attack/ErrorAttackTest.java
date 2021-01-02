package com.languagelatte.simplechaos.attacks.attack;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.languagelatte.simplechaos.properties.ChaosPropertiesDefaultImpl;
import org.junit.jupiter.api.Test;

public class ErrorAttackTest {

  @Test()
  public void attackTest() {

    ErrorAttack errorAttack = new ErrorAttack();

    Error error =
        assertThrows(Error.class, () -> errorAttack.attack(new ChaosPropertiesDefaultImpl()));
    assertEquals("Chaos Attack Error", error.getMessage());
  }
}
