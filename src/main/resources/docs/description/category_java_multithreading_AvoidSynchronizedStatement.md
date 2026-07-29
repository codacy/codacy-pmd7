Since: PMD 7.5.0

Reports any synchronization statements.

      Virtual threads (introduced by Java 21 via [JEP 444](https://openjdk.org/jeps/444)) are pinned to their
      carrier thread when executing code protected by `synchronized` (either synchronized blocks or synchronized
      methods). When the virtual threads are blocked by I/O and need to wait, the carrier thread stays unavailable
      for other virtual threads. If this happens with all carrier threads, no new virtual threads can be
      executed and this can cause performance problems.

      With Java 24 ([JEP 491](https://openjdk.org/jeps/491)) virtual threads can release their carrier thread,
      when they are blocked by I/O inside a synchronized block. The situation, that all carrier threads are
      exhausted, should be much less likely.

      Note: Thread pinning still occurs, if the virtual thread is calling native methods inside a synchronized
      block.

Example(s):
```
public class Foo {
    // Try to avoid this:
    void foo() {
        // code that doesn't need mutual exclusion
        synchronized(this) {
            // code that requires mutual exclusion
        }
        // more code that doesn't need mutual exclusion
    }

    // Prefer this:
    Lock instanceLock = new ReentrantLock();
    void foo() {
        // code that doesn't need mutual exclusion
        instanceLock.lock();
        try {
            // code that requires mutual exclusion
        } finally {
            instanceLock.unlock();
        }
        // more code that doesn't need mutual exclusion
    }

    // Or prefer this with tryLock:
    Lock instanceLock = new ReentrantLock();
    void foo() {
        // code that doesn't need mutual exclusion
        if (instanceLock.tryLock(10, TimeUnit.SECONDS)) {
            try {
                // code that requires mutual exclusion
            } finally {
                instanceLock.unlock();
            }
        } else {
            // unable to acquire the lock
        }
        // more code that doesn't need mutual exclusion
    }
}
```
