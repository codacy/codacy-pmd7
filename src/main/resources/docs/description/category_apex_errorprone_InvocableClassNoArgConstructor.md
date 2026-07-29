Since: PMD 7.26.0

Apex classes containing @InvocableVariable properties used for flow parameters must expose a visible, zero-argument constructor
            to prevent runtime instantiation errors under modern Salesforce API versions.

Example(s):
```
// BAD: Explicit constructor eliminates default constructor, leaving no zero-arg constructor
public class FlowInput {
    @InvocableVariable public String recordId;
    public FlowInput(String recordId) { this.recordId = recordId; }
}

// GOOD: Explicit zero-arg constructor alongside customized signature
public class FlowInput {
    @InvocableVariable public String recordId;
    public FlowInput() {}
    public FlowInput(String recordId) { this.recordId = recordId; }
}
```
