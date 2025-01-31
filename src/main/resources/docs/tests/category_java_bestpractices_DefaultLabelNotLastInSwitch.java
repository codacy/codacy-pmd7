//#Patterns: category_java_bestpractices_DefaultLabelNotLastInSwitch
   
public class Foo {
  void bar(int a) {
   switch (a) {
    case 1:  // do something
       break;
   //#Warn: category_java_bestpractices_DefaultLabelNotLastInSwitch
    default:  // the default case should be last, by convention
       break;
    case 2:
       break;
   }
  }
}   
       

