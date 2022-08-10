# Simple Chaos

## Table of Contents

- [Simple Chaos](#simple-chaos)
  - [Table of Contents](#table-of-contents)
  - [What is Chaos Testing](#what-is-chaos-testing)
  - [What is SimpleChaos](#what-is-simplechaos)
    - [Sub Projects](#sub-projects)
  - [Use Cases](#use-cases)
  - [Why SimpleChaos](#why-simplechaos)
    - [ChaosAttacks](#chaosattacks)
  - [Demo Projects](#demo-projects)
  - [Versioning](#versioning)
  - [Contributing](#contributing)

## What is Chaos Testing

Chaos Testing is the idea that failures are guaranteed to happen, and that Engineers should purposely introduce errors
into their **production** systems so that they can learn how their system really deals with failure. Examples of
failures are - network latency, server crashing, database failure, network failure, expected files not existing, out of
memory errors, thread race conditions, etc.

By purposely introducing errors:

* We can teach our engineers that they must build their systems to be fault tolerant from day one.
* We can discover execution paths that we had not considered before.

Additional Reading:

* [principlesofchaos.org](https://principlesofchaos.org/)
* [Awesome Chaos](https://github.com/dastergon/awesome-chaos-engineering) - A Github project that links to dozens of
  books, articles, and tools for chaos testing.

## What is SimpleChaos

Simple Chaos is a code based chaos testing library. Traditional chaos tools require that you can destroy or modify your
infrastructure or your runtime environment. SimpleChaos takes a code based approach to adding chaos to your
applications. A code based approach looks like this:

```
class FileAccess {

    SimpleChaos simpleChaos;
        
    void writeToFile(Data data){
        simpleChaos.latencyAttack()
        // actual logic here
    }
    
    Data readFromFile(){
        simpleChaos.randomAttack()
        // actual logic here
    }
}
```

These attacks will only run when we want them to. You have full control over what days/hours they can run, and you have
full control over how often they run i.e., every 10th call, 100th call, 1000th call, etc.

### Sub Projects

Links to the individual project README s

* [Java](java/README.md) - A Java only implementation of SimpleChaos.
* [Spring](spring/README.md) - Same as the Java project, but can be autowired and can use spring `Enviornment` for
  properties.
* [Spring-AOP](spring-aop/README.md) - Uses Spring AOP, so no code changes are needed in your application code. Can
  automatically attack classes that use common spring annotations like `@Service`, `@Component`, `@RestController`
  , `@Controller` and `@Repository`. Also, specific classes/methods can be targeted or ignored
  with `@ChaosAttackThisClass`, `@ChaosDoNotAttackThisClass`, `@ChaosAttackThisMethod`, `@ChaosDoNotAttackThisMethod`

## Use Cases

One of the most common areas of unexpected system failure is around I/O operations. Often times unit tests will test how
a method deals with failed I/O, but our integration or e2e tests will fail to catch all the possible negative path
scenarios. This makes all code that handles I/O a great place to do chaos testing.

The next most common area is non application code failures. For instance, if your server goes down, Does your
monitoring/alerting correctly notify you. Does your load balancer correctly divert load to a non-failed instance? These
questions are especially important at companies where the application teams do not own their infrastructure.

Some good areas to focus on for chaos testing are:

1. DB Read/Write
2. File Read/Write
3. Network Calls
4. Deserialization/Serialization
5. Multi Threaded.
6. Anywhere else you would like to learn how your system deals with failure.

## Why SimpleChaos

There are a lot of chaos testing tools out there. So why should you use SimpleChaos? There are a few reasons:

1. Most chaos testing tools operate at the infrastructure level. So unless you have control of your infrastructure, you
   won't be able to use these tools. SimpleChaos operates at the code level, so application teams have 100% control
   without needing infrastructure teams.
2. SimpleChaos gives you far more control over what you attack. A traditional chaos tool might disconnect your database
   for 10 minutes, but with SimpleChaos you can pick which exact db methods to attack and when to attack them. Often
   times, an application can survive total DB failure (by not processing any new requests), but will break down if the
   DB failure is at a specific point, for instance the 3rd DB call in a series.

### ChaosAttacks

There are many types of attacks that can be carried out.

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

## Demo Projects

There are a few demo application for using SimpleChaos. They are located in the `/demo-applications` directory.

* [Java demo](demo-applications/java-demo/README.md)
* [Spring demo](demo-applications/spring-demo/README.md)
* [Kotlin-Spring demo](demo-applications/kotlin-spring-demo/README.md)
* Kotlin and Scala examples coming soon.

## Versioning

All projects use [SemVer](https://semver.org/). Except versions that are below `1.0.0`. Versions below `1.0.0` should
not be used in production and do not follow SemVer.

## Contributing

Contributions are welcomed!

The best way to contribute is just to use SimpleChaos in your projects! Opening a feature request, or a bug issue is
also greatly appreciated.

If you are looking for more active contributions Some areas that could use help are:

* Examples of real life use cases of SimpleChaos!
* Additional implementations of the `Reporter` Interface. (SQL/NOSQL/etc reporters)
* Documentation
* Implementations of SimpleChaos for other languages (C#/Go/Python/etc)