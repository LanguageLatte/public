package com.languagelatte.simplechaos.spring.aop;

import com.languagelatte.simplechaos.spring.ChaosService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ServiceAspect {

  private ChaosService chaosService;

  public ServiceAspect(ChaosService chaosService) {
    this.chaosService = chaosService;
  }

  @Pointcut("within(com.languagelatte.simplechaos..*)")
  public void classInChaosMonkeyPackage() {}

  @Pointcut("!within(is(FinalType)) && execution(public * *(..))")
  public void allPublicMethodPointcut() {}

  @Pointcut("within(@org.springframework.stereotype.Service *)")
  public void classAnnotatedWithControllerPointcut() {}

  @Pointcut("within(org.springframework..*)")
  public void springpackage() {}

  @Around(
      "allPublicMethodPointcut() && classAnnotatedWithControllerPointcut() && !springpackage() && !classInChaosMonkeyPackage()")
  public Object intercept(ProceedingJoinPoint pjp) throws Throwable {

    System.out.println(
        "HAHAHAHAH IT WORKED IM INSIDE THE ASPECT " + pjp.getSignature().getDeclaringTypeName());

    chaosService.exception();
    return pjp.proceed();
  }
}
