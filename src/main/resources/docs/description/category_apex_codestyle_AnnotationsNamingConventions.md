Since: PMD 7.17.0

Apex, while case-insensitive, benefits from a consistent code style to improve readability and maintainability.
            Enforcing PascalCase for annotations aligns with the established conventions and reduces ambiguity - promoting a unified coding standard.

Example(s):
```
// Incorrect:
@istest
private static void fooShouldBar() {
  //...
}

// Correct:
@IsTest
private static void fooShouldBar() {
  //...
}

//Incorrect:
@testvisible
private boolean doSomething = false;


//Correct:
@TestVisible
private boolean doSomething = false;
```
