Since: PMD 1.2.2

Ensure that resources (like `java.sql.Connection`, `java.sql.Statement`, and `java.sql.ResultSet` objects
and any subtype of `java.lang.AutoCloseable`) are always closed after use.
Failing to do so might result in resource leaks.

Note: It suffices to configure the super type, e.g. `java.lang.AutoCloseable`, so that this rule automatically triggers
on any subtype (e.g. `java.io.FileInputStream`). Additionally specifying `java.sql.Connection` helps in detecting
the types, if the type resolution / auxclasspath is not correctly setup.

Note: Since PMD 6.16.0 the default value for the property `types` contains `java.lang.AutoCloseable` and detects
now cases where the standard `java.io.*Stream` classes are involved. In order to restore the old behaviour,
just remove &quot;AutoCloseable&quot; from the types.

The property `allowedResourceMethodPatterns` can be used to specify method invocation patterns that return
resources which are managed externally and don't need to be closed by the caller. This is useful for
servlet-related streams like `HttpServletRequest.getReader()` or `HttpServletResponse.getWriter()`,
which are managed by the servlet container, and for mocking frameworks, whose mocks are not real
resources even when the mocked type implements `java.lang.AutoCloseable`. The patterns use
InvocationMatcher syntax (e.g., `javax.servlet.ServletRequest#getReader()`).

The defaults cover the servlet API and Mockito's `mock()`. Mocks created by other frameworks
can be allowed by adding their factory methods, for example `org.easymock.EasyMock#createMock(_*)`,
`org.jmock.Mockery#mock(_*)` or `mockit.Mocked#new(_*)`.

Note that `Mockito.spy()` and `Mockito.mockStatic()` are intentionally not among the defaults.
A spy calls through to the real object by default, so either the spy or the object it wraps still
has to be closed; `mockStatic()` returns a `MockedStatic`, which has to be closed as well. A violation
in either case is a true positive.

Example(s):
```
public class Bar {
    public void withSQL() {
        Connection c = pool.getConnection();
        try {
            // do stuff
        } catch (SQLException ex) {
           // handle exception
        } finally {
            // oops, should close the connection using 'close'!
            // c.close();
        }
    }

    public void withFile() {
        InputStream file = new FileInputStream(new File("/tmp/foo"));
        try {
            int c = file.in();
        } catch (IOException e) {
            // handle exception
        } finally {
            // TODO: close file
        }
    }
}
```
