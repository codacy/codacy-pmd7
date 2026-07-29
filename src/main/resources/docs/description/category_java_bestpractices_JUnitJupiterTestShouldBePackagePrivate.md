Since: PMD 6.35.0

Reports JUnit Jupiter test classes and methods that are not package-private.
Contrary to JUnit 4 tests, which required public visibility to be run by the engine,
JUnit Jupiter tests can also be run if they're package-private. Marking them as such
is a good practice to limit their visibility.

Test methods are identified as those which use `@Test`, `@RepeatedTest`,
`@TestFactory`, `@TestTemplate` or `@ParameterizedTest`.

Note: This rule was named `JUnit5TestShouldBePackagePrivate` before PMD 7.25.0.

Example(s):
```
class MyTest { // not public, that's fine
    @Test
    public void testBad() { } // should not have a public modifier

    @Test
    protected void testAlsoBad() { } // should not have a protected modifier

    @Test
    private void testNoRun() { } // should not have a private modifier

    @Test
    void testGood() { } // package private as expected
}
```
