Since: PMD 4.0

Methods that return boolean or Boolean results should be named as predicate statements to denote this.
            I.e., 'isReady()', 'hasValues()', 'canCommit()', 'willFail()', etc. Avoid the use of the 'get' prefix for these methods.
            By default, methods returning the Boolean wrapper type are checked as well. This can be disabled with the includeWrappedType property.

Example(s):
```
public boolean getFoo();            // bad
public Boolean getFoo();            // bad if includeWrappedType=true (default)
public boolean isFoo();             // ok
public Boolean isFoo();             // ok
public boolean getFoo(boolean bar); // ok, unless checkParameterizedMethods=true
```
