/**
 * A specialized way to assume a failure state for a Field.
 *
 * @author kedarabhyankar
 * @version 11/07/2020
 */
public class FieldTestFailure extends FieldTest {

    private final Exception exception;

    public FieldTestFailure(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
