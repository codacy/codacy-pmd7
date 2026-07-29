Since: PMD 7.8.0

Detects when the Queueable interface is used but a Finalizer is not attached.
            It is best practice to call the `System.attachFinalizer(Finalizer f)` method within the `execute` method of a class which implements the `Queueable` interface.
            Without attaching a Finalizer, there is no way of designing error recovery actions should the Queueable action fail.

Example(s):
```
// Incorrect code, does not attach a finalizer.
public class UserUpdater implements Queueable {
    public List<User> usersToUpdate;

    public UserUpdater(List<User> usersToUpdate) {
        this.usersToUpdate = usersToUpdate;
    }

    public void execute(QueueableContext context) { // no Finalizer is attached
        update usersToUpdate;
    }
}

// Proper code, attaches a finalizer.
public class UserUpdater implements Queueable, Finalizer {
    public List<User> usersToUpdate;

    public UserUpdater(List<User> usersToUpdate) {
        this.usersToUpdate = usersToUpdate;
    }

    public void execute(QueueableContext context) {
        System.attachFinalizer(this);
        update usersToUpdate;
    }

    public void execute(FinalizerContext ctx) {
        if (ctx.getResult() == ParentJobResult.SUCCESS) {
            // Handle success
        } else {
            // Handle failure
        }
    }
}
```
