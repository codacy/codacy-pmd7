//#Patterns: category_java_errorprone_UnusedReturnValue
public class Foo {
    public void foo() {
        //#Warn: category_java_errorprone_UnusedReturnValue
        bar();
    }

    @CheckReturnValue
    private int bar() {
        return 42;
    }
}
