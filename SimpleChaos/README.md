# Simple Chaos

Simple Chaos is a code based chaos testing library. Specifically, it is meant to be used when you do **not** have control over your infrastructure or your runtime enviornment. Traditional chaos tools require that you can destroy or modify infrastructure and/or your runtime enviornment. 

Recomended to use near the following areas of code:
1. DB Read/Write
2. File Read/Write
3. Network Calls
4. Deserialization/Serialization
5. Anywhere else you would like to learn how your system deals with failure. 

## What is Chaos Testing
The idea of chaos tesing is that failures will happen. Failure is not optional. Failure is a fact.

So what happens when your application has failures? What happens when your DB goes down? What happens when your network fails? What happens when your server runs out of memory? What happens when ...?

Chaos testing aims to answer these questions by purposly introducing errors into your application. Initially just in your Dev or QA environments, but then also in your Production environment once you reach the needed maturity. Yes, you should purposley intorduce failures into your production environment!



## Primary Interface
The primary interface is the `ChaosAttacks` interface
* public randomAttack()
* public exception()
* public error()
* public jvmCrash()
* public latency()
* ~~public highCPU()~~
* ~~public highMemory()~~
* ~~public highDisk()~~
* ~~public highNetwork()~~
* ~~public abnormalLogs(LogLevel logLevel)~~

## Sub Projects
### Java
A java ony implementation of the SimpleChaos Interface.

### Spring
Same as the Java project, but exposes an implementation the SimpleChaos Interface that can be autowired.

### Spring-AOP
Same as the Spring project, but uses Spring AOP so that no code change is required. It will automatically add chaos to public methods in your `@Service`, `@Component`, etc classes.

## Versioning

All projects use [SemVer](https://semver.org/). Except versions that are below `1.0.0`. Versions below `1.0.0` should not be used in production and do not follow SemVer.