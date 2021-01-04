package com.languagelatte.simplechaos.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class SimpleChaosSpringAspect {

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

  @Pointcut("within(@org.springframework.stereotype.Component *)")
  public void classAnnotatedWithComponent() {}
}
