Since: PMD 7.17.0

Configurable naming conventions for type parameters in generic types and methods.
            This rule reports type parameter declarations which do not match the configured regex.
            Type parameters can appear on classes, interfaces, enums, records, and methods.

            By default, this rule uses the standard Java naming convention (single uppercase letter).

Example(s):
```
// Generic types - valid
public interface Repository<T> { }
public class Cache<K, V> { }

// Generic types - invalid
public interface Repository<type> { }      // lowercase
public class Cache<KEY, VALUE> { }         // multiple letters

// Generic methods - valid
public class Util {
    public static <T> T identity(T value) { return value; }
    public <T, R> R transform(T input, Function<T, R> mapper) { }
}

// Generic methods - invalid
public class Util {
    public static <element> element get(element value) { }  // lowercase
    public <INPUT, OUTPUT> OUTPUT convert(INPUT in) { }     // multiple letters
}
```
