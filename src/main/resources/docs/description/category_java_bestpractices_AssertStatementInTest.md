Since: PMD 7.25.0

Assert statements should not be used in test code. Instead, use the assertion methods provided by
                the testing framework, such as JUnit 4's `Assert`, JUnit Jupiter's `Assertions` or TestNG's `Assert` class.
                These helper methods provide better error messages and make test code more readable.
                They also are not disabled when tests are run without `-ea`.

Example(s):
```
import org.junit.Test;

public class FooTest {
    @Test
    public void testSomething() {
        assert Foo.bar == 1; // violation, should use Assert.assertEquals(1, Foo.bar);
    }
}
```
