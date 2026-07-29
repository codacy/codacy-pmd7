Since: PMD 1.04

Unit assertions should include an informative message - i.e., use the three-argument version of
`assertEquals()`, not the two-argument version.

This rule supports tests using JUnit (3, 4 and Jupiter) and TestNG.

Note: This rule was named JUnitAssertionsShouldIncludeMessage before PMD 7.7.0.

Example(s):
```
public class Foo {
    @Test
    public void testSomething() {
        assertEquals("foo", "bar");
        // Use the form:
        // assertEquals("Foo does not equals bar", "foo", "bar");
        // instead
    }
}
```
