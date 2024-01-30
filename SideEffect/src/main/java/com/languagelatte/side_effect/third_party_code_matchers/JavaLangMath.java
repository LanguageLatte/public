package com.languagelatte.side_effect.third_party_code_matchers;

import static com.google.errorprone.matchers.Matchers.staticMethod;

import com.google.errorprone.matchers.Matcher;
import com.sun.source.tree.ExpressionTree;

public class JavaLangMath {
  public static final Matcher<ExpressionTree> JAVA_LANG_MATH_RANDOM =
      staticMethod().onClass("java.lang.Math").named("random");
}
