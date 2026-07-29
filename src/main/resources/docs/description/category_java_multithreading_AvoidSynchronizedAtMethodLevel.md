Since: PMD 3.0

Method-level synchronization will pin virtual threads and can cause performance problems. Additionally, it can cause
problems when new code is added to the method.  Block-level ReentrantLock helps to ensure that only the code that
needs mutual exclusion will be locked.

See also {% rule AvoidSynchronizedStatement %}.

Example(s):
```
public class Foo {
    // Try to avoid this:
    synchronized void foo() {
        // code, that doesn't need synchronization
        // ...
        // code, that requires synchronization
        if (!sharedData.has("bar")) {
            sharedData.add("bar");
        }
        // more code, that doesn't need synchronization
        // ...
    }

    // Prefer this:
    Lock instanceLock = new ReentrantLock();

    void bar() {
        // code, that doesn't need synchronization
        // ...
        instanceLock.lock();
        try {
            // code, that requires synchronization
            if (!sharedData.has("bar")) {
                sharedData.add("bar");
            }
        } finally {
            instanceLock.unlock();
        }
        // more code, that doesn't need synchronization
        // ...
    }

    // Or prefer this with tryLock:
    Lock instanceLock = new ReentrantLock();

    void bar() {
        // code, that doesn't need synchronization
        // ...
        if (instanceLock.tryLock(10, TimeUnit.SECONDS)) {
            try {
                // code, that requires synchronization
                if (!sharedData.has("bar")) {
                    sharedData.add("bar");
                }
            } finally {
                instanceLock.unlock();
            }
        } else {
            // unable to acquire the lock
        }
        // more code, that doesn't need synchronization
        // ...
    }

        
    // Try to avoid this for static methods:
    static synchronized void fooStatic() {
    }

    // Prefer this:
    private static Lock CLASS_LOCK = new ReentrantLock();

    static void barStatic() {
        // code, that doesn't need synchronization
        // ...
        CLASS_LOCK.lock();
        try {
            // code, that requires synchronization
        } finally {
            CLASS_LOCK.unlock();
        }
        // more code, that doesn't need synchronization
        // ...
    }
}
```
