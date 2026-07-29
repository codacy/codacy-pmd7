Since: PMD 0.9

Methods with numerous parameters are a challenge to maintain and increase the risk of bugs.
            When parameters share similar datatypes, they become prone to mix-ups during refactoring or
            when calling the method with positional arguments. Additionally, long parameter lists violate
            the Single Responsibility Principle — they suggest the method is trying to do too much or that
            conceptually related data hasn't been properly grouped. As methods evolve, maintaining consistent
            parameter ordering across overloads and similar methods becomes increasingly difficult.

            Long parameter lists also make code harder to read and understand. Callers must carefully match
            arguments to parameters, and the parameter list itself becomes a visual barrier. This complexity
            increases the cognitive load for maintainers and leads to higher defect rates. The more parameters
            a method has, the less intuitive it becomes and the harder it is to test comprehensively,
            since the number of possible input combinations grows exponentially.

            There are several well-established alternatives to long parameter lists, each suited to different scenarios:
            the Builder Pattern (constructing objects fluently with named parameters),
            Multiple Parameter Objects (grouping related parameters into dedicated classes),
            Method Overloading (providing convenient signatures for common use cases),
            and Method Decomposition (splitting a single method into multiple, more focused methods that each take fewer parameters).

            Builder Pattern — Use when you want to replace a method call with many positional arguments
            with a fluent, named-parameter style. Each parameter is named at the call site, eliminating confusion
            about argument order and making the code self-documenting.

            Multiple Parameter Objects — Use when parameters naturally group into distinct semantic units.
            This is lighter than a full builder and works well when the parameter objects are stable
            and reused across multiple methods.

            Method Overloading — Use to provide convenient signatures for common cases without forcing callers
            to provide all possible parameters. Effective for APIs where there are genuinely frequent
            &quot;common use case&quot; patterns. Be cautious of overloading too many variants.

            Method Decomposition — Use when a single method is trying to do too much. Split it into focused methods,
            each with a clear responsibility and fewer parameters. Often combined with builder-style fluent APIs
            for controlled sequencing and initialization.

Example(s):
```
// ============ PROBLEM ============
public void createDatabaseConnection(String host, int port, String username, String password, int timeout, boolean ssl) { }

// Usage - hard to understand what each argument means
createDatabaseConnection("localhost", 5432, "admin", "secret", 30, true);


// ============ SOLUTION 1: Builder Pattern ============
public class ConnectionBuilder { [...] }

// Usage - clear and self-documenting
createDatabaseConnection(new ConnectionBuilder()
    .withHost("localhost")
    .withPort(5432)
    .withCredentials("admin", "secret")
    .withTimeout(30)
    .withSSL(true)
    .build());


// ============ SOLUTION 2: Multiple Parameter Objects ============
public class ServerAddress { [...] }

public class DatabaseCredentials { [...] }

public class ConnectionOptions { [...] }

public void createDatabaseConnection(ServerAddress address, DatabaseCredentials credentials, ConnectionOptions options) { }

// Usage - semantic grouping makes intent clear
createDatabaseConnection(
    new ServerAddress("localhost", 5432),
    new DatabaseCredentials("admin", "secret"),
    new ConnectionOptions(30, true)
);


// ============ example for Method Overloading ============
public void sendEmail(String to, String subject, String body) {
    // Basic email with defaults
}

public void sendEmail(String to, String subject, String body, String[] cc) {
    // With cc recipients
}

public void sendEmail(String to, String subject, String body, String[] cc, String[] bcc, String from, boolean html) {  //NOPMD
    // Full featured
}

// Usage - call with only needed parameters
sendEmail("user@example.com", "Welcome", "Hello there");
sendEmail("user@example.com", "Welcome", "Hello there", new String[]{"manager@example.com"});
sendEmail("user@example.com", "Welcome", "<b>Hello</b>",
          new String[]{"manager@example.com"},
          new String[]{"admin@example.com"},
          "noreply@company.com",
          true);


// ============ Example for Method Decomposition ============
// Problem: ReportGenerator is called like this:
Report report = ReportGenerator.generate("Sales Report", "PDF", "Company Report", 16, true, "Page {n} of {total}", 1, "center", "John Smith", LocalDate.now(), "1.0"

// Solution: Allow your code to be called like this:
ReportGenerator generator = new ReportGenerator();
generator.startReport("Sales Report", "PDF");
generator.addHeader("Company Report", 16, true);
generator.addFooter("Page {n} of {total}", 1, "center");
generator.addMetadata("John Smith", LocalDate.now(), "1.0");
Report report = generator.generate();
```
