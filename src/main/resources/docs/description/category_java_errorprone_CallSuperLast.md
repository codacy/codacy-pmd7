Since: PMD 4.2.5

Super should be called at the end of the method

This rule is intended for Android development. Don't use for general purpose java development!

Example(s):
```
import android.app.Activity;

public class DummyActivity extends Activity {
    public void onPause() {
        foo();
        // missing call to super.onPause()
    }
}
```
