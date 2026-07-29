Since: PMD 6.23.0

Field declarations should appear before method declarations within a class.

Note: Since PMD 7.21.0, properties are ignored. This means that fields can appear before
or after properties (or fields and properties can be mixed) as long as all fields are placed
before the first method declaration.

Example(s):
```
class Foo {
    public Integer someProperty { get; set; } // good
    public Integer someField; // good

    public void someMethod() {
    }

    public Integer anotherField; // bad
}
```
