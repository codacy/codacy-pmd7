Since: PMD 4.2

Calls to `System.gc()`, `Runtime.getRuntime().gc()`, and `System.runFinalization()` are not advised.
Code should have the same behavior whether the garbage collection is disabled using the option
`-Xdisableexplicitgc` or not.

Moreover, &quot;modern&quot; JVMs do a very good job handling garbage collections. If memory usage issues unrelated to memory
leaks develop within an application, it should be dealt with JVM options rather than within the code itself.

Example(s):
```
public class GCCall {
    public GCCall() {
        // Explicit gc call !
        System.gc();
    }

    public void doSomething() {
        // Explicit gc call !
        Runtime.getRuntime().gc();
    }

    public explicitGCcall() {
        // Explicit gc call !
        System.gc();
    }

    public void doSomething() {
        // Explicit gc call !
        Runtime.getRuntime().gc();
    }
}
```
