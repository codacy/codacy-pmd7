Since: PMD 7.17.0

Local variables should not be declared and then immediately returned or thrown. Such
            variable declarations add unnecessary complexity and make the code harder to read.
            It is often simpler and cleaner to return or throw the value directly.

            This rule implements SonarSource rule [S1488](https://sonarsource.github.io/rspec/#/rspec/S1488).

Example(s):
```
class Foo {
                    Object foo() {
                        var foo = "foo";
                        return foo;  // instead, just 'return "foo";'
                    }

                    Object bar() {
                        var ex = getIllegalArgumentException();
                        throw ex; // instead, just 'throw getIllegalArgumentException();'
                    }

                    Object baz() {
                        var baz = switch (foo()) {
                            case "foo" -> {
                                var foo = foo();
                                yield foo;  // Can be simplified to 'yield foo();'
                            }
                            case "bar" -> {
                                var bar = bar();
                                yield bar;  // Can be simplified to 'yield bar();'
                            }
                            default -> bar("baz");
                        };
                        return baz; // instead, just 'return switch (foo()) {...'
                    }
                }
```
