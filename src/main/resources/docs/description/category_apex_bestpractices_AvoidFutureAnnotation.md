Since: PMD 7.19.0

Usage of the `@Future` annotation should be limited. The `@Future` annotation is a legacy way to execute asynchronous Apex code, but it has several limitations:
- Methods must be static
- Only primitive data types, primitive arrays, or collections of primitive data types can be parameters
- No way to monitor job status or handle failures
- No support for chaining jobs

Consider implementing the `Queueable` interface instead, which provides:
- Better error handling and monitoring capabilities
- Support for more complex data types
- Ability to chain jobs

See more here: &lt;https://developer.salesforce.com/docs/atlas.en-us.apexcode.meta/apexcode/apex_queueing_jobs.htm&gt;

Example(s):
```
// Not recommended: Using @Future annotation
public class FutureExample {
    @Future
    public static void futureMethod(String accountId) {
        // Process account asynchronously
        Account acc = [SELECT Id, Name FROM Account WHERE Id = :accountId];
        // ... process account
    }
}

// Recommended: Using Queueable interface with Finalizer to detect and handle job failure
public class QueueableExample implements Queueable, Finalizer {
    private String accountId;
    
    public QueueableExample(String accountId) {
        this.accountId = accountId;
    }
    
    public void execute(QueueableContext context) {
        System.attachFinalizer(this);
        Account acc = [SELECT Id, Name FROM Account WHERE Id = :accountId];
        // ... process account
    }

    public void execute(FinalizerContext ctx) {
        if (ctx.getResult() == ParentJobResult.SUCCESS) {
            // Handle success
        } else {
            // Handle failure
        }
    }
}

// Usage:
// System.enqueueJob(new QueueableExample(accountId));
```
