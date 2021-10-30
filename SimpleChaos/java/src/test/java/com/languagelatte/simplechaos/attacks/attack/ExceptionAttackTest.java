package com.languagelatte.simplechaos.attacks.attack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.languagelatte.simplechaos.properties.ChaosProperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExceptionAttackTest {

  @BeforeEach
  public void beforeEach(){
    ChaosProperties.INSTANCE.clearProperties();
  }

  @Test()
  public void attackTest() {

    Attack exceptionAttack = new ExceptionAttack();

    Exception exception =
        assertThrows(
            Exception.class, () -> exceptionAttack.attack(ChaosProperties.INSTANCE));
    assertEquals("Chaos Attack Exception", exception.getMessage());
  }
}
