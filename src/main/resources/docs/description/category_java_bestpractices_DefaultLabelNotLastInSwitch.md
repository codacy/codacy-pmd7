Since: PMD 1.5

By convention, the default label should be the last label in a switch statement or switch expression.

Note: This rule has been renamed from &quot;DefaultLabelNotLastInSwitchStmt&quot; with PMD 7.7.0.

Example(s):
```
public class Foo {
  void bar(int a) {
   switch (a) {
    case 1:  // do something
       break;
    default:  // the default case should be last, by convention
       break;
    case 2:
       break;
   }
  }
}
```
