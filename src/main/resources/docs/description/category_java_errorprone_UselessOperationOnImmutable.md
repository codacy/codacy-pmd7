Since: PMD 3.5

An operation on an immutable object will not change the object itself since the result of the operation is a new object.
Therefore, ignoring the result of such an operation is likely a mistake. The operation can probably be removed.

This rule recognizes the types `String`, `BigDecimal`, `BigInteger` or any type from `java.time.*` as immutable.

Example(s):
```
import java.math.*;

class Test {
    void method1() {
        BigDecimal bd=new BigDecimal(10);
        bd.add(new BigDecimal(5));      // this will trigger the rule
    }
    void method2() {
        BigDecimal bd=new BigDecimal(10);
        bd = bd.add(new BigDecimal(5)); // this won't trigger the rule
    }
}
```
