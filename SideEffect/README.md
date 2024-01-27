# SideEffect

NOTE - This project is extremely early in development. No releases will be published until it is much further along. (If it ever gets that far along...)

A plugin to make **[function purity](https://en.m.wikipedia.org/wiki/Pure_function)**, and **[side effects](https://en.m.wikipedia.org/wiki/Side_effect_(computer_science))** a part of the type system. Like [NullAway](https://github.com/uber/nullaway), but for side effects.


Note - In this doc, we use the term "side effect" as synonymous with impure function.


## Checks

These 5 checks are meant to be a proof of concept 

* [TODO] Any function that calls a function annotated with @SideEffect or @NotPure should itself be annotated with @SideEffect or @NotPure. (Do we actually want @NotPure?)
* [TODO] Any function that is annotated with @Pure should be ignored. (maybe renamed to @SideEffectIgnore, or @SideEffectFree)
* [TODO] Any function who's classes FQDN is not in the input list should be ignored.
* [TODO] Any function that mutates an input parameter of type List,Map,Set should be annotated with @SideEffect
* [TODO] Any function that calls Math.random should be annotated with @SideEffect



## Explicit Non Checks (or optional?)
We want to be practical here. We don't want to catch all side effects, because then the plugin will be just be too annoying. Some examples:

* All loggers, metrics, traces.
* Writing to console?
* Mutating local (non shared) state.




## Java AST Notes

```
public class ExampleClass {
    
    
    // MethodTreeMatcher will find this
    public void f1() {
        
        // stmt instanceof VariableTree vt = TRUE
        var a = someFunc();
        
        // stmt instanceof ExpressionStatementTree vt = TRUE
        someFunc();
        
        // stmt instanceof ReturnTree vt = TRUE
        return someFunc();
    }
}
```