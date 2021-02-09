package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import com.languagelatte.simplechaos.spring.SimpleChaosSpringConfig
import kotlin.jvm.JvmStatic
import org.springframework.boot.SpringApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(SimpleChaosSpringConfig::class)
open class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}