Since: PMD 4.2.5

Super should be called at the start of the method

This rule is intended for Android development. Don't use for general purpose java development!

Example(s):
```
import android.app.Activity;
import android.os.Bundle;

public class DummyActivity extends Activity {
    public void onCreate(Bundle bundle) {
        // missing call to super.onCreate(bundle)
        foo();
    }
}
```
