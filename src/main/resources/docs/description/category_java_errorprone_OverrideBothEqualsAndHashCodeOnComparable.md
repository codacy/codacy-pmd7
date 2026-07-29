Since: PMD 7.17.0

Classes that implement `Comparable` should override both `equals()` and
            `hashCode()` if instances of these classes are used in collections. This is
            to ensure that the class's natural ordering is consistent with `equals()`.
            Failing to do so can lead to unexpected behavior in sets which then do not
            conform to the `Set` interface. While the `Set` interface relies on
            `equals()` to determine object equality, sorted sets like `TreeSet` use
            `compareTo()` instead. The same issue can arise when such objects are used
            as keys in sorted maps.

            Note 1: This rule is related to {% rule OverrideBothEqualsAndHashcode %}. It
            will report missing `equals()` and/or `hashCode()` methods for classes only
            that implement `Comparable`.

            Note 2: This rule reports records only, if either `equals()` or `hashCode()`
            have been overridden, but not both. If a record uses the generated
            equals/hashCode methods, then the `compareTo()` implementation is only
            consistent with `equals()` if all record components are considered.

Example(s):
```
public class Bar implements Comparable<Bar> {  // poor - missing equals() and hashCode()
    public int compareTo(Bar other) {
        // some comparison
    }
}

public class Baz implements Comparable<Baz> {  // poor - missing hashCode()
    public int compareTo(Baz other) {
        // some comparison
    }
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return compareTo((Baz) o) == 0;
    }
}

public class Foo implements Comparable<Foo> {  // correct
    public int compareTo(Foo other) {
        // some comparison
    }
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return compareTo((Foo) o) == 0;
    }
    public int hashCode() {
        // return hash code
    }
}
```
