package dev.noblehouse.side_effect;

import com.google.errorprone.CompilationTestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SideEffectCheckerTest {

  private final CompilationTestHelper helper =
      CompilationTestHelper.newInstance(SideEffectChecker.class, getClass());

  @Test
  public void callsImpureFunction() {
    helper
        .addSourceLines(
            "TestClass.java",
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
    helper
        .addSourceLines(
            "TestClass.java",
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
    helper
        .addSourceLines(
            "TestClass.java",
            "import dev.noblehouse.side_effect.annotations.SideEffect;",
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
    helper
        .addSourceLines(
            "TestClass.java",
            "import dev.noblehouse.side_effect.annotations.SideEffectIgnore;",
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
    helper
        .addSourceLines(
            "TestClass.java",
            "import dev.noblehouse.side_effect.annotations.SideEffect;",
            "public class TestClass {",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public void f1() {var w = f2();}",
            "   @SideEffect public int f2() {return 1;}",
            "}")
        .doTest();
  }

  @Test
  public void callsAtSideEffectFunction2() {
    helper
        .addSourceLines(
            "TestClass.java",
            "import dev.noblehouse.side_effect.annotations.SideEffect;",
            "public class TestClass {",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public void f1() {f2();}",
            "   @SideEffect public void f2() {}",
            "}")
        .doTest();
  }

  @Test
  public void callsAtSideEffectFunctionIsAnnotatedWithSideEffect() {
    helper
        .addSourceLines(
            "TestClass.java",
            "import dev.noblehouse.side_effect.annotations.SideEffect;",
            "public class TestClass {",
            "   @SideEffect public void f1() {f2();}",
            "   @SideEffect public void f2() {}",
            "}")
        .doTest();
  }

  @Test
  public void callsAtSideEffectFunctionIsAnnotatedWithSideEffectIgnore() {
    helper
        .addSourceLines(
            "TestClass.java",
            "import dev.noblehouse.side_effect.annotations.SideEffect;",
            "import dev.noblehouse.side_effect.annotations.SideEffectIgnore;",
            "public class TestClass {",
            "   @SideEffectIgnore public void f1() {f2();}",
            "   @SideEffect public void f2() {}",
            "}")
        .doTest();
  }

  @Test
  public void mutatesLocalList() {
    helper
        .addSourceLines(
            "TestClass.java",
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
    helper
        .addSourceLines(
            "TestClass.java",
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
  public void mutatesClassList() {
    helper
        .addSourceLines(
            "TestClass.java",
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
}
