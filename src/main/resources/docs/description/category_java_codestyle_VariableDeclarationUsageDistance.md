Since: PMD 7.25.0

Checks for variables that are defined long before they are used.

            Keeping variable declaration closer to its usage makes the code more readable.

            Final variables are exempt from the rule to allow snapshot comparison,
            the same applies to variables defined using time measurement methods.

            Other local variable declarations that are not separated from the current one
            by any executable statement don't count towards the distance either, so that
            variables that are declared together (e.g. all at the top of a block) are not
            reported just because of each other.

Example(s):
```
public void lengthSum(String[] strings) {

    int length = 0; // violation: could be moved closer to the loop

    // ... many unrelated statements

    for (String str : strings) {
        length += str.length();
    }

    System.out.println("Total length " + length);
}

public void measure() {
    long start = System.currentTimeMillis(); // OK, time measurement cannot be moved
    final State state = getState(); // OK, final variables are exempt from the rule to allow snapshot comparison
    // ... many unrelated statements

    long duration = System.currentTimeMillis() - start;
    System.out.println("Duration: " + duration);
    System.out.println("State changed: " + !state.equals(getState()));
}

// none of these are reported: the other declarations in between don't count
// towards the distance, since they aren't executable statements themselves
public void report() {
    Report firstReport;
    Tree   firstTree;
    Frame  frame;
    Report secondReport;

    firstReport  = new Report();
    secondReport = new Report();
    frame        = createFrame();
    // ... many unrelated statements

    firstTree = firstReport.getTree();
}
```
