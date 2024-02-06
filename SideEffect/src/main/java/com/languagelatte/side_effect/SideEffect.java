package com.languagelatte.side_effect;

import static com.google.errorprone.BugPattern.SeverityLevel.ERROR;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.BugPattern;
import com.google.errorprone.ErrorProneFlags;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.bugpatterns.BugChecker.ClassTreeMatcher;
import com.google.errorprone.bugpatterns.BugChecker.MethodTreeMatcher;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.util.ASTHelpers;
import com.languagelatte.side_effect.third_party_code_matchers.All;
import com.sun.source.tree.ArrayAccessTree;
import com.sun.source.tree.AssignmentTree;
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
import com.sun.source.tree.Tree.Kind;
import com.sun.source.tree.VariableTree;
import com.sun.tools.javac.code.Symbol;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;

@AutoService(BugChecker.class)
@BugPattern(
    summary = "Method should be annotated because it calls a impure function",
    explanation = "Method should be annotated because it calls a impure function",
    severity = ERROR)
public final class SideEffect extends BugChecker implements MethodTreeMatcher, ClassTreeMatcher {
  private static final long serialVersionUID = 1L;

  private final Config config;

  public SideEffect() {
    config = new Config(ImmutableMap.of());
  }

  @Inject
  public SideEffect(ErrorProneFlags flags) {
    this.config = new Config(flags.getFlagsMap());
  }

  @Override
  public Description matchMethod(MethodTree tree, VisitorState state) {
    if (!shouldAnalyze(ASTHelpers.getSymbol(tree))) {
      return Description.NO_MATCH;
    }

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

    if (tree.getBody() != null) {
      for (StatementTree stmt : tree.getBody().getStatements()) {
        var kind = stmt.getKind();
        if (stmt instanceof VariableTree vt) {
          localVars.add(vt.getName().toString());
          ExpressionTree aaa = vt.getInitializer();
          errors.addAll(evaluateExpressionTree(aaa, state, localVars));

        } else if (stmt instanceof ReturnTree rt) {
          ExpressionTree aaa = rt.getExpression();
          var kind2 = aaa.getKind();
          errors.addAll(evaluateExpressionTree(aaa, state, localVars));

        } else if (stmt instanceof ExpressionStatementTree et) {
          ExpressionTree aaa = et.getExpression();
          errors.addAll(evaluateExpressionTree(aaa, state, localVars));
        }
      }
    }

    return errors.isEmpty() ? Description.NO_MATCH : buildDescription(tree).build();
  }

  @Override
  public Description matchClass(ClassTree tree, VisitorState state) {
    if (!shouldAnalyze(ASTHelpers.getSymbol(tree))) {
      return Description.NO_MATCH;
    }

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
    if (expressionTree == null) {
      return errors;
    }

    var kind = expressionTree.getKind();

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
    } else if (expressionTree instanceof AssignmentTree assignmentTree) {
      String variable = "";
      var variableTree = assignmentTree.getVariable();
      if (variableTree.getKind() == Kind.ARRAY_ACCESS) {

        if (variableTree instanceof ArrayAccessTree arrayAccessTree) {
          var www = arrayAccessTree.getExpression();
          if (www instanceof IdentifierTree identifierTree) {
            variable = identifierTree.getName().toString();
          }
        }

        if (!localVars.contains(variable)) {
          errors.add("Should be annotated because it calls a impure function");
        }
      }
    }

    return errors;
  }

  private boolean shouldAnalyze(Symbol sym) {
    var packageName = ASTHelpers.enclosingPackage(sym).getQualifiedName().toString();

    if (this.config.isUnAnnotatedPackage(packageName)) {
      return false;
    }

    if (this.config.isAnnotatedPackage(packageName)) {
      return true;
    }

    return false;
  }
}
