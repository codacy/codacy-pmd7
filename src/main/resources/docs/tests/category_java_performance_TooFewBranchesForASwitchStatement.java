//#Patterns: category_java_performance_TooFewBranchesForASwitchStatement


public class Foo {
    public void bar() {
//#Warn: category_java_performance_TooFewBranchesForASwitchStatement
        switch (condition) {
            case ONE:
                instruction;
                break;
            default:
                break; // not enough for a 'switch' stmt, a simple 'if' stmt would have been more appropriate
        }
    }
    public void bar2() {
	    switch (condition) {
	        case 1:
	            instruction;
	            break;
	        case 2:
	            instruction;
	            break;
	   		case 3:
	            instruction;
	            break;
	        default:
	            break; // not enough for a 'switch' stmt, a simple 'if' stmt would have been more appropriate
    	}
	}
}
