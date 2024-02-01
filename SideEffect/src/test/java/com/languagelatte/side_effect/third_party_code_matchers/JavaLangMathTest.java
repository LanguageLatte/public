package com.languagelatte.side_effect.third_party_code_matchers;

import com.google.errorprone.CompilationTestHelper;
import com.languagelatte.side_effect.SideEffect;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class JavaLangMathTest {

  @Test
  public void callsMathRandom() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "public class TestClass {",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public double f1() {",
            "       double w = Math.random(); ",
            "       return w;",
            "   }",
            "}")
        .doTest();
  }

  public CompilationTestHelper makeCompilationTestHelperWithArgs(List<String> args) {
    return CompilationTestHelper.newInstance(SideEffect.class, getClass()).setArgs(args);
  }
}
