Since: PMD 7.22.0

Implicitly declaring that a class implements an interface already implemented by its
            superclass or parent interface is not necessary.

Example(s):
```
interface I {
}

interface J extends I {
}

class A implements I, J { // Unnecessary to declare that A implements I, since J already extends I
}

class C extends A implements J { // Unnecessary to declare that C implements J, since A already implements J
}
```
