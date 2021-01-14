package com.languagelatte.simplechaos.spring.aop.aspects;

import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.spring.ChaosService;
import com.languagelatte.simplechaos.spring.aop.properties.SimpleChaosSpringAOPConstants;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class SpringRestControllerAspect extends BaseAspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(SpringComponentAspect.class);
  private ChaosProperties chaosProperties;
  private ChaosService chaosService;

  public SpringRestControllerAspect(ChaosProperties chaosProperties, ChaosService chaosService) {
    this.chaosProperties = chaosProperties;
    this.chaosService = chaosService;
  }

  @Around(
      "publicMethodNonFinalNonEnumClass() "
          + "&& classAnnotatedWithRestController() "
          + "&& !classesAndMethodsToAvoid()")
  public Object restControllerIntercept(ProceedingJoinPoint pjp) throws Throwable {
    if (chaosProperties.getBooleanProperty(
      SimpleChaosSpringAOPConstants.RESTCONTROLLER_ENABLED)) {
      LOGGER.trace("Inside SimpleChaos Spring Rest Controller Aspect");
      chaosService.randomAttack(chaosProperties);
    }
    return pjp.proceed();
  }
}
