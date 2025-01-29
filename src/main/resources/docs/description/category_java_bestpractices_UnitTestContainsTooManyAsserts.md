Since: PMD 5.0

Unit tests should not contain too many asserts. Many asserts are indicative of a complex test, for which
            it is harder to verify correctness.  Consider breaking the test scenario into multiple, shorter test scenarios.
            Customize the maximum number of assertions used by this Rule to suit your needs.

            This rule checks for JUnit (3, 4 and 5) and TestNG Tests.

            Note: This rule was named JUnitTestContainsTooManyAsserts before PMD 7.7.0.

Example(s):
```
public class MyTestCase {
    // Ok
    @Test
    public void testMyCaseWithOneAssert() {
        boolean myVar = false;
        assertFalse("should be false", myVar);
    }

    // Bad, too many asserts (assuming max=1)
    @Test
    public void testMyCaseWithMoreAsserts() {
        boolean myVar = false;
        assertFalse("myVar should be false", myVar);
        assertEquals("should equals false", false, myVar);
    }
}
```
