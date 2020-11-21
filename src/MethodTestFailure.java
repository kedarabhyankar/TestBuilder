/**
 * A specialized way to assume a failure state for a Method.
 *
 * @author kedarabhyankar
 * @version 11/07/2020
 */
public class MethodTestFailure extends MethodTest {

    /**
     * The child exception for this {@code MethodTestFailure}.
     */
    private final Exception exception;

    /**
     * The {@code Exception} thrown with a {@code String} parameter.
     * @param exception the {@code Exception} thrown with a {@code String} parameter.
     */
    public MethodTestFailure(Exception exception) {
        this.exception = exception;
    }

    /**
     * The exception header with no parameters
     * @return the {@code Exception} thrown with no parameter.
     */
    public Exception getException() {
        return exception;
    }
}
