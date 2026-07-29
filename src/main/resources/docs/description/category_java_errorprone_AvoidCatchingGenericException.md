Since: PMD 4.2.6

Avoid catching generic exceptions in try-catch blocks. Catching overly broad exception types makes it difficult
to understand what can actually go wrong in your code and can hide real problems.

**Why these exceptions should not be caught:**

* **Exception**: This is the base class for all checked exceptions. Catching it means you're handling all possible
  checked exceptions the same way, which is rarely appropriate and makes error handling less precise.

* **RuntimeException**: These represent programming errors (like logic bugs) that should typically be fixed in
  code rather than caught and handled. Catching them can hide bugs that should be addressed during development.

* **NullPointerException**: This usually indicates a programming error (accessing null references). Rather than
  catching it, code should be written to avoid null pointer dereferences through null checks or defensive programming.

* **Throwable**: This is the superclass of all errors and exceptions. Catching it means you're trying to handle
  both recoverable exceptions and serious errors (like OutOfMemoryError) the same way, which is dangerous.

* **Error**: These represent serious problems that applications should not try to handle (like OutOfMemoryError,
  StackOverflowError). Catching Error can prevent the JVM from properly terminating when it encounters unrecoverable situations.

**Better approaches:**
- Catch specific exception types that you can meaningfully handle
- Use multiple catch blocks for different exception types that require different handling
- Consider using defensive programming techniques to prevent exceptions rather than catching them

Example(s):
```
public class PrimitiveType {
    public void downCastPrimitiveType(int i) {
        try {
            System.out.println(" i [" + i + "]");
        } catch(NullPointerException e) {
            e.printStackTrace();
        } catch(RuntimeException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
```
