Since: PMD 1.2

Configurable naming conventions for type declarations. This rule reports
            type declarations which do not match the regex that applies to their
            specific kind (e.g. enum or interface). Each regex can be configured on the PMD configuration file.
Check the [PMD documentation](https://pmd.github.io/pmd-7.26.0/pmd_rules_java_codestyle.html#classnamingconventions) for more information.

            By default, this rule uses the standard Java naming convention (Pascal case).
            
            The rule can detect utility classes and enforce a different naming convention
            on those. E.g. setting the property `utilityClassPattern` to
            `[A-Z][a-zA-Z0-9]+(Utils?|Helper|Constants)` reports any utility class, whose name
            does not end in &quot;Util(s)&quot;, &quot;Helper&quot; or &quot;Constants&quot;.

            A class is a utility class, if and only if it fulfills ALL the following criteria:
            * ALL member functions, member variables, nested classes, and initializers are static.
            * The class has at least one member function, member variable, or nested class that is not private.
            * The class is a concrete class (neither abstract nor an interface).
            * The class has no superclasses and implements no interfaces.
            * The class has no main method.

            This rule detects test classes using the following convention: Test classes are top-level classes, that
            either inherit from JUnit 3 TestCase or have at least one method annotated with the Test annotations from
            JUnit4/Jupiter or TestNG.

Example(s):
```
// This is Pascal case, the recommended naming convention in Java
// Note that the default values of this rule don't allow underscores
// or accented characters in type names
public class FooBar {}

// You may want abstract classes to be named 'AbstractXXX',
// in which case you can customize the regex for abstract
// classes to 'Abstract[A-Z]\w+'
public abstract class Thing {}

// This class doesn't respect the convention, and will be flagged
public class Éléphant {}
```
