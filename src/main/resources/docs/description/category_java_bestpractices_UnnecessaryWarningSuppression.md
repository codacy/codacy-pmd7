Since: PMD 7.14.0

This rule reports suppression comments and annotations that did not suppress any PMD violation.
            Note that violations of this rule cannot be suppressed.

            Please note:
            - The rule will report those suppressions comments/annotations that did not suppress a violation
            _during the current run_. That means you cannot run this rule separately from other rules, it must
            always be run with all the rules that could produce a warning. This is most likely not a problem, as
            you can just include this rule in your regular ruleset.
            - The rule for now only reports annotations specific to PMD, like `@SuppressWarnings(&quot;PMD&quot;)`. For instance
            `@SuppressWarnings(&quot;all&quot;)` is never reported as we cannot know if another tool is producing a
            warning there that must be suppressed. In the future we might be able to check for other common ones
            like `@SuppressWarnings(&quot;unchecked&quot;)` or `&quot;fallthrough&quot;`.

Example(s):
```
public class Something {
                // Unless some rule triggered on the following line, this rule will report the comment:
                private void foo() {} // NOPMD
            }
```
