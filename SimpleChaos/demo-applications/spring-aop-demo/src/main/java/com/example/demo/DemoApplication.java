package com.example.demo;

import com.languagelatte.simplechaos.spring.ChaosService;
import com.languagelatte.simplechaos.spring.ChaosSpringPropertiesImpl;
import com.languagelatte.simplechaos.spring.aop.SimpleChaosSpringAspect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;


@SpringBootApplication
//@ComponentScan({"com.example", "com.languagelatte.simplechaos"})
@Import({
	SimpleChaosSpringAspect.class,
	ChaosService.class,
	ChaosSpringPropertiesImpl.class})

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
