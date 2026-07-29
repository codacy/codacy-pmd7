Since: PMD 7.24.0

Overriding `Thread::run` method is not recommended. Instead, implement `Runnable`
            and pass an instance to the thread constructor. Using `Runnable` to represent a task
            makes it more reusable and allows your class to extend another class.
            When you use lambdas, this also allows you to write more concise code,
            e.g. `new Thread(() -&gt; System.out.println(&quot;Hello!&quot;)).start();`.

Example(s):
```
public class GreetingThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello!");
    }
}
new GreetingThread().start(); // not recommended, use Runnable instead

public class GreetingRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello!");
    }
}
new Thread(new GreetingRunnable()).start(); // preferred
```
