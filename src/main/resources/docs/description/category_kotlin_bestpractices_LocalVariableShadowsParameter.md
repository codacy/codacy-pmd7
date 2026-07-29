Since: PMD 7.26.0

A local variable declaration uses the same name as a parameter of the enclosing function.
            This shadows the parameter and may lead to confusion about which value is used.

Example(s):
```
fun compute(result: Int): Int {
    val result = 42  // violation - shadows parameter 'result'
    return result
}

fun compute2(input: Int): Int {
    val result = input + 1  // no violation - different name
    return result
}
```
