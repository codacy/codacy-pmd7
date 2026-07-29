Since: PMD 7.21.0

Avoid importing classes or using APIs from the `sun.*` or `jdk.internal.*` packages, including `sun.misc.Unsafe` or
            `jdk.internal.misc.Unsafe`. These packages are neither portable nor stable and may change or be removed in future
            versions of the JDK. As the name implies, the `Unsafe` class methods are particularly dangerous and have been
            superseded by safer alternatives, such as the VarHandle API (introduced in JDK 9) and the
            Foreign Function &amp; Memory API (introduced in JDK 22). Refer to [JEP 471](https://openjdk.org/jeps/471)
            for safer alternatives with examples.

            If you must depend on Sun APIs, confine their usage to a minimal, isolated scope, such as within a stable
            wrapper class. You may suppress this rule in the implementation of such a wrapper, but it’s strongly
            recommended to migrate to official APIs wherever possible.

            The use of such unsupported APIs will also be flagged by the Java compiler, as they are intended
            strictly for internal purposes. This PMD rule has been added to facilitate independent code reviews
            and to catch instances where compiler warnings may have been overlooked.

Example(s):
```
public final class MemoryWiper {
  public static void main(final String[] args) throws NoSuchFieldException, IllegalAccessException {
    for (final String s : args) {
      sun.misc.Unsafe.getUnsafe().putAddress(Long.parseLong(s), 0L); // bad
    }
  }
}
```
