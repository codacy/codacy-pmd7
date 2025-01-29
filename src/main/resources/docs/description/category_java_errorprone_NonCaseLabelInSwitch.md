Since: PMD 1.5

A non-case label (e.g. a named break/continue label) was present in a switch statement or switch expression.
This is legal, but confusing. It is easy to mix up the case labels and the non-case labels.

Note: This rule was renamed from `NonCaseLabelInSwitchStatement` with PMD 7.7.0.

Example(s):
```
public class Foo {
  void bar(int a) {
   switch (a) {
     case 1:
       // do something
     mylabel: // this is legal, but confusing!
       break;
     default:
       break;
    }
  }
}
```
