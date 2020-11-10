/**
 * A specialized way to assume a failure state for a Method.
 *
 * @author kedarabhyankar
 * @version 11/07/2020
 */
public class MethodTestFailure extends MethodTest {

    private final Exception exception;

    public MethodTestFailure(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
