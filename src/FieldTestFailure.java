public class FieldTestFailure extends FieldTest {

    private final Exception exception;

    public FieldTestFailure(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
