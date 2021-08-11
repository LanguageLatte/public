package com.languagelatte.simplechaos.spring.aop;

import com.languagelatte.simplechaos.spring.SimpleChaosSpringConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.languagelatte.simplechaos.spring.aop")
@Import(SimpleChaosSpringConfig.class)
public class SimpleChaosSpringAOPConfig {}
