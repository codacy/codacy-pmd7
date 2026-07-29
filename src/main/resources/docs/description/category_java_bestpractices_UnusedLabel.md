Since: PMD 7.18.0

Unused labeled are unnecessary and may be confusing as you might be wondering what this label is used for.
To improve readability the unused label should simply be removed.

This rule implements SonarSource rule [S1065](https://sonarsource.github.io/rspec/#/rspec/S1065).

Example(s):
```
class Example {
    void main() {
        lbl1: {                     // violation: Label "lbl1" is not nused
            int x = 1;
            System.out.println(x);
        }
    }
}
```
