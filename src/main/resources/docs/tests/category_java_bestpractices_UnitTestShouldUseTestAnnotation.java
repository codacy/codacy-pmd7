//#Patterns: category_java_bestpractices_UnitTestShouldUseTestAnnotation

public class MyTest {
    //#Warn: category_java_bestpractices_UnitTestShouldUseTestAnnotation
    public void testBad() {
        doSomething();
    }
    //#Warn: category_java_bestpractices_UnitTestShouldUseTestAnnotation
    @Test public void testGood() {
        doSomething();
    }
}
