Since: PMD 7.18.0

Conditional statement that does the same thing when the condition is true and false
            is either incorrect (one of the branches should be changed) or redundant
            (can be replaced by one of its branches).

Example(s):
```
class Test {
    int method1() {
        if (Math.random() > 0.5) {
            return 1;
        } else {
            return 1;
        }
    }
    int method2() {
        return Math.random() > 0.5 ? 1 : 1;
    }
}
```
