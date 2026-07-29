Since: PMD 7.17.0

Enforces the modifier order recommended by the JLS. Apart from sorting modifiers,
            this rule also enforces that all annotations appear before all modifier keywords.
            By setting the property `typeAnnotations`, you can also enforce that type
            annotations appear right of the modifier keywords, next to the type they apply to.
            This property can have three values:
            - `onType`: Type annotations must be placed next to the type they apply to
            - `onDecl`: Type annotations must be placed with other annotations, before modifiers.
            This is not enforced if the type annotations syntactically appears within the type, e.g.
            in `public Map.@Nullable Entry&lt;K,V&gt; method()` or `public &lt;T&gt; @NonNull T method()`.
            - `anywhere` (default): Either position fits. They still cannot be interspersed within keyword
            modifiers. Annotations that are not type annotations are still required to be before keyword
            modifiers.

Example(s):
```
abstract public class Foo { // Warn: `public` should appear before `abstract`

    // This order is not recommended, annotations should appear before keyword modifiers,
    // and may appear after if they are type annotations.
    public
    @Override
    static fooStuff() {
    }

    // This order is ok if property typeAnnotations is "anywhere", and enforced if it is "on decl":
    @Nullable
    public Object fooStuff() {}

    // This order is ok if property typeAnnotations is "anywhere", and enforced if it is "on type":
    public @Nullable Object fooStuff() {}


}
```
