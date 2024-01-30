package com.languagelatte.side_effect;

import static com.google.errorprone.BugPattern.SeverityLevel.WARNING;

import com.google.errorprone.BugPattern;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.bugpatterns.BugChecker.ClassTreeMatcher;
import com.google.errorprone.bugpatterns.BugChecker.MethodTreeMatcher;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.util.ASTHelpers;
import com.languagelatte.side_effect.third_party_code_matchers.All;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.ExpressionStatementTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.ReturnTree;
import com.sun.source.tree.StatementTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@BugPattern(
    summary = "Method should be annotated because it calls a impure function",
    explanation = "Method should be annotated because it calls a impure function",
    severity = WARNING)
public final class SideEffectChecker extends BugChecker
    implements MethodTreeMatcher, ClassTreeMatcher {

  @Override
  public Description matchMethod(MethodTree tree, VisitorState state) {

    boolean isMethodMarkedAsPure =
        ASTHelpers.hasAnnotation(
            tree, "com.languagelatte.side_effect.annotations.SideEffectIgnore", state);
    boolean isMethodMarkedAsSideEffect =
        ASTHelpers.hasAnnotation(
            tree, "com.languagelatte.side_effect.annotations.SideEffect", state);

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
        var kind = aaa.getKind();
        errors.addAll(evaluateExpressionTree(aaa, state, localVars));

      } else if (stmt instanceof ExpressionStatementTree et) {
        ExpressionTree aaa = et.getExpression();
        errors.addAll(evaluateExpressionTree(aaa, state, localVars));
      }
    }

    return errors.isEmpty() ? Description.NO_MATCH : buildDescription(tree).build();
  }

  @Override
  public Description matchClass(ClassTree tree, VisitorState state) {
    List<String> errors = new ArrayList<>();
    // Empty set, there are no localVars here.
    Set<String> localVars = new HashSet<>();

    var members = tree.getMembers();
    for (Tree member : members) {
      if (member instanceof VariableTree vt) {

        boolean isMethodMarkedAsPure =
            ASTHelpers.hasAnnotation(
                vt, "com.languagelatte.side_effect.annotations.SideEffectIgnore", state);
        boolean isMethodMarkedAsSideEffect =
            ASTHelpers.hasAnnotation(
                vt, "com.languagelatte.side_effect.annotations.SideEffect", state);

        if (isMethodMarkedAsSideEffect || isMethodMarkedAsPure) {
          continue;
        }

        errors.addAll(evaluateExpressionTree(vt.getInitializer(), state, localVars));
        if (!errors.isEmpty()) {
          return buildDescription(vt).build();
        }
      }
    }

    return Description.NO_MATCH;
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
          "com.languagelatte.side_effect.annotations.SideEffect",
          state)) {
        errors.add("Should be annotated because it calls a impure function");
      } else if (All.ALL_KNOWN_THIRD_PARTY_IMPURE_METHODS.matches(methodInvocationTree, state)) {
        errors.add("Should be annotated because it calls a impure function");
      } else if (!localVars.contains(variable)
          && All.ALL_KNOWN_THIRD_PARTY_CONTEXT_DEPENDENT_IMPURE_METHODS.matches(
              methodInvocationTree, state)) {
        errors.add("Should be annotated because it calls a impure function");
      }
    } else if (expressionTree instanceof MemberSelectTree memberSelectTree) {
      if (ASTHelpers.hasAnnotation(
          ASTHelpers.getSymbol(memberSelectTree),
          "com.languagelatte.side_effect.annotations.SideEffect",
          state)) {
        errors.add("Should be annotated because it calls a impure function");
      }
    }

    return errors;
  }
}
