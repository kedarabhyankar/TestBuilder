import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Build a {@code MethodTest} from a class name, method name, and an array of parameters. You can then
 * use this to check against another {@code MethodTest} to check for equality.
 *
 * @author kedarabhyankar
 * @version 11/07/2020
 */
public class MethodTestGenerator {

    public static MethodTest generateMethodTest(String className, String methodName,
                                                Class<?>... parameters) {
        if (className == null || methodName == null || className.isEmpty() || methodName.isEmpty()) {
            return null;
        }

        Class<?> classReference;
        try {
            classReference = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        Method classReferenceMethod;
        try {
            classReferenceMethod = classReference.getDeclaredMethod(methodName, parameters);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }

        String modifierString = Modifier.toString(classReferenceMethod.getModifiers());
        String[] modifiers = modifierString.substring(1, modifierString.length() - 1).split(" ");
        MethodTest methodTest = new MethodTest();
        methodTest.setMethodName(methodName);
        for (String s : modifiers) {
            String modName = s.substring(0, s.indexOf("="));
            boolean isMod = Boolean.parseBoolean(s.substring(s.indexOf("=")));
            if (isMod) {
                methodTest.addModifier(modName);
            }
        }

        Class<?>[] throwzz = classReferenceMethod.getExceptionTypes();
        for (Class<?> t : throwzz) {
            methodTest.addThrowsClause(t);
        }


        return methodTest;
    }
}
