package executables.exceptions;

public class TestCaseException  extends Exception {
    public TestCaseException (String str) {
        // calling the constructor of parent Exception
        super(str);
    }
}
