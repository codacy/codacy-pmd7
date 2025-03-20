Since: PMD 7.11.0

Using instance variables of the following types (or collections of these types) within a stateful batch class can cause serialization errors between batch iterations:

      - `Database.DeleteResult`
      - `Database.EmptyRecycleBinResult`
      - `Database.MergeResult`
      - `Database.SaveResult`
      - `Database.UndeleteResult`
      - `Database.UpsertResult`

      This error occurs inconsistently and asynchronously with an obscure error message - making it particularly challenging to troubleshoot.
      See [this issue](https://issues.salesforce.com/issue/a028c00000qPwlqAAC/stateful-batch-job-that-stores-databasesaveresult-failed-after-validation-errors-throws-error-during-deserialization) for more details.

      These errors can be avoided by marking the variable as static, transient, or using a different
      data type that is safe to serialize.

Example(s):
```
// Violating
public class Example implements Database.Batchable<SObject>, Database.Stateful {
  List<Database.SaveResult> results = new List<Database.SaveResult>(); // This can cause failures

  public Database.Querylocator start(Database.BatchableContext context) {
    return Database.getQueryLocator('SELECT Id FROM Account');
  }

  public void execute(Database.BatchableContext context, List<SObject> scope) {
    Database.SaveResult[] saveResults = Database.update(scope, false);
    results.addAll(saveResults);
  }

  public void finish(database.BatchableContext context) {
  }
}

// Compliant
public class Example implements Database.Batchable<SObject>, Database.Stateful {
  List<StatefulResult> results = new List<StatefulResult>(); // Use a different custom type to persist state

  public Database.Querylocator start(Database.BatchableContext context) {
    return Database.getQueryLocator('SELECT Id FROM Account');
  }

  public void execute(Database.BatchableContext context, List<SObject> scope) {
    Database.SaveResult[] saveResults = Database.update(scope, false);
    for (Database.SaveResult result : saveResults) {
      results.add(new StatefulResult(result));
    }
  }

  public void finish(database.BatchableContext context) {
  }

}

public class StatefulResult {
  private Boolean isSuccess;
  private Id id;
  private Database.Error[] errors;

  public StatefulResult(Database.SaveResult result) {
    isSuccess = result.isSuccess();
    id = result.getId();
    errors = result.getErrors();
  }

  public Boolean isSuccess() {
    return isSuccess;
  }

  public Id getId() {
    return id;
  }

  public Database.Error[] getErrors() {
    return errors;
  }
}
```
