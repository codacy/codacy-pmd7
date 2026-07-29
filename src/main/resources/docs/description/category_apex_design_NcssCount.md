Since: PMD 7.19.0

This rule uses the NCSS (Non-Commenting Source Statements) metric to determine the number of lines
            of code in a class, method or constructor. NCSS ignores comments, blank lines, and only counts actual
            statements. For more details on the calculation, see the documentation
            {% jdoc apex::lang.apex.metrics.ApexMetrics#NCSS %}.

Example(s):
```
class Foo {                         // +1, total Ncss = 12

  public void bigMethod()           // +1
  {
    int x = 0, y = 2;               // +1
    boolean a = false, b = true;    // +1

    if (a || b) {                   // +1
      try {                         // +1
        do {                        // +1
          x += 2;                   // +1
        } while (x < 12);

        System.exit(0);             // +1
      } catch (IOException ioe) {   // +1
        throw new PatheticFailException(ioe); // +1
      }
    } else {
      System.out.println('false');  // +1
    }
  }
}
```
