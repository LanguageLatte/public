# Simple Chaos Monkey

Simple Chaos Monkey is a code based chaos testing library. Specifically, it is meant to be used when you do **not** have control over your infrastructure or your runtime enviornment. Traditional chaos tools require that you can destroy or modify infrastructure and/or your runtime enviornment. 


So far there are three sub projects:

* Java Monkey
* Spring Monkey
* Spring-AOP Monkey


Recomended to use near the following areas of code:
1. DB Read/Write
2. File Read/Write
3. Network Calls
4. Deserialization/Serialization