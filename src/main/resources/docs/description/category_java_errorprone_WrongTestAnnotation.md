Since: PMD 7.26.0

When you use the @Test annotation from the wrong framework, your tests won't be executed.

Example(s):
```
// Example 1: GOOD if using JUnit Jupiter, BAD if using JUnit4/TestNG
import org.junit.jupiter.api.Test;
public class TestClass {
    @Test
    public void myTest() { }
}

// Example 2: GOOD if using TestNG, BAD if using JUnit
import org.testng.annotations.Test;
public class TestClass {
    @Test
    public void myTest() { }
}
```
