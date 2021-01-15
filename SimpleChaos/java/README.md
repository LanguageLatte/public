# Simple Chaos: Java

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.languagelatte/simple-chaos/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/com.languagelatte/simple-chaos)


**If you are using Spring, look at the  [Spring](../spring/README.md) or [Spring-AOP](../spring-aop/README.md) projects. They are easier to use.**


Contents:
* [Maven](#maven)
* [Gradle](#gradle)
* [Usage](#usage)
* [Properties](#properties)
* [Chance of Attack](#chance-of-attack)
* [Properties copy/paste](#properties-copy-paste)



### Maven
```
<dependency>
  <groupId>com.languagelatte</groupId>
  <artifactId>simple-chaos</artifactId>
  <version>x.x.x</version>
</dependency>
```

### Gradle
```
implementation 'com.languagelatte:simple-chaos:x.x.x'
```

### Usage
```
// 1st load your properties
ChaosProperties properties = new ChaosPropertiesDefaultImpl();
ChaosService chaosService = new ChaosService(properties);

// You can randomly pick an attack:
chaosService.randomAttack();

// Or choose a specific attack:
chaosService.exception();
chaosService.error();
chaosService.latency();
chaosService.jvmCrash();
```

### Properties

By default, all attacks are turned off. There are quite a few properties. At the end of this readme, there are some easy to copy paste versions.

```
| Property                                                | Default Value                            | Notes                                                                                                                                                                                                 |
|---------------------------------------------------------|------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| com.languagelatte.simplechaos.attacks.enabled.enabled   | false                                    | If this is false, then no attacks will run.                                                                                                                                                           |
| com.languagelatte.simplechaos.attacks.enabled.days      | MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY | Monday to Friday                                                                                                                                                                                      |
| com.languagelatte.simplechaos.attacks.enabled.hours     | 9,10,11,12,13,14,15,16                   | 9AM to 4PM                                                                                                                                                                                            |
| com.languagelatte.simplechaos.attacks.exception.enabled | false                                    |                                                                                                                                                                                                       |
| com.languagelatte.simplechaos.attacks.exception.chance  | 0.0                                      |                                                                                                                                                                                                       |
| com.languagelatte.simplechaos.attacks.latency.enabled   | false                                    |                                                                                                                                                                                                       |
| com.languagelatte.simplechaos.attacks.latency.chance    | 0.0                                      |                                                                                                                                                                                                       |
| com.languagelatte.simplechaos.attacks.latency.mintime   | 1000                                     |                                                                                                                                                                                                       |
| com.languagelatte.simplechaos.attacks.latency.maxtime   | 25000                                    |                                                                                                                                                                                                       |
| com.languagelatte.simplechaos.attacks.error.enabled     | false                                    |                                                                                                                                                                                                       |
| com.languagelatte.simplechaos.attacks.error.chance      | 0.0                                      |                                                                                                                                                                                                       |
| com.languagelatte.simplechaos.attacks.jvmcrash.enabled  | false                                    |                                                                                                                                                                                                       |
| com.languagelatte.simplechaos.attacks.jvmcrash.mode     | NA                                       | By default, this attack will call System.exit(1), Setting this property to "RUNTIMEHALT" will use the more drastic Runtime.getRuntime().halt(1). That will skip any hooks that have been registered.  |
| com.languagelatte.simplechaos.attacks.jvmcrash.chance   | 0.0                                      |                                                                                                                                                                                                       |
| com.languagelatte.simplechaos.reporter                  | com.languagelatte.simplechaos.reports.ConsoleLogReporter | Logs a summary via standard slf4j logger.                                                                                                                                             |
| com.languagelatte.simplechaos.attacker                  | com.languagelatte.simplechaos.attacks.RandomChaosAttacks | An implementation which randomly decides when to attack. Based of the chance values for each attack                                                                                   |
|                                                         |                                          |                                                                                                                                                                                                       |
```

## Chance of attack

By default, SimpleChaos uses a random attack chance. So if you set the chance to `0.1` there will be a 10% chance of the attack running everytime the method is called.

The value you pick is up to you. Keep in mind how often the attack method is called. If you are getting 100,000 requests a day, then make sure to pick a very small chance. Conversely, if you only expect 10 requests a day, you might want to pick a larger chance like 10%. 

## Properties Copy-Paste

Three versions below:
1. Setting via `Map` in code.
2. `.properties` file
3. `.yml` file

```
ChaosProperties props = new ChaosPropertiesDefaultImpl();
Map<String,String> properties = new HashMap<>();
properties.put(SimpleChaosConstants.ENABLED, "true");
properties.put(SimpleChaosConstants.EXCEPTION_ATTACK_ENABLED, "true");
properties.put(SimpleChaosConstants.EXCEPTION_ATTACK_CHANCE, "0.5");
properties.put(SimpleChaosConstants.ERROR_ATTACK_ENABLED, "true");
properties.put(SimpleChaosConstants.ERROR_ATTACK_CHANCE, "0.5");
properties.put(SimpleChaosConstants.LATENCY_ATTACK_ENABLED, "true");
properties.put(SimpleChaosConstants.LATENCY_ATTACK_CHANCE, "0.5");
properties.put(SimpleChaosConstants.LATENCY_ATTACK_MINTIME, "1000");
properties.put(SimpleChaosConstants.LATENCY_ATTACK_MAXTIME, "25000");
properties.put(SimpleChaosConstants.JVMCRASH_ATTACK_ENABLED, "true");
properties.put(SimpleChaosConstants.JVMCRASH_ATTACK_CHANCE, "0.5");
properties.put(SimpleChaosConstants.JVMCRASH_ATTACK_MODE, "RUNTIMEHALT");
properties.put(SimpleChaosConstants.ENABLED_HOURS, "8,9,10,11,12,13,14,15,16");
properties.put(SimpleChaosConstants.ENABLED_DAYS, "MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY");
properties.put(SimpleChaosConstants.REPORTER_CLASS, "com.languagelatte.simplechaos.reports.ConsoleLogReporter");
		properties.put(SimpleChaosConstants.ATTACKER_CLASS, "com.languagelatte.simplechaos.attacks.RandomChaosAttacks");

props.loadProperties(properties);
```

```
com.languagelatte.simplechaos.attacks.enabled.enabled=true
com.languagelatte.simplechaos.attacks.enabled.hours=9,10,11,12,13,14,15,16
com.languagelatte.simplechaos.attacks.enabled.days=MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY

com.languagelatte.simplechaos.attacks.exception.enabled=true
com.languagelatte.simplechaos.attacks.exception.chance=0.5

com.languagelatte.simplechaos.attacks.error.enabled=true
com.languagelatte.simplechaos.attacks.error.chance=0.5

com.languagelatte.simplechaos.attacks.latency.enabled=true
com.languagelatte.simplechaos.attacks.latency.chance=0.5

com.languagelatte.simplechaos.attacks.jvmcrash.enabled=true
com.languagelatte.simplechaos.attacks.jvmcrash.chance=0.5
com.languagelatte.simplechaos.attacks.jvmcrash.mode=RUNTIMEHALT

com.languagelatte.simplechaos.reporter=com.languagelatte.simplechaos.reports.ConsoleLogReporter
com.languagelatte.simplechaos.attacker=com.languagelatte.simplechaos.attacks.RandomChaosAttacks

```

```
com:
    languagelatte:
        simplechaos:
            attacks:
                enabled:
                    days: MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY
                    enabled: true
                    hours: 9,10,11,12,13,14,15,16
                error:
                    chance: 0.5
                    enabled: true
                exception:
                    chance: 0.5
                    enabled: true
                jvmcrash:
                    chance: 0.5
                    enabled: true
                    mode: RUNTIMEHALT
                latency:
                    chance: 0.5
                    enabled: true
            reporter: com.languagelatte.simplechaos.reports.ConsoleLogReporter
            attacker: com.languagelatte.simplechaos.attacks.RandomChaosAttacks
```