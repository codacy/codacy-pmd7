Since: PMD 1.0

A method should have only one exit point, and that should be the last statement in the method.

If the property `allowGuardIfs` is set to true, an unlimited amount of guard ifs at the beginning of the method are allowed.
A guard if is an if of the form `if (cond) { return somevalue; }` or `if (cond) { throw ex }`. In the example below,
the first exit is part of a guard if, so it isn't counted.

Example(s):
```
public class OneReturnOnly1 {
  public String foo(int x) {
    if (x > 0) {
      return "hey";   // first exit
    }
    return "hi";    // second exit
  }
}
```
