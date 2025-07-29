Since: PMD 7.13.0

This rule finds Apex classes, enums, and interfaces that have the same name as a class, enum, or interface in the `System`
            or `Schema` namespace.
            Shadowing these namespaces in this way can lead to confusion and unexpected behavior.
            Code that intends to reference a `System` or `Schema` class, enum, or interface may inadvertently reference the locally defined type instead.
            This can result in ambiguous code and unexpected runtime behavior.
            It is best to avoid naming your types the same as those in the `System` or `Schema` namespace to prevent these issues.

            Note that the list of classes, enums, and interfaces in the `System` and `Schema` namespaces are determined through
            [io.github.apex-dev-tools:standard-types](https://github.com/apex-dev-tools/standard-types). It is based on the contents of
            Salesforce's [Apex Reference Guide / System Namespace](https://developer.salesforce.com/docs/atlas.en-us.apexref.meta/apexref/apex_namespace_System.htm)
            and [Apex Reference Guide / Schema Namespace](https://developer.salesforce.com/docs/atlas.en-us.apexref.meta/apexref/apex_namespace_Schema.htm).
            As Salesforce introduces new types into the `System` and `Schema` namespaces, the rule might not always recognize
            the new types and produce false-negatives und the standard types are updated.

Example(s):
```
// Violation: Causes a collision with the `System.Database` class.
public class Database {
    public static String query() {
        return 'Hello World';
    }
}
```
