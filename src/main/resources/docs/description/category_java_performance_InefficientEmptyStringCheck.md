Since: PMD 3.6

Checking the length of `string.trim()` or `string.strip()` is an inefficient way
to decide if a string is really blank, as it creates a new `String` object just to check its size.
As of Java 11 you can use `string.isBlank()` to decide if a string consists only of whitespace,
that check is equivalent to `string.strip().isEmpty()`. To get the semantic of
`string.trim().isEmpty()`, you can loop through the characters and compare them to 0x20:

```java
private boolean checkTrimEmpty(String str) {
    return str.chars().allMatch(c -&gt; c &lt;= 0x20);
}
```

You can also consider using library functions that include a null check
(e.g. Apache's `StringUtils#isBlank` in commons-lang,
Spring's `StringUtils#hasText` in the Spring framework) or use a different definition of whitespace
(e.g. Google's `CharMatcher#whitespace` in Guava).

Calling `string.strip().isBlank()` is also redundant and should be simplified to `string.isBlank()`.

Example(s):
```
public void bar(String string) {
    if (string != null && string.trim().length() > 0) {
        doSomething();
    }
}
```
