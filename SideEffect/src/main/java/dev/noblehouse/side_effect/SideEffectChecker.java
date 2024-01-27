package dev.noblehouse.side_effect;

import static com.google.errorprone.BugPattern.SeverityLevel.WARNING;
import static com.google.errorprone.matchers.Matchers.anyOf;
import static com.google.errorprone.matchers.Matchers.instanceMethod;
import static com.google.errorprone.matchers.Matchers.staticMethod;

import com.google.errorprone.BugPattern;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.bugpatterns.BugChecker.MethodTreeMatcher;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.matchers.Matcher;
import com.google.errorprone.util.ASTHelpers;
import com.sun.source.tree.ExpressionStatementTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.ReturnTree;
import com.sun.source.tree.StatementTree;
import com.sun.source.tree.VariableTree;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@BugPattern(
    summary = "Method should be annotated because it calls a impure function",
    explanation = "Method should be annotated because it calls a impure function",
    severity = WARNING)
public final class SideEffectChecker extends BugChecker implements MethodTreeMatcher {

  private static final Matcher<ExpressionTree> JAVA_LANG_MATH_RANDOM =
      staticMethod().onClass("java.lang.Math").named("random");

  // These are ALWAYS impure. Things like network calls, file system read/write, random numbers,
  // time.now, date.now
  private static final Matcher<ExpressionTree> ALL_KNOWN_THIRD_PARTY_IMPURE_METHODS =
      anyOf(JAVA_LANG_MATH_RANDOM);

  private static final Matcher<ExpressionTree> JAVA_UTIL_LIST_ADD =
      instanceMethod().onDescendantOf("java.util.List").named("add");

  // These are impure when applied to a non local variable. i.e., modifying an input parameter.
  private static final Matcher<ExpressionTree>
      ALL_KNOWN_THIRD_PARTY_CONTEXT_DEPENDENT_IMPURE_METHODS = anyOf(JAVA_UTIL_LIST_ADD);

  @Override
  public Description matchMethod(MethodTree tree, VisitorState state) {

    boolean isMethodMarkedAsPure =
        ASTHelpers.hasAnnotation(
            tree, "dev.noblehouse.side_effect.annotations.SideEffectIgnore", state);
    boolean isMethodMarkedAsSideEffect =
        ASTHelpers.hasAnnotation(tree, "dev.noblehouse.side_effect.annotations.SideEffect", state);

    // If the method is already annotated with @SideEffect, then we can exit early. (Future idea, If
    // this plugin becomes very mature, then we should still process. If we don't find a side
    // effect, then we should emit a warning saying the method is erroneously annotated.)
    // If the method is annotated with @SideEffectIgnore, we can exit early.
    if (isMethodMarkedAsSideEffect || isMethodMarkedAsPure) {
      return Description.NO_MATCH;
    }

    List<String> errors = new ArrayList<>();
    Set<String> localVars = new HashSet<>();

    for (StatementTree stmt : tree.getBody().getStatements()) {
      if (stmt instanceof VariableTree vt) {
        localVars.add(vt.getName().toString());
        ExpressionTree aaa = vt.getInitializer();
        errors.addAll(evaluateExpressionTree(aaa, state, localVars));

      } else if (stmt instanceof ReturnTree rt) {
        ExpressionTree aaa = rt.getExpression();
        errors.addAll(evaluateExpressionTree(aaa, state, localVars));

      } else if (stmt instanceof ExpressionStatementTree et) {
        ExpressionTree aaa = et.getExpression();
        errors.addAll(evaluateExpressionTree(aaa, state, localVars));
      }
    }

    return errors.isEmpty() ? Description.NO_MATCH : buildDescription(tree).build();
  }

  private List<String> evaluateExpressionTree(
      ExpressionTree expressionTree, VisitorState state, Set<String> localVars) {

    List<String> errors = new ArrayList<>();
    if (expressionTree instanceof MethodInvocationTree methodInvocationTree) {
      var a = methodInvocationTree.getMethodSelect();
      String variable = "";
      if (a instanceof MemberSelectTree memberSelectTree) {
        var mstExpressionTree = memberSelectTree.getExpression();

        if (mstExpressionTree instanceof IdentifierTree identifierTree) {
          variable = identifierTree.getName().toString();
        }
      }

      if (ASTHelpers.hasAnnotation(
          ASTHelpers.getSymbol(methodInvocationTree),
          "dev.noblehouse.side_effect.annotations.SideEffect",
          state)) {
        errors.add("Should be annotated because it calls a impure function");
      } else if (ALL_KNOWN_THIRD_PARTY_IMPURE_METHODS.matches(methodInvocationTree, state)) {
        errors.add("Should be annotated because it calls a impure function");
      } else if (!localVars.contains(variable)
          && ALL_KNOWN_THIRD_PARTY_CONTEXT_DEPENDENT_IMPURE_METHODS.matches(
              methodInvocationTree, state)) {
        errors.add("Should be annotated because it calls a impure function");
      }
    }

    return errors;
  }
}
