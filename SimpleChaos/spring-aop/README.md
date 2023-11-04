# Simple Chaos: Spring-AOP

[![Maven Central - Java](https://maven-badges.herokuapp.com/maven-central/com.languagelatte/simple-chaos/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/com.languagelatte/simple-chaos-spring-aop)


Contents:
* [Maven](#maven)
* [Gradle](#gradle)
* [Usage](#usage)
* [Properties](#properties)

### Maven
```
<dependency>
  <groupId>com.languagelatte</groupId>
  <artifactId>simple-chaos-spring-aop</artifactId>
  <version>x.x.x</version>
</dependency>
```

### Gradle
```
implementation 'com.languagelatte:simple-chaos-spring-aop:x.x.x'
```

### Usage

This version of SimpleChaos required almost zero code changes. Simply add this jar to your project and, in your properties, choose which attacks to enable. 

In your main class, (Application.java most likely), you need to make sure you are importing this project so Spring finds all the services. Adding this import should be all that is needed. 

```
@Import(SimpleChaosSpringAOPConfig.class)
```


For the base properties, please see [Properties](../java/README.md#properties)

SimpleChaos can automatically attack classes that are annotated by `@Component`, `@Controller`, `@Repository`, `@RestController` or `@Service` 

You can also choose specific methods or classes to attack or not attack regardless of the above annotations. Just annotate a class or method with the following:

* `@ChaosAttackThisClass`
* `@ChaosDoNotAttackThisClass`
* `@ChaosAttackThisMethod`
* `@ChaosDoNotAttackThisMethod`

The `DoNotAttack` annotations always take precedence. 


### Properties
| Property                                                        | Default Value | Notes                                                             |
|-----------------------------------------------------------------|---------------|-------------------------------------------------------------------|
| com.languagelatte.simplechaos.spring.aop.component.enabled      | false         | Should public methods in classes with @Component be attacked      |
| com.languagelatte.simplechaos.spring.aop.controller.enabled     | false         | Should public methods in classes with @Controller be attacked     |
| com.languagelatte.simplechaos.spring.aop.repository.enabled     | false         | Should public methods in classes with @Repository be attacked     |
| com.languagelatte.simplechaos.spring.aop.restcontroller.enabled | false         | Should public methods in classes with @RestController be attacked |
| com.languagelatte.simplechaos.spring.aop.service.enabled        | false         | Should public methods in classes with @Service be attacked        |

