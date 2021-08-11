# Simple Chaos: Spring

[![Maven Central - Java](https://maven-badges.herokuapp.com/maven-central/com.languagelatte/simple-chaos/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/com.languagelatte/simple-chaos-spring)

**Consider using the [Spring-AOP](../spring-aop/README.md) version. This allows you to add chaos with zero code changes**

Contents:
* [Maven](#maven)
* [Gradle](#gradle)
* [Usage](#usage)
* [Properties](#properties)


### Maven
```
<dependency>
  <groupId>com.languagelatte</groupId>
  <artifactId>simple-chaos-spring</artifactId>
  <version>x.x.x</version>
</dependency>
```

### Gradle
```
implementation 'com.languagelatte:simple-chaos-spring:x.x.x'
```

### Usage

In your main class, (Application.java most likely), you need to make sure you are importing this project so Spring finds all the services. Adding this import should be all that is needed. 

```
@Import(SimpleChaosSpringConfig.class)
```


You can use `ChaosService` just like any other spring `@Service` class. 

```
@Autowired
private final ChaosService chaosService;
```
or 
```
// Constructor Injection
private final ChaosService chaosService;
public YourClass(ChaosService chaosService){
    this.chaosService = chaosService;
}
```

Now call any attacks you want:
```
// You can randomly pick an attack:
chaosService.randomAttack();

// Or choose a specific attack:
chaosService.exception();
chaosService.error();
chaosService.latency();
chaosService.jvmCrash();
```

### Properties

The Chaos Service will use Spring's `Environment` for all properties. By default, all attacks are turned off. 
Please look at the base java project for all the properties that you will need to set. 

[Properties](../java/README.md#properties)