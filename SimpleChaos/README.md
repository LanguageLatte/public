# Simple Chaos

## Table of Contents

1. [What is Chaos Testing](#what-is-chaos-testing)
1. [What is SimpleChaos](#what-is-simplechaos)
    * [ChaosAttacks](#chaosattacks)
    * [Sub Projects](#sub-projects)
        * Java
        * Spring
        * Spring-AOP
1. [Use Cases](#use-cases)
1. [Demo Projects](#demo-projects)
1. [Versioning](#versioning)
1. [Contributing](#contributing)



## What is Chaos Testing
Chaos Testing is the idea that failures are guaranteed to happen, and that Engineers should purposly introduce errors into their production systems so that they can learn how their system really deals with failure. Examples of failures are - network latency, server crashing, database failure, network failure, expected files not existing, out of memory errors, etc.

By purposley intoducing errors:
* We can teach our engineers that they must build their systems to be fault tolerant from day one.
* We can discover execution paths that we had not considered before.  

Additional Reading:
* [principlesofchaos.org](https://principlesofchaos.org/)
* [Awesome Chaos](https://github.com/dastergon/awesome-chaos-engineering) - A Github project that links to dozens of books, articles, and tools for chaos testing.
## What is SimpleChaos
Simple Chaos is a code based chaos testing library. Specifically, it is meant to be used when you do **not** have control over your infrastructure or your runtime enviornment. Traditional chaos tools require that you can destroy or modify infrastructure and/or your runtime enviornment.
### ChaosAttacks
There are many different types of attacks that can be carried out. 
* randomAttack - randomly picks a below attack.
* exception
* error
* jvmCrash
* latency
* ~~highCPU~~ (coming soon)
* ~~highMemory~~ (coming soon)
* ~~abnormalLogs~~ (coming soon)
* ~~highDisk~~ (more research needed, might not be a useful attack) 
* ~~highNetwork~~ (more research needed, might not be a useful or possible attack)
### Sub Projects
Links to the individual project READMEs
* [Java](java/README.md) - A Java only implementation of SimpleChaos.
* [Spring](spring/README.md) - Same as the Java project, but can be autowired and can use spring `Enviornment` for properties.
* [Spring-AOP](spring-aop/README.md) - Uses Spring AOP, so no code changes are needed in your application code. Can automatically attack classes that use common spring annonations like `@Service`, `@Component`, `@RestController`, `@Controller` and `@Repository`.
## Use Cases

One of the most common areas of unexpected system failure is around I/O operations. Often times unit tests will test how a method deals with failed I/O, but our integration or e2e tests will fail to catch all of the possible negative path scenarios. This makes all code that handles I/O a great place to do chaos testing. 

The next most common area is non application code failures. For instance, if your server goes down, Does your monitoring/alerting correctly notify you. Does your load ballancer correctly divert load to a non failed instance? These questions are especially important at companies where the application teams do not own their infrastructure. 

Some good areas to focus on for chaos testing are:
1. DB Read/Write
2. File Read/Write
3. Network Calls
4. Deserialization/Serialization
5. Multi Threaded. 
6. Anywhere else you would like to learn how your system deals with failure. 


## Demo Projects

There are a few demo application for using SimpleChaos. They are located in the `/demo-applications` directory. 

## Versioning
All projects use [SemVer](https://semver.org/). Except versions that are below `1.0.0`. Versions below `1.0.0` should not be used in production and do not follow SemVer.
## Contributing
Contibutions are welcomed! 

The best way to contribute is just to use SimpleChaos in your projects! Opening a feature request or a bug issue is also greatly appreciated.  

If your are looking for more active contributions Some areas that could use help are:
* Examples of real life use cases of SimpleChaos!
* Additional implementations of the `Reporter` Interface. (SQL/NOSQL/etc reporters)
* Documentaion
* Implementations of SimpleChaos for other languages (C#/Go/Python/etc)