package com.languagelatte.side_effect.third_party_code_matchers;

import static com.google.errorprone.matchers.Matchers.anyOf;

import com.google.errorprone.matchers.Matcher;
import com.sun.source.tree.ExpressionTree;

public class All {
  // These are ALWAYS impure. Things like network calls, file system read/write, random numbers,
  // time.now, date.now
  public static final Matcher<ExpressionTree> ALL_KNOWN_THIRD_PARTY_IMPURE_METHODS =
      anyOf(JavaLangMath.JAVA_LANG_MATH_RANDOM);

  // These are impure when applied to a non local variable. i.e., modifying an input parameter.
  public static final Matcher<ExpressionTree>
      ALL_KNOWN_THIRD_PARTY_CONTEXT_DEPENDENT_IMPURE_METHODS =
          anyOf(JavaUtilList.JAVA_UTIL_LIST_ADD);
}
