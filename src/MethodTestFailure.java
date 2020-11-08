public class MethodTestFailure extends MethodTest {

    private final Exception exception;

    public MethodTestFailure(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
