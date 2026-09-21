//#Patterns: category_java_design_ProtectedMemberInFinalClass

public final class Bar {
  private int x;

  //#Warn: category_java_design_ProtectedMemberInFinalClass
  protected int y;  // bar cannot be subclassed, so is y really private or package visible?

  Bar() {}

  private int bar() {
    return 0;
  }

  //#Warn: category_java_design_ProtectedMemberInFinalClass
  protected int baz() {
    return 0;
  }
}
