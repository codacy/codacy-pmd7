Since: PMD 4.2

Switch statements are intended to be used to support complex branching behaviour. Using a switch for only a few
cases is ill-advised, since switches are not as easy to understand as if-else statements. In these cases use the
if-else statement to increase code readability.

Note: This rule was named TooFewBranchesForASwitchStatement before PMD 7.7.0.

Example(s):
```
// With a minimumNumberCaseForASwitch of 3
public class Foo {
    public void bar(int condition) {
        switch (condition) {
            case 1:
                instruction;
                break;
            default:
                break; // not enough for a 'switch' stmt, a simple 'if' stmt would have been more appropriate
        }
    }
}
```
