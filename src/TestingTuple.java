/**
 * A 'tuple' representative of a test case. The boolean value indicates the test success state, and the
 * message represents the message if the test failed. Typically, failed tests will have a message, while
 * tests that succeed will have an empty string as a message.
 *
 * @author kedarabhyankar
 * @version 11/09/2020
 */
public class TestingTuple {

    private final boolean testState;
    private final String testMessage;

    public TestingTuple(boolean testState, String testMessage) {
        this.testState = testState;
        this.testMessage = testMessage;
    }

    public boolean getTestState() {
        return this.testState;
    }

    public String getTestMessage() {
        return testMessage;
    }
}
