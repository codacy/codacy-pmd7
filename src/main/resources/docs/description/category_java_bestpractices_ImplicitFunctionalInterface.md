Since: PMD 7.12.0

Reports functional interfaces that were not explicitly declared as such with
            the annotation `@FunctionalInterface`. If an interface is accidentally a functional
            interface, then it should bear a `@SuppressWarnings(&quot;PMD.ImplicitFunctionalInterface&quot;)`
            annotation to make this clear.

Example(s):
```
// The intent on this declaration is unclear, and the rule will report it.
            public interface MyInterface {
                void doSomething();
            }

            // This is clearly intended as a functional interface.
            @FunctionalInterface
            public interface MyInterface {
                void doSomething();
            }

            // This is clearly NOT intended as a functional interface.
            @SuppressWarnings("PMD.ImplicitFunctionalInterface")
            public interface MyInterface {
                void doSomething();
            }
```
