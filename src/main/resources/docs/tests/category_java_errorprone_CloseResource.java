//#Patterns: category_java_errorprone_CloseResource

public class Bar {
    public void foo() {
        //#Warn: category_java_errorprone_CloseResource
        Connection c = pool.getConnection();
        try {
            // do stuff
        } catch (SQLException ex) {
            // handle exception
        } finally {
            // oops, should close the connection using 'close'!

            // c.close();
        }

        Connection c2 = pool.getConnection();
        try {
            // do stuff
        } catch (SQLException ex) {
            // handle exception
        } finally {
            // oops, should close the connection using 'close'!

            c2.close();
        }
    }
}

