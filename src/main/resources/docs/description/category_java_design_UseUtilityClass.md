Since: PMD 0.3

A class is a utility class, if and only if it fulfills ALL the following criteria:
* ALL member functions, member variables, nested classes, and initializers are static.
* The class has at least one member function, member variable, or nested class that is not private.
* The class is a concrete class (neither abstract nor an interface).
* The class has no superclasses and implements no interfaces.
* The class has no main method.

Utility classes should not be instantiable. Make sure that the only constructor is a
private no-args constructor to enforce this.

(Note, that this rule was known before PMD 5.1.0 as UseSingleton).

Example(s):
```
public class MaybeAUtility {
    public static final int SOME_CONST = 42;

    public static void foo() {}
    public static void bar() {}
}
```
