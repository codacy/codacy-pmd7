Since: PMD 7.17.0

This rule detects method calls of pure methods whose result is unused. A pure method is a method without
side effects. Therefore, ignoring the result of such a method call is likely a mistake.

Either the method call can be removed or the result should be used.

Example(s):
```
public class Something {
    public void foo() {
        List.of("foo").size(); // result unused
        Stream.of("bar").map(item -> System.out.format("%s", item)); // result unused
        Stream.of("bar").forEach(item -> System.out.format("%s", item)); // better
    }
}
```
