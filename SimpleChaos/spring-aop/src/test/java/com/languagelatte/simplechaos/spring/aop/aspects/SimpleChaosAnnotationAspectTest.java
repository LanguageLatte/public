package com.languagelatte.simplechaos.spring.aop.aspects;

import com.languagelatte.simplechaos.properties.ChaosProperties;
import com.languagelatte.simplechaos.properties.SimpleChaosConstants;
import com.languagelatte.simplechaos.spring.ChaosService;
import com.languagelatte.simplechaos.spring.ChaosSpringPropertiesImpl;
import com.test.testhelper.SimpleChaosAnnotationAspectHelper;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.mock.env.MockEnvironment;

public class SimpleChaosAnnotationAspectTest {

  @Test
  public void test() {

    MockEnvironment mockEnvironment = new MockEnvironment();
    mockEnvironment.setProperty(SimpleChaosConstants.ENABLED, "true");
    mockEnvironment.setProperty(SimpleChaosConstants.ENABLED_HOURS, "20,21,22");

    ChaosProperties properties = new ChaosSpringPropertiesImpl(mockEnvironment);
    ChaosService chaosService = new ChaosService(properties);

    SimpleChaosAnnotationAspectHelper target = new SimpleChaosAnnotationAspectHelper();
    AspectJProxyFactory factory = new AspectJProxyFactory(target);
    SimpleChaosAnnotationAspect aspect = new SimpleChaosAnnotationAspect(properties, chaosService);
    factory.addAspect(aspect);

    SimpleChaosAnnotationAspectHelper helper = factory.getProxy();

    helper.test1();
  }
}
