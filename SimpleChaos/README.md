# Simple Chaos

Simple Chaos is a code based chaos testing library. Specifically, it is meant to be used when you do **not** have control over your infrastructure or your runtime enviornment. Traditional chaos tools require that you can destroy or modify infrastructure and/or your runtime enviornment. 

Recomended to use near the following areas of code:
1. DB Read/Write
2. File Read/Write
3. Network Calls
4. Deserialization/Serialization
5. Anywhere else you would like to learn how your system deals with failure. 

## Sub Projects
* Java - A base java 
* Spring
* Spring-AOP

## Primary Interface
The primary interface is the `ChaosAttacks` interface
* public void exception();
* public void error();
* public void jvmCrash();
* public void latency();
* ~~public void highCPU();~~
* ~~public void highMemory();~~
* ~~public void highDisk();~~
* ~~public void highNetwork();~~
* ~~public void abnormalLogs(LogLevel logLevel);~~
