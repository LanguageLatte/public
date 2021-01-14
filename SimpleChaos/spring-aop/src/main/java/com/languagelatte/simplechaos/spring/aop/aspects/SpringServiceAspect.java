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
public class SpringServiceAspect extends BaseAspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(SpringServiceAspect.class);
  private ChaosProperties chaosProperties;
  private ChaosService chaosService;

  public SpringServiceAspect(ChaosProperties chaosProperties, ChaosService chaosService) {
    this.chaosProperties = chaosProperties;
    this.chaosService = chaosService;
  }

  @Around(
      "publicMethodNonFinalNonEnumClass() "
          + "&& classAnnotatedWithService() "
          + "&& !classesAndMethodsToAvoid()")
  public Object serviceIntercept(ProceedingJoinPoint pjp) throws Throwable {
    if (chaosProperties.getBooleanProperty(
      SimpleChaosSpringAOPConstants.SERVICE_ENABLED)) {
      LOGGER.trace("Inside SimpleChaos Spring Service Aspect");
      chaosService.randomAttack(chaosProperties);
    }
    return pjp.proceed();
  }
}
