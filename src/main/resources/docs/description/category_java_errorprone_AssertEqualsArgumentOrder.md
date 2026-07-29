Since: PMD 7.26.0

Unit testing frameworks provide the `assertEquals` method that takes expected and actual
            value of an object as arguments.

            Passing a constant as the actual value and a computed value as the expected value
            indicates that the actual and expected values were swapped, leading to confusing
            error messages should the test fail.

            This rule currently supports assertions from JUnit Jupiter, JUnit versions 4 and 3,
            TestNG, Spring assertions and the JSONAssert library.

Example(s):
```
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class SimpleTest {
    @Test
    public void testX() {
        assertEquals(next("foo"), "bar"); // wrong argument order
        assertEquals("bar", next("foo")); // good
    }
}
```
