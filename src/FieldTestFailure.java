/**
 * A specialized way to assume a failure state for a Field.
 *
 * @author kedarabhyankar
 * @version 11/07/2020
 */
public class FieldTestFailure extends FieldTest {

    /**
     * The child {@code Exception} of a {@code FieldTestFailure}.
     */
    private final Exception exception;

    /**
     * The parameterized constructor for a {@code FieldTestFailure}.
     * @param exception the {@code Exception} for a {@code FieldTestFailure}.
     */
    public FieldTestFailure(Exception exception) {
        this.exception = exception;
    }

    /**
     * The non parameterized constructor for a {@code FieldTestFailure}.
     * @return the {@code Exception} connected to this {@code FieldTestFailure}.
     */
    public Exception getException() {
        return exception;
    }
}
