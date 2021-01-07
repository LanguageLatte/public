package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import com.languagelatte.simplechaos.properties.ChaosPropertiesDefaultImpl;
import com.languagelatte.simplechaos.properties.SimpleChaosConstants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.example", "com.languagelatte.simplechaos"})
public class Application {

	public static ChaosPropertiesDefaultImpl props = new ChaosPropertiesDefaultImpl();

	public static void main(String[] args) {

		Map<String,String> properties = new HashMap<>();
		properties.put(SimpleChaosConstants.ENABLED, "true");
		properties.put(SimpleChaosConstants.EXCEPTION_ATTACK_ENABLED, "true");
		properties.put(SimpleChaosConstants.EXCEPTION_ATTACK_CHANCE, "0.5");
		properties.put(SimpleChaosConstants.ERROR_ATTACK_ENABLED, "true");
		properties.put(SimpleChaosConstants.ERROR_ATTACK_CHANCE, "0.5");
		properties.put(SimpleChaosConstants.LATENCY_ATTACK_ENABLED, "true");
		properties.put(SimpleChaosConstants.LATENCY_ATTACK_CHANCE, "0.5");
		properties.put(SimpleChaosConstants.JVMCRASH_ATTACK_ENABLED, "true");
		properties.put(SimpleChaosConstants.JVMCRASH_ATTACK_CHANCE, "0.5");
		properties.put(SimpleChaosConstants.JVMCRASH_ATTACK_MODE, "RUNTIMEHALT");
		properties.put(SimpleChaosConstants.ENABLED_HOURS, "17,18,19,20,21,22,23");
		props.loadProperties(properties);


		SpringApplication.run(Application.class, args);
	}
}
