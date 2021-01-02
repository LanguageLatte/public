package com.languagelatte.simplechaos.attacks.attack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import com.languagelatte.simplechaos.properties.ChaosPropertiesDefaultImpl;
import org.junit.jupiter.api.Test;

public class ExceptionAttackTest {
  @Test()
  public void attackTest() {

    Attack exceptionAttack = new ExceptionAttack();

    Exception exception =
        assertThrows(
            Exception.class, () -> exceptionAttack.attack(new ChaosPropertiesDefaultImpl()));
    assertEquals("Chaos Attack Exception", exception.getMessage());
  }
}
