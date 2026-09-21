Since: PMD 7.27.0

The UnusedReturnValue rule ensures that developers do not ignore the return values of methods where doing so would likely be a bug.

This could be because you are treating a mutation-by-copy operation (like String.concat() or BigDecimal.add())
as an in-place mutation, or forgetting to handle a critical validation result, error code, or stream.
By enforcing that the returned object is assigned to a variable, passed to another method, or explicitly handled,
this rule prevents silent logic failures.

See {% rule &quot;java/bestpractices/UnusedAssignment&quot; %} if you think assigning to a variable isn't enough.

This rule replaces the old rules {% rule CheckSkipResult %} and {% rule UselessPureMethodCall %}.

Example(s):
```
public class Foo {
    public void foo() {
        bar();
    }

    @CheckReturnValue
    private int bar() {
        return 42;
    }
}
```
