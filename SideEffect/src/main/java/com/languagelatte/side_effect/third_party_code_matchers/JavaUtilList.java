package com.languagelatte.side_effect.third_party_code_matchers;

import static com.google.errorprone.matchers.Matchers.instanceMethod;

import com.google.errorprone.matchers.Matcher;
import com.sun.source.tree.ExpressionTree;

public class JavaUtilList {
  public static final Matcher<ExpressionTree> JAVA_UTIL_LIST_ADD =
      instanceMethod().onDescendantOf("java.util.List").named("add");
}
