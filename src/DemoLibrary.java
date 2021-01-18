import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

public class DemoLibrary {

    @Test
    public void testOne() {
        MethodTest getNameTest = MethodTestGenerator.generateMethodTest("Something", "getName");
        MethodTest getNameExpected = new MethodTest().setMethodName("getName").
                setModifiers(new String[]{"public"}).
                setReturnType(String.class);
        TestingTuple res = getNameExpected.assertEquality(getNameTest);
        if (!res.getTestState()) {
            fail(res.getTestMessage());
        }
    }
}
