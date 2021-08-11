package com.test.testhelper;

import com.languagelatte.simplechaos.spring.aop.annotations.ChaosAttackThisClass;
import com.languagelatte.simplechaos.spring.aop.annotations.ChaosAttackThisMethod;

@ChaosAttackThisClass
public class SimpleChaosAnnotationAspectHelper {

  @ChaosAttackThisMethod
  public void test1() {}
}
