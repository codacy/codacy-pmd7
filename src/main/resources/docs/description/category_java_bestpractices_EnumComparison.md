Since: PMD 7.19.0

When comparing enums, `equals()` should be avoided and `==` should be preferred.

Using `==` has some advantages:
* same semantic as `equals()`
* less problematic with possible null pointer exceptions
* supports static type check: If you compare two incompatible enum types, the compiler will tell you.
  When using equals, you'll only get noticed at runtime or not at all.

This rule implements SonarSource rule [S4551](https://sonarsource.github.io/rspec/#/rspec/S4551).

Note, that only primitive types and enums should be compared using `==`. To compare other
objects, `equals()` is the correct way. See {%rule java/errorprone/CompareObjectsWithEquals %}
and {%rule java/errorprone/UseEqualsToCompareStrings %}.

Example(s):
```
enum Color { RED, GREEN, BLUE }
class ColorTester {
  boolean isRed(Color color) {
    return color.equals(Color.RED); // violation
  }

  boolean isGreen(Color color) {
    return color == Color.GREEN; // preferred
  }
}
```
