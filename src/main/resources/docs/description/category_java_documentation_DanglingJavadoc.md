Since: PMD 7.17.0

Javadoc comments that do not belong to a class, method or field are ignored by the JavaDoc tool
and don't end up in the generated API documentation. Such comments are either misplaced
(e.g. between annotations and method declaration) or should be block comments.

In order to fix this violation, the comment should be moved to the correct place,
converted into a block comment or removed completely.

Example(s):
```
public class Foo {
  /**
   * Public methods // wrong
   */

  /**
   * A setter // OK
   */
   public void setFoo() {

   }

}
```
