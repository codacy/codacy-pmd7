Since: PMD 7.27.0

Do not use protected members in final classes since they cannot be subclassed. This does
            not apply to methods that override a protected method from a superclass, since visibility cannot be decreased .
            Clarify your intent by using private or package access modifiers instead.

            This replaced the old rules &quot;AvoidProtectedFieldInFinalClass&quot; and &quot;AvoidProtectedMethodInFinalClassNotExtending&quot; in PMD 7.27.0

Example(s):
```
public final class Foo {
  private int bar() {}
  protected int baz() {} // Foo cannot be subclassed, and doesn't extend anything, so is baz() really private or package visible?
  protected int field; // This should be a private or package visible field
  protected enum InnerEnum {} // Same for enums ...
  protected class InnerClass {} // ... classes ...
  protected record InnerRecord {} // ... record ...
  protected @interface InnerAnnotation {} // ... and annotations
}
```
