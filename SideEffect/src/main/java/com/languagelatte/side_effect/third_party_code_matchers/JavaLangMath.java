package com.languagelatte.side_effect.third_party_code_matchers;

import static com.google.errorprone.matchers.Matchers.staticMethod;

import com.google.errorprone.matchers.Matcher;
import com.sun.source.tree.ExpressionTree;
import java.util.List;

public class JavaLangMath {
  public static final List<Matcher<ExpressionTree>> matchers =
      List.of(staticMethod().onClass("java.lang.Math").named("random"));
}
