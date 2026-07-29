Since: PMD 7.21.0

A non-public type should not declare its own members as public, as their visibility is effectively limited
to private, package-private, or protected, making the use of the public modifier misleading.
Declaring members as public within a non-public type creates confusion and can lead to unintended consequences
if the type is later made public, as this would expose all its public members.
However, it is acceptable for a non-public type to inherit public members from a superclass, as this is part of
the superclass's design.

To avoid such issues, these members should be declared as protected, package-private, or even private,
as appropriate. Note that altering the visibility of such members might unintentionally affect the API
of a public subtype. Specifically, a public subtype of a package-private supertype inherits all public
methods of the supertype. Converting these methods to private removes them from the subtype as well, which
can inadvertently change the subtype's public API.

Example(s):
```
class Wrong {
    public void method() {} // violation
    public int field; // violation
}

class Correct {
    void method() {}
    // or even
    private void privateMethod() {}

    int field;
    // or even
    private int privateField;
}
```
