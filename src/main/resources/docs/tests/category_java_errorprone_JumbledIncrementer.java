//#Patterns: category_java_errorprone_JumbledIncrementer
public class JumbledIncrementerRule1 {

    public void foo() {
        for (int i = 0; i < 10; i++) {			// only references 'i'
            //#Warn: category_java_errorprone_JumbledIncrementer
            for (int k = 0; k < 20; i++) {		// references both 'i' and 'k'
                System.out.println("Hello");
            }
        }
    }
}
