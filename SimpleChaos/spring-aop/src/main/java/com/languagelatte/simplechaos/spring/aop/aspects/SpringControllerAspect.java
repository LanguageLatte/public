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
public class SpringControllerAspect extends BaseAspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(SpringControllerAspect.class);
  private ChaosProperties chaosProperties;
  private ChaosService chaosService;

  public SpringControllerAspect(ChaosProperties chaosProperties, ChaosService chaosService) {
    this.chaosProperties = chaosProperties;
    this.chaosService = chaosService;
  }

  @Around(
      "publicMethodNonFinalNonEnumClass() "
          + "&& classAnnotatedWithController() "
          + "&& !classesAndMethodsToAvoid()")
  public Object controllerIntercept(ProceedingJoinPoint pjp) throws Throwable {
    if (chaosProperties.getBooleanProperty(SimpleChaosSpringAOPConstants.CONTROLLER_ENABLED)) {
      LOGGER.trace("Inside SimpleChaos Spring Controller Aspect");
      chaosService.randomAttack(chaosProperties);
    }
    return pjp.proceed();
  }
}
