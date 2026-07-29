Since: PMD 7.17.0

Be sure to specify a character set for APIs that use the JVM's default character set to ensure
stable encoding behavior between different JVMs, programs, and servers. Using the platform's
default charset makes the code less portable and might lead to unexpected behavior when running
on different systems.

Additional, since Java 18, the default charset for these APIs is consistently UTF-8
(see [JEP 400](https://openjdk.org/jeps/400)). While this reduces unexpected behavior
on different systems, it is still advised to explicitly specify a character set,
especially if UTF-8 is not the desired charset.

Example(s):
```
public class Foo {
    void bad() throws IOException {
        new InputStreamReader(inputStream);  // violation
        new OutputStreamWriter(outputStream);  // violation
        URLEncoder.encode("test string");  // violation (deprecated)
        new PrintStream(outputStream);  // violation
        new PrintWriter("output.txt");  // violation
        new Scanner(inputStream);  // violation
        new Formatter();  // violation
        "test".getBytes();  // violation
        new ByteArrayOutputStream().toString();  // violation
        new FileReader("input.txt");  // violation
        new FileWriter("output.txt");  // violation
    }

    void good() throws IOException {
        new InputStreamReader(inputStream, StandardCharsets.UTF_8);  // ok
        new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);  // ok
        URLEncoder.encode("test string", StandardCharsets.UTF_8);  // ok
        new PrintStream(outputStream, true, StandardCharsets.UTF_8);  // ok
        new PrintWriter("output.txt", StandardCharsets.UTF_8);  // ok
        new Scanner(inputStream, StandardCharsets.UTF_8);  // ok
        new Formatter(Locale.US);  // ok
        "test".getBytes(StandardCharsets.UTF_8);  // ok
        new ByteArrayOutputStream().toString(StandardCharsets.UTF_8);  // ok
        new FileReader("input.txt", StandardCharsets.UTF_8);  // ok
        new FileWriter("output.txt", StandardCharsets.UTF_8);  // ok
    }
}
```
