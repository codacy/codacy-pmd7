Since: PMD 7.25.0

Reports blocks that are unnecessary as they don't introduce a new scope.

Unnecessary blocks can make code harder to read and may be misleading. They are often used
to introduce a new variable scope, but if no variables or classes are declared within the
block, it serves no purpose. Similarly, a block that is the only statement within another
block is redundant. Removing them simplifies the code structure.

* A block is considered unnecessary if:
    * It is nested within another block and does not contain any local variable or local
      class declarations.
    * It is the only child of another block.
* Blocks that are required by the language syntax (e.g., as the body of an `if`, `for`,
  `while` statement, or a method body) are not reported.

Example(s):
```
public class Foo {
    public void bar() {
        { // Violation: This block is unnecessary as it doesn't introduce a new scope
            System.out.println("Hello");
        }

        { // No violation: This block is necessary to scope the variable "x"
            int x = 1;
            System.out.println(x);
        }

        // "x" can be redefined here because the previous "x" was scoped to the block above
        int x = 2;
    }

    public void baz() {
        if (foo) {{ // Violation: Why the second block?
            int i = 1;
            System.out.println(i);
        }}

        if (foo) { // No violation: This is what the above should look like
            int i = 1;
            System.out.println(i);
        }
    }
}
```
