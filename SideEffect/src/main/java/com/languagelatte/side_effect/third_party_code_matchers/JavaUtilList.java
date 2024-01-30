package com.languagelatte.side_effect.third_party_code_matchers;

import static com.google.errorprone.matchers.Matchers.instanceMethod;

import com.google.errorprone.matchers.Matcher;
import com.sun.source.tree.ExpressionTree;
import java.util.List;

public class JavaUtilList {

  private static final String JAVA_UTIL_LIST = "java.util.List";
  public static final List<Matcher<ExpressionTree>> matchers =
      List.of(
          instanceMethod().onDescendantOf(JAVA_UTIL_LIST).named("add"),
          instanceMethod().onDescendantOf(JAVA_UTIL_LIST).named("addAll"),
          instanceMethod().onDescendantOf(JAVA_UTIL_LIST).named("clear"),
          instanceMethod().onDescendantOf(JAVA_UTIL_LIST).named("remove"),
          instanceMethod().onDescendantOf(JAVA_UTIL_LIST).named("removeAll"),
          instanceMethod().onDescendantOf(JAVA_UTIL_LIST).named("replaceAll"),
          instanceMethod().onDescendantOf(JAVA_UTIL_LIST).named("retainAll"),
          instanceMethod().onDescendantOf(JAVA_UTIL_LIST).named("set"),
          instanceMethod().onDescendantOf(JAVA_UTIL_LIST).named("sort"));
}
