package com.languagelatte.simplechaos.properties;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.languagelatte.simplechaos.attacks.ChaosAttack;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ChaosPropertiesTest {

  @Test
  public void isTodayEnabledTest() {

    // Clock clock = mock(Clock.class);
    // This was a friday
    LocalDate friday = LocalDate.of(2021, 1, 1);
    LocalDate saturday = LocalDate.of(2021, 1, 2);

    Clock fridayClock =
        Clock.fixed(
            friday.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

    Clock saturdayClock =
        Clock.fixed(
            saturday.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();

    assertTrue(properties.isTodayEnabled(fridayClock));
    assertFalse(properties.isTodayEnabled(saturdayClock));

    Map<String, String> props = new HashMap<>();
    props.put(
        SimpleChaosConstants.ENABLED_DAYS,
        LocalDate.now(ZoneId.systemDefault()).getDayOfWeek().toString());
    properties.loadProperties(props);
    assertTrue(properties.isTodayEnabled(Clock.system(ZoneId.systemDefault())));
  }

  @Test
  public void isThisHourEnabledTest() {

    // Clock clock = mock(Clock.class);
    // This was a friday
    LocalDateTime friday9am = LocalDateTime.of(LocalDate.of(2021, 1, 1), LocalTime.of(9, 0));

    Clock fridayClock =
        Clock.fixed(
            friday9am.toInstant(OffsetDateTime.now(ZoneId.systemDefault()).getOffset()),
            ZoneId.systemDefault());

    ChaosProperties properties = new ChaosPropertiesDefaultImpl();

    assertTrue(properties.isThisHourEnabled(fridayClock));

    Map<String, String> props = new HashMap<>();
    props.put(
        SimpleChaosConstants.ENABLED_HOURS,
        String.valueOf(LocalTime.now(ZoneId.systemDefault()).getHour()));
    properties.loadProperties(props);
    assertTrue(properties.isThisHourEnabled(Clock.system(ZoneId.systemDefault())));
  }

  @Test
  public void isAttackEnabledTest() {
    ChaosProperties properties = new ChaosPropertiesDefaultImpl();

    Map<String, String> props = new HashMap<>();
    props.put(SimpleChaosConstants.LATENCY_ATTACK_ENABLED, "true");

    assertFalse(properties.isAttackEnabled(ChaosAttack.LATENCY));
    properties.loadProperties(props);
    assertTrue(properties.isAttackEnabled(ChaosAttack.LATENCY));
  }
}
