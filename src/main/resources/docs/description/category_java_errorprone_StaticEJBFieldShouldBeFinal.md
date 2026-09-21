Since: PMD 4.1

According to the J2EE specification, an EJB should not have any static fields
with write access. However, static read-only fields are allowed. This ensures proper
behavior especially when instances are distributed by the container on several JREs.

Example(s):
```
public class SomeEJB extends EJBObject implements EJBLocalHome {

    private static int CountA;          // poor, field can be edited

    private static final int CountB;    // preferred, read-only access
}

// Since EJB 3.0, components may be declared with @Stateless / @Stateful /
// @Singleton / @MessageDriven instead of implementing an EJB interface.
@Stateless
public class MySessionBean {

    private static int CountC;          // poor, field can be edited
}
```
