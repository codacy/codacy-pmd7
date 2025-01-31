Since: PMD 2.0

Unit tests should include at least one assertion. This makes the tests more robust, and using assert
            with messages provide the developer a clearer idea of what the test does.

            This rule checks for JUnit (3, 4 and 5) and TestNG Tests.

            Note: This rule was named JUnitTestsShouldIncludeAssert before PMD 7.7.0.

Example(s):
```
public class Foo {
   @Test
   public void testSomething() {
      Bar b = findBar();
      // This is better than having a NullPointerException
      // assertNotNull("bar not found", b);
      b.work();
   }
}
```
