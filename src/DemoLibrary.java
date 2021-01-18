import org.junit.Test;

import static org.junit.Assert.fail;

public class DemoLibrary {

    @Test(timeout = 1000)
    public void testSimpleMethodExample() {
        MethodTest getNameTest = MethodTestGenerator.generateMethodTest("Something", "getName");
        MethodTest getNameExpected = new MethodTest().setMethodName("getName").
                setModifiers(new String[]{"public"}).
                setReturnType(String.class).build();
        TestingTuple res = getNameExpected.assertEquality(getNameTest);
        if (!res.getTestState()) {
            fail(res.getTestMessage());
        }
    }

    @Test(timeout = 1000)
    public void testFieldExample() {
        FieldTest nameFieldTest = FieldTestGenerator.generateFieldTest("Something", "name").
                setModifiers(new String[]{"private"});
        FieldTest fieldTestExpected = new FieldTest().setFieldName("name").
                setFieldType(String.class).
                setModifiers(new String[]{"private"}).build();
        TestingTuple res = fieldTestExpected.assertEquality(nameFieldTest);
        if (!res.getTestState()) {
            fail(res.getTestMessage());
        }
    }
}
