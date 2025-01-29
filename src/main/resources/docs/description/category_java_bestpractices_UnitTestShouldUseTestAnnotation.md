Since: PMD 4.0

The rule will detect any test method starting with &quot;test&quot; that is not properly annotated, and will therefore not be run.

            In JUnit 4, only methods annotated with the `@Test` annotation are executed.
            In JUnit 5, one of the following annotations should be used for tests: `@Test`, `@RepeatedTest`, `@TestFactory`, `@TestTemplate` or `@ParameterizedTest`.
            In TestNG, only methods annotated with the `@Test` annotation are executed.

            Note: This rule was named JUnit4TestShouldUseTestAnnotation before PMD 7.7.0.

Example(s):
```
public class MyTest {
    public void testBad() {
        doSomething();
    }

    @Test
    public void testGood() {
        doSomething();
    }
}
```
