package com.languagelatte.simplechaos.attacks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import com.languagelatte.simplechaos.attacks.attack.ErrorAttack;
import com.languagelatte.simplechaos.attacks.attack.ExceptionAttack;
import com.languagelatte.simplechaos.attacks.attack.JvmCrashAttack;
import com.languagelatte.simplechaos.attacks.attack.LatencyAttack;
import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.properties.ChaosPropertiesDefaultImpl;
import com.languagelatte.simplechaos.properties.SimpleChaosConstants;
import com.languagelatte.simplechaos.reports.ConsoleLogReporter;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class RandomChaosAttacksTest {

  @Test
  public void exceptionAttackTest() {

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();
    Map<String, String> props = new HashMap<>();

    props.put(SimpleChaosConstants.ENABLED, "true");
    props.put(
        SimpleChaosConstants.ENABLED_HOURS,
        "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23");
    props.put(SimpleChaosConstants.EXCEPTION_ATTACK_ENABLED, "true");
    props.put(SimpleChaosConstants.EXCEPTION_ATTACK_CHANCE, "1.0");
    properties.loadProperties(props);

    RandomChaosAttacks r =
        new RandomChaosAttacks(
            new ConsoleLogReporter(),
            properties,
            new ExceptionAttack(),
            new LatencyAttack(),
            new JvmCrashAttack(),
            new ErrorAttack());

    // r.exception();
    Exception exception = assertThrows(Exception.class, () -> r.exception());
    assertEquals("Chaos Attack Exception", exception.getMessage());
  }
}
