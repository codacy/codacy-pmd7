Since: PMD 0.4

Override both `public boolean Object.equals(Object other)` and `public int Object.hashCode()` or override neither.
Even if you are inheriting a `hashCode()` from a parent class, consider implementing `hashCode()` and explicitly
delegating to your superclass.

Not overriding both methods could violate the contract between `equals()` and `hashCode()`. Most importantly,
if two instances are equal, then they must have the same hash code. Using such invalid instances in hash-based
collections like `HashSet` or `HashMap` could lead to duplicated or missing entries.

This rule does not consider types that implement `Comparable`. There is a separate rule
{% rule OverrideBothEqualsAndHashCodeOnComparable %} for this.

Example(s):
```
public class Bar {        // poor, missing a hashcode() method
    public boolean equals(Object o) {
      // do some comparison
    }
}

public class Baz {        // poor, missing an equals() method
    public int hashCode() {
      // return some hash value
    }
}

public class Foo {        // perfect, both methods provided
    public boolean equals(Object other) {
      // do some comparison
    }
    public int hashCode() {
      // return some hash value
    }
}
```
