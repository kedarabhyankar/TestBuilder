/**
 * A 'tuple' representative of a test case. The boolean value indicates the test success state, and the
 * message represents the message if the test failed. Typically, failed tests will have a message, while
 * tests that succeed will have an empty string as a message.
 *
 * @author kedarabhyankar
 * @version 11/09/2020
 */
public class TestingTuple {

    /**
     * the state of the test case - true if passed, false if failed
     */
    private final boolean testState;
    /**
     * The message of the test case. If the test passed, then an empty string, otherwise a message is given.
     */
    private final String testMessage;

    /**
     * A constructor for an {@code TestingTuple}. Takes in a state, representative of if the test failed or not
     * ( false if failed, true if passed), as well as a message. generally, if the test passed, the state is
     * true, and the message is an empty string. Conversely, if the test failed, the message is usually
     * populated with a specific error case.
     *
     * @param testState   The state of the test case, whether it passed or not.
     * @param testMessage the message associated with the test case state, either an empty string if passed,
     *                    or a string with information representative of the test case error if failed.
     */
    public TestingTuple(boolean testState, String testMessage) {
        this.testState = testState;
        this.testMessage = testMessage;
    }

    /**
     * A simple accessor method to get the state of if the test passed or not,
     *
     * @return the test state, true if passed, false if failed.
     */
    public boolean getTestState() {
        return this.testState;
    }

    /**
     * A simple accessor method to get the message associated with the test.
     *
     * @return the message associated with the test.
     */
    public String getTestMessage() {
        return testMessage;
    }
}
