//#Patterns: category_java_errorprone_UseEqualsToCompareStrings

public class Foo {

  public boolean test(String s) {
      //#Warn: category_java_errorprone_UseEqualsToCompareStrings
      if (s == "one") return true; // unreliable if (\"two\".equals(s)) return true; // better return false;
  }

}

