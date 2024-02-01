# SideEffect

NOTE - This project is extremely early in development. No releases will be published until it is much further along. (If it ever gets that far along...)

A plugin to make **[function purity](https://en.m.wikipedia.org/wiki/Pure_function)**, and **[side effects](https://en.m.wikipedia.org/wiki/Side_effect_(computer_science))** a part of the type system. Like [NullAway](https://github.com/uber/nullaway), but for side effects.


Note - In this doc, we use the term "side effect" as synonymous with impure function.


## Checks

Status | Name | Desc | Code Example
---|---|---|---
DONE | @SideEffect | Any function, or class member, that calls a function annotated with `@SideEffect` should itself be annotated with `@SideEffect` | link
DONE | @SideEffectIgnore | Any function, or class member, that is annotated with `@SideEffectIgnore` should be ignored.| link
DONE | Random |Any function that calls `java.lang.Math.random` should be annotated with `@SideEffect`|link
DONE | List | Any function that mutates an input parameter, or class member of type `java.util.List` should be annotated with `@SideEffect`| link
TODO | Map | Any function that mutates an input parameter, or class member of type `java.util.Map` should be annotated with `@SideEffect`| link
TODO | Set | Any function that mutates an input parameter, or class member of type `java.util.Set` should be annotated with `@SideEffect`| link
TODO | Queue | Any function that mutates an input parameter, or class member of type `java.util.Queue` should be annotated with `@SideEffect`| link
TODO | Deque | Any function that mutates an input parameter, or class member of type `java.util.Deque` should be annotated with `@SideEffect`| link
TODO | a|b|c
TODO | a|b|c
TODO | a|b|c

## Explicit Non Checks (or optional?)
We want to be practical here. We don't want to catch all side effects, because then the plugin will be just be too annoying. Some examples:

* All loggers, metrics, traces.
* Writing to console.
* Mutating local (non shared) state.