package com.languagelatte.side_effect;

import com.google.errorprone.CompilationTestHelper;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SideEffectTest {
  @Test
  public void callsImpureFunction() {
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

  @Test
  public void callsImpureFunction2() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "public class TestClass {",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public double f1() {",
            "       return Math.random();",
            "   }",
            "}")
        .doTest();
  }

  @Test
  public void callsImpureFunctionIsAnnotatedWithSideEffect() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import com.languagelatte.side_effect.annotations.SideEffect;",
            "public class TestClass {",
            "   @SideEffect public double f1() {",
            "       double w = Math.random(); ",
            "       return w;",
            "   }",
            "}")
        .doTest();
  }

  @Test
  public void callsImpureFunctionIsAnnotatedWithSideEffectIgnore() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import com.languagelatte.side_effect.annotations.SideEffectIgnore;",
            "public class TestClass {",
            "   @SideEffectIgnore public double f1() {",
            "       double w = Math.random(); ",
            "       return w;",
            "   }",
            "}")
        .doTest();
  }

  @Test
  public void callsAtSideEffectFunction() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import com.languagelatte.side_effect.annotations.SideEffect;",
            "public class TestClass {",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public void f1() {var w = f2();}",
            "   @SideEffect public int f2() {return 1;}",
            "}")
        .doTest();
  }

  @Test
  public void callsAtSideEffectFunction2() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import com.languagelatte.side_effect.annotations.SideEffect;",
            "public class TestClass {",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public void f1() {f2();}",
            "   @SideEffect public void f2() {}",
            "}")
        .doTest();
  }

  @Test
  public void callsAtSideEffectFunctionIsAnnotatedWithSideEffect() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import com.languagelatte.side_effect.annotations.SideEffect;",
            "public class TestClass {",
            "   @SideEffect public void f1() {f2();}",
            "   @SideEffect public void f2() {}",
            "}")
        .doTest();
  }

  @Test
  public void callsAtSideEffectFunctionIsAnnotatedWithSideEffectIgnore() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import com.languagelatte.side_effect.annotations.SideEffect;",
            "import com.languagelatte.side_effect.annotations.SideEffectIgnore;",
            "public class TestClass {",
            "   @SideEffectIgnore public void f1() {f2();}",
            "   @SideEffect public void f2() {}",
            "}")
        .doTest();
  }

  @Test
  public void mutatesLocalList() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import java.util.ArrayList;",
            "public class TestClass {",
            "   public void f1() {",
            "       var list = new ArrayList<String>();",
            "       list.add(\"this is **NOT** a side effect\");",
            "   }",
            "}")
        .doTest();
  }

  @Test
  public void mutatesMethodParameterList() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import java.util.List;",
            "public class TestClass {",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public void f1(List<String> list) {",
            "       list.add(\"this is a side effect\");",
            "   }",
            "}")
        .doTest();
  }

  @Test
  public void mutatesMethodParameterArray() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import java.util.List;",
            "public class TestClass {",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public void f1(String[] array) {",
            "       array[0] = \"this is a side effect\";",
            "   }",
            "}")
        .doTest();
  }

  @Test
  public void mutatesLocalArray() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import java.util.List;",
            "public class TestClass {",
            "   public void f1() {",
            "       String[] array = new String[1];",
            "       array[0] = \"this is a side effect\";",
            "   }",
            "}")
        .doTest();
  }

  @Test
  public void mutatesClassList() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import java.util.ArrayList;",
            "import java.util.List;",
            "public class TestClass {",
            "   List<String> list = new ArrayList<>();",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public void f1() {",
            "       list.add(\"this is a side effect\");",
            "   }",
            "}")
        .doTest();
  }

  @Test
  public void classVariableThatIsImpure() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "public class TestClass {",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   double x = Math.random();",
            "}")
        .doTest();
  }

  @Test
  public void classVariableThatIsImpureIsAnnotatedWithSideEffect() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import com.languagelatte.side_effect.annotations.SideEffect;",
            "public class TestClass {",
            "   @SideEffect double x = Math.random();",
            "}")
        .doTest();
  }

  @Test
  public void usesClassVariableThatIsImpure() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList(
                "-XepOpt:NullAway:AnnotatedPackages=com.mike ",
                "-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import com.languagelatte.side_effect.annotations.SideEffect;",
            "public class TestClass {",
            "   @SideEffect double x = Math.random();",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public double f1() {",
            "       return this.x;",
            "   }",
            "}")
        .doTest();
  }

  public void f1(String[] a) {
    a[0] = "this is a side effect";
  }

  // TODO - Test fails.
  //   @Test
  //   public void usesClassVariableThatIsImpure2() {
  //     helper
  //         .addSourceLines(
  //             "TestClass.java",
  //             "import com.languagelatte.side_effect.annotations.SideEffect;",
  //             "public class TestClass {",
  //             "   @SideEffect double x = Math.random();",
  //             "   // BUG: Diagnostic contains: Method should be annotated because it calls a
  // impure function",
  //             "   public double f1() {",
  //             "       return 10.0 + this.x;",
  //             "   }",
  //             "}")
  //         .doTest();
  //   }

  public CompilationTestHelper makeCompilationTestHelperWithArgs(List<String> args) {
    return CompilationTestHelper.newInstance(SideEffect.class, getClass()).setArgs(args);
  }
}
