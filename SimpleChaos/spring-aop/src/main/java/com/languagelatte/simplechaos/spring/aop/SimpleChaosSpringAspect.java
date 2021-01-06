package com.languagelatte.simplechaos.spring.aop;

import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.spring.ChaosService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Aspect
@Configuration
public class SimpleChaosSpringAspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleChaosSpringAspect.class);
  private ChaosProperties chaosProperties;
  private ChaosService chaosService;
  private final Environment environment;

  public SimpleChaosSpringAspect(
      ChaosProperties chaosProperties, ChaosService chaosService, Environment environment) {
    this.chaosProperties = chaosProperties;
    this.chaosService = chaosService;
    this.environment = environment;
  }

  @Pointcut("within(com.languagelatte.simplechaos..*)")
  public void classInSimpleChaosPackage() {}

  @Pointcut("within(org.springframework..*)")
  public void classInSpringPackage() {}

  // !within(is(FinalType)) && !within(is(EnumType)) &&
  @Pointcut("execution(public * *(..))")
  public void publicMethodNonFinalNonEnumClass() {}

  @Pointcut("within(@org.springframework.stereotype.Service *)")
  public void classAnnotatedWithService() {}

  @Pointcut("within(@org.springframework.stereotype.Repository *)")
  public void classAnnotatedWithRepository() {}

  @Pointcut("within(@org.springframework.stereotype.Controller *)")
  public void classAnnotatedWithController() {}

  @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
  public void classAnnotatedWithRestController() {}

  @Pointcut("within(@org.springframework.stereotype.Component *)")
  public void classAnnotatedWithComponent() {}

  @Pointcut("within(@org.springframework.scheduling.annotation.Scheduled *)")
  public void classAnnotatedWithScheduled(){}

  @Around(
      "publicMethodNonFinalNonEnumClass() "
          + "&& classAnnotatedWithRepository() "
          + "&& !classInSpringPackage() "
          + "&& !classInSimpleChaosPackage()")
  public Object repositoryIntercept(ProceedingJoinPoint pjp) throws Throwable {
    if ("true"
        .equals(
            environment.getProperty(
                "com.languagelatte.simplechaos.spring.aop.repository.enabled"))) {
      LOGGER.info("Inside SimpleChaos Spring Repository Aspect");
      chaosService.randomAttack(chaosProperties);
    }
    return pjp.proceed();
  }

  @Around(
      "publicMethodNonFinalNonEnumClass() "
          + "&& classAnnotatedWithController() "
          + "&& !classInSpringPackage() "
          + "&& !classInSimpleChaosPackage()")
  public Object controllerIntercept(ProceedingJoinPoint pjp) throws Throwable {
    if ("true"
        .equals(
            environment.getProperty(
                "com.languagelatte.simplechaos.spring.aop.controller.enabled"))) {
      LOGGER.trace("Inside SimpleChaos Spring Controller Aspect");
      chaosService.randomAttack(chaosProperties);
    }
    return pjp.proceed();
  }

  @Around(
      "publicMethodNonFinalNonEnumClass() "
          + "&& classAnnotatedWithRestController() "
          + "&& !classInSpringPackage() "
          + "&& !classInSimpleChaosPackage()")
  public Object restControllerIntercept(ProceedingJoinPoint pjp) throws Throwable {
    if ("true"
        .equals(
            environment.getProperty(
                "com.languagelatte.simplechaos.spring.aop.restcontroller.enabled"))) {
      LOGGER.trace("Inside SimpleChaos Spring Rest Controller Aspect");
      chaosService.randomAttack(chaosProperties);
    }
    return pjp.proceed();
  }

  @Around(
      "publicMethodNonFinalNonEnumClass() "
          + "&& classAnnotatedWithComponent() "
          + "&& !classInSpringPackage() "
          + "&& !classInSimpleChaosPackage()")
  public Object componentIntercept(ProceedingJoinPoint pjp) throws Throwable {
    if ("true"
        .equals(
            environment.getProperty(
                "com.languagelatte.simplechaos.spring.aop.component.enabled"))) {
      LOGGER.trace("Inside SimpleChaos Spring Component Aspect");
      chaosService.randomAttack(chaosProperties);
    }
    return pjp.proceed();
  }

  @Around(
      "publicMethodNonFinalNonEnumClass() "
          + "&& classAnnotatedWithService() "
          + "&& !classInSpringPackage() "
          + "&& !classInSimpleChaosPackage()")
  public Object serviceIntercept(ProceedingJoinPoint pjp) throws Throwable {
    if ("true"
        .equals(
            environment.getProperty("com.languagelatte.simplechaos.spring.aop.service.enabled"))) {
      LOGGER.trace("Inside SimpleChaos Spring Service Aspect");
      chaosService.randomAttack(chaosProperties);
    }
    return pjp.proceed();
  }
}
