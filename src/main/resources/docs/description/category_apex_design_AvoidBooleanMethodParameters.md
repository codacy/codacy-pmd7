Since: PMD 7.15.0

Boolean parameters in a system's API can make method calls difficult to understand and
            maintain. They often indicate that a method is doing more than one thing and
            could benefit from being split into separate methods with more descriptive
            names.

            This rule flags any boolean parameters found in public or global methods,
            encouraging developers to use more expressive alternatives such as enums,
            separate methods, or configuration objects.

Example(s):
```
// Violates the rule: Uses a Boolean parameter
public class MyClass {
  public static void doSomething(Boolean isSomething) {
    if (isSomething == true) {
      // Do something
    } else {
      // Do something else, or maybe do nothing if isSomething is null?
    }
  }
}

// Compliant code: Two separate methods
public class MyClass {
  public static void doSomething() {
    // Do something
  }

  public static void doSomethingElse() {
    // Do something else
  }
}

        
public void setFlag(Boolean strict) { ... } // violation

// compliant
public void enableStrictChecking() { ... }
public void disableStrictChecking() { ... }
```
