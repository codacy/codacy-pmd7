Since: PMD 7.25.0

JUnit Jupiter tests cannot be private. Otherwise, they won't be found by the framework:

&gt; Test classes, test methods, and lifecycle methods are not required to be `public`, but they must not be `private`.

— [JUnit Documentation](https://docs.junit.org/6.0.3/writing-tests/test-classes-and-methods.html)

Example(s):
```
import org.junit.jupiter.api.Test;

//bad
class MyTests {
    @Test
    private void testFoo() { }
}

//bad
private class MyTests {
    @Test
    void testFoo() { }
}

//good
class MyTests {
    @Test
    void testFoo() { }
}
```
