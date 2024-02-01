package com.languagelatte.side_effect.third_party_code_matchers;

import com.google.errorprone.CompilationTestHelper;
import com.languagelatte.side_effect.SideEffect;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class JavaUtilListTest {

  @Test
  public void callsListAdd() {
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
  public void callsListAddAll() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import java.util.List;",
            "public class TestClass {",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public void f1(List<String> list) {",
            "       list.addAll(List.of(\"this is a side effect\"));",
            "   }",
            "}")
        .doTest();
  }

  @Test
  public void callsListClear() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import java.util.List;",
            "public class TestClass {",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public void f1(List<String> list) {",
            "       list.clear();",
            "   }",
            "}")
        .doTest();
  }

  @Test
  public void callsListRemoveIndex() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import java.util.List;",
            "public class TestClass {",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public void f1(List<String> list) {",
            "       list.remove(1);",
            "   }",
            "}")
        .doTest();
  }

  @Test
  public void callsListRemoveObject() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import java.util.List;",
            "public class TestClass {",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public void f1(List<String> list) {",
            "       list.remove(\"this\");",
            "   }",
            "}")
        .doTest();
  }

  @Test
  public void callsListRemoveAll() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import java.util.List;",
            "public class TestClass {",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public void f1(List<String> list) {",
            "       list.removeAll(List.of(\"this\"));",
            "   }",
            "}")
        .doTest();
  }

  @Test
  public void callsListReplaceAll() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import java.util.List;",
            "public class TestClass {",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public void f1(List<String> list) {",
            "       list.replaceAll(str -> str.replace(\"A\", \"\"));",
            "   }",
            "}")
        .doTest();
  }

  @Test
  public void callsListRetainAll() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import java.util.List;",
            "public class TestClass {",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public void f1(List<String> list) {",
            "       list.retainAll(List.of(\"this\"));",
            "   }",
            "}")
        .doTest();
  }

  @Test
  public void callsListSet() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import java.util.List;",
            "public class TestClass {",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public void f1(List<String> list) {",
            "       list.set(0, \"this\");",
            "   }",
            "}")
        .doTest();
  }

  @Test
  public void callsListSort() {
    makeCompilationTestHelperWithArgs(
            Arrays.asList("-XepOpt:SideEffect:AnnotatedPackages=com.languagelatte"))
        .addSourceLines(
            "TestClass.java",
            "package com.languagelatte.annotated;",
            "import java.util.List;",
            "public class TestClass {",
            "   // BUG: Diagnostic contains: Method should be annotated because it calls a impure function",
            "   public void f1(List<String> list) {",
            "       list.sort(null);",
            "   }",
            "}")
        .doTest();
  }

  public CompilationTestHelper makeCompilationTestHelperWithArgs(List<String> args) {
    return CompilationTestHelper.newInstance(SideEffect.class, getClass()).setArgs(args);
  }
}
