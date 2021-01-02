package com.example.demo;

import com.languagelatte.simplechaos.properties.SimpleChaosConstants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.example", "com.languagelatte.simplechaos"})
public class Application {

	public static void main(String[] args) {


		System.setProperty(SimpleChaosConstants.ENABLED, "true");

		System.setProperty(SimpleChaosConstants.EXCEPTION_ATTACK_ENABLED, "true");
		System.setProperty(SimpleChaosConstants.EXCEPTION_ATTACK_CHANCE, "0.5");

		System.setProperty(SimpleChaosConstants.ERROR_ATTACK_ENABLED, "true");
		System.setProperty(SimpleChaosConstants.ERROR_ATTACK_CHANCE, "0.5");

		System.setProperty(SimpleChaosConstants.LATENCY_ATTACK_ENABLED, "true");
		System.setProperty(SimpleChaosConstants.LATENCY_ATTACK_CHANCE, "0.5");

		System.setProperty(SimpleChaosConstants.JVMCRASH_ATTACK_ENABLED, "true");
		System.setProperty(SimpleChaosConstants.JVMCRASH_ATTACK_CHANCE, "0.5");
		System.setProperty(SimpleChaosConstants.JVMCRASH_ATTACK_MODE, "RUNTIMEHALT");

		SpringApplication.run(Application.class, args);
	}
}
