Since: PMD 7.24.0

In Apex, when a `Map` uses an interface as key and an abstract class implements that interface
and defines `equals`/`hashCode`, methods like `containsKey` do not dispatch to the correct implementation.
This results in potentially duplicated map entries or not being able to get entries by key.

This rule reports `Map` declarations (fields, variables, parameters) whose key type is an interface that
has at least one abstract implementing class defining `equals` or `hashCode`.

[Apex Language Server](https://github.com/apex-dev-tools/apex-ls) is used to make this possible and this needs
additional configuration. The environment variable `PMD_APEX_ROOT_DIRECTORY` needs to be set prior to executing
PMD. With this variable the root directory of the Salesforce metadata, where `sfdx-project.json` resides, is
specified. Apex Language Server can then load all the types in the project and figure out the type hierarchy.

Example(s):
```
public class Outer {
    public interface IKey {
        Boolean equals(Object obj);
        Integer hashCode();
    }
    public abstract class AbstractKey implements IKey {
        protected abstract String getKeyValue();
        public Boolean equals(Object obj) {
            if (obj instanceof AbstractKey) {
                return ((AbstractKey) obj).getKeyValue() == this.getKeyValue();
            }
            return false;
        }
        public Integer hashCode() {
            String v = getKeyValue();
            return v != null ? System.hashCode(v) : 0;
        }
    }
    public class StringKey extends AbstractKey {
        private String value;
        public StringKey(String value) {
            this.value = value;
        }
        protected override String getKeyValue() {
            return value;
        }
    }

    public void method() {
        Map<IKey, String> m = new Map<IKey, String>(); // violation: IKey has abstract implementor with equals/hashCode

        m.put(new StringKey('hello'), 'value');
        Boolean exists = m.containsKey(new StringKey('hello')); // might return false
        m.put(new StringKey('hello'), 'other value'); // might add a second entry instead of replacing the existing one
    }
}
```
