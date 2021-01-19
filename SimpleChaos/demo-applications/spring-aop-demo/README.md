# SpimpleChaos - Spring-AOP Demo

This demo is a simple SpringBoot web project. It uses the `simple-spring-aop` project. The following endpoints are exposed in the `RestEndpoint.java` file. Each endpoint calls a method in another class that is annotated with either `@Service`, `@Repository`, `@Component` or `@Controller`

`GET /simplechaos/aop/service`
`GET /simplechaos/aop/repository`
`GET /simplechaos/aop/component`
`GET /simplechaos/aop/controller`

The chance for each attack has been set at `50%` (far higher than you would ever want to use). When calling one of the above end points, you will be able to see if the attack ran or not. The attacks have been set to run on any day/hour for demonstration. Normally, you would want to leave the default settings of only running Mon-Fri 9 to 5

The Properties have all be set in the `application.properties` file. Spring automatically loads these into the Spring `Enviornment` class.

You can also expirement with the following annotations: These work regardless of the Spring specific annotations above. 
* `@ChaosAttackThisMethod`
* `@ChaosDoNotAttackThisMethod`
* `@ChaosAttackThisClass`
* `@ChaosDoNotAttackThisClass`

## Building the demo
```
mvn package
```

## Running the demo
```
// From the root directory
cd target
java -jar simplechaos-spring-aop-demo-1.0.0.jar
```