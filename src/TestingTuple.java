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
