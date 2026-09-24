Since: PMD 7.10.0

When switching over an enum or sealed class, the compiler will ensure that all possible cases are covered.
If a case is missing, this will result in a compilation error. But if a default case is added, this compiler
check is not performed anymore, leading to difficulties in noticing bugs at runtime.

Not using a default case makes sure, a compiler error is introduced whenever a new enum constant or a
new subclass to the sealed class hierarchy is added. We will discover this problem at compile time
rather than at runtime (if at all).

Note: The fix it not necessarily just removing the default case. Maybe a case is missing which needs to be implemented.

Note 2: The compiler doesn't create a compilation error for a switch *statement* over an enum that is non-exhaustive.
When enabling this rule, you should consider also enabling the rule {% rule NonExhaustiveSwitch %}, so that you are still
notified, if someone adds a new enum constant and that new constant is not covered in an existing switch statement
that previously was exhaustive but now is not anymore.

Example(s):
```
class Foo {
    enum MyEnum { A, B };

    void doSomething(MyEnum e) {
        switch(e) {
            case A -> System.out.println("a");
            case B -> System.out.println("b");
            default -> System.out.println("unnecessary default");
        };
    }
}
```
