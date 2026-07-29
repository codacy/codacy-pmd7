Since: PMD 1.9

Avoid negation within an &quot;if&quot; expression with an &quot;else&quot; clause.  For example, rephrase:
`if (x != y) diff(); else same();` as: `if (x == y) same(); else diff();`.

Most &quot;if (x != y)&quot; cases without an &quot;else&quot; are often return cases, so consistent use of this
rule makes the code easier to read.  Also, this resolves trivial ordering problems, such
as &quot;does the error case go first?&quot; or &quot;does the common case go first?&quot;.

Since null checks are commonly considered a positive condition, they are by default ignored
by this rule. This means both `a == null` and `a != null` are allowed. If you want
to enforce either style and disallow the other, use the property `nullCheckBranch`.

Example(s):
```
boolean bar(int x, int y) {
    return (x != y) ? diff : same;
}
```
