package com.languagelatte.simplechaos.spring.aop;

import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.spring.ChaosService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Aspect
@Configuration
public class ServiceAspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);
  private ChaosProperties chaosProperties;
  private ChaosService chaosService;
  private final Environment environment;

  public ServiceAspect(
      ChaosProperties chaosProperties, ChaosService chaosService, Environment environment) {
    this.chaosProperties = chaosProperties;
    this.chaosService = chaosService;
    this.environment = environment;
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
      LOGGER.info("Inside SimpleChaos Spring Service Aspect");
      chaosService.randomAttack(chaosProperties);
    }
    return pjp.proceed();
  }
}
