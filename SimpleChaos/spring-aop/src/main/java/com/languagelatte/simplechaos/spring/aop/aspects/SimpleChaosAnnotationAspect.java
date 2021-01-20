package com.languagelatte.simplechaos.spring.aop.aspects;

import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.spring.ChaosService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class SimpleChaosAnnotationAspect extends BaseAspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleChaosAnnotationAspect.class);
  private ChaosProperties chaosProperties;
  private ChaosService chaosService;

  public SimpleChaosAnnotationAspect(ChaosProperties chaosProperties, ChaosService chaosService) {
    this.chaosProperties = chaosProperties;
    this.chaosService = chaosService;
  }

  @Around(
      "classAnnotatedAttackThisClass() && publicMethodNonFinalNonEnumClass() && !classesAndMethodsToAvoid()")
  public Object annotatedAttackThisClassIntercept(ProceedingJoinPoint pjp) throws Throwable {

    LOGGER.trace("Inside SimpleChaos classAnnotatedAttackThisClass Aspect");
    chaosService.randomAttack(chaosProperties);

    return pjp.proceed();
  }

  @Around(
      "classAnnotatedAttackThisMethod() && !classAnnotatedAttackThisClass() && publicMethodNonFinalNonEnumClass() && !classesAndMethodsToAvoid()")
  public Object annotatedAttackThisMethodIntercept(ProceedingJoinPoint pjp) throws Throwable {

    LOGGER.trace("Inside SimpleChaos classAnnotatedAttackThisMethod Aspect");
    chaosService.randomAttack(chaosProperties);

    return pjp.proceed();
  }
}
