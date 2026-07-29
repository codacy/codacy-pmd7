Since: PMD 7.18.0

This rule detects the use of labeled statements. By default, it allows labeled loops so that you can
use `break` / `continue` with labels. This can be changed with the property `allowLoops` to flag any
labels.

Labels make control flow difficult to understand and should be avoided. They can be confused
with the goto statement and make code harder to read and maintain.

If you really need to jump out of an inner loop, think about refactoring the multiple
loops into a function - that way, you can replace the break with a return.

This rule implements SonarSource rule [S1119](https://sonarsource.github.io/rspec/#/rspec/S1119).

To detect unused labels, use the rule {% rule UnusedLabel %}.

Example(s):
```
class Scratch {
    public static void main(String[] args) {
        int x = 1;
        lbl1: while (true) {          // violation: labeled statement on loop (when property allowLoops=false)
            lbl2: if (x == 3) {       // violation: labeled statement
                x++;
                break lbl2;
            }
            lbl3: if (x == 4) {
                break lbl1;
            }
            System.out.println(x);
            x++;
        }
    }
}

        
            
// Bad
outer:
for (int i = 0; i < 10; i++) {
    for (int j = 0; j < 10; j++) {
        break outer; // violation - labeled break (when allowLoops=false)
        continue outer; // violation - labeled continue (when allowLoops=false)
    }
}
```
