package com.languagelatte.simplechaos.spring.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
// import org.springframework.context.annotation.Configuration;

@Aspect
public abstract class BaseAspect {

  @Pointcut("within(com.languagelatte.simplechaos..*)")
  public void classInSimpleChaosPackage() {}

  @Pointcut("within(org.springframework..*)")
  public void classInSpringPackage() {}

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
  public void classAnnotatedWithScheduled() {}

  @Pointcut("within(@com.languagelatte.simplechaos.spring.aop.annotations.ChaosAttackThisClass *)")
  public void classAnnotatedAttackThisClass() {}

  @Pointcut(
      "within(@com.languagelatte.simplechaos.spring.aop.annotations.ChaosDoNotAttackThisClass *)")
  public void classAnnotatedDoNotAttackThisClass() {}

  @Pointcut(
      "@annotation(com.languagelatte.simplechaos.spring.aop.annotations.ChaosAttackThisMethod)")
  public void classAnnotatedAttackThisMethod() {}

  @Pointcut(
      "@annotation(com.languagelatte.simplechaos.spring.aop.annotations.ChaosDoNotAttackThisMethod)")
  public void classAnnotatedDoNotAttackThisMethod() {}

  @Pointcut(
      "classInSpringPackage() "
          + "|| classInSimpleChaosPackage()"
          + "|| classAnnotatedDoNotAttackThisClass()"
          + "|| classAnnotatedDoNotAttackThisMethod()")
  public void classesAndMethodsToAvoid() {}
}
