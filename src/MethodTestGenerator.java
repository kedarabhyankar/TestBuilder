import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * Build a {@code MethodTest} from a class name, method name, and an array of parameters. You can then
 * use this to check against another {@code MethodTest} to check for equality.
 *
 * @author kedarabhyankar
 * @version 11/07/2020
 */
@SuppressWarnings("unused")
public class MethodTestGenerator {

    /**
     * Generate a {@code MethodTest} given a class name, method name, and an array of parameters.
     *
     * @param className  the class name where this method exists
     * @param methodName the method name to examine
     * @param parameters the list of parameters of the method, or null if no parameters are expected
     * @return the built {@code MethodTest}.
     */
    public static MethodTest generateMethodTest(String className, String methodName,
                                                Class<?>... parameters) {
        if (className == null || methodName == null || className.isEmpty() || methodName.isEmpty()) {
            return null;
        }

        Class<?> classReference;
        try {
            classReference = Class.forName(className);
        } catch (ClassNotFoundException e) {
            return new MethodTestFailure(e);
        }

        Method classReferenceMethod;
        try {
            classReferenceMethod = classReference.getDeclaredMethod(methodName, parameters);
        } catch (NoSuchMethodException e) {
            return new MethodTestFailure(e);
        }

        String modifierString = Modifier.toString(classReferenceMethod.getModifiers());
        String[] modifiers = modifierString.substring(1, modifierString.length() - 1).split(" ");

        String[] mods = new String[modifiers.length];
        int modsPtr = 0;
        for (String s : modifiers) {
            String modName = s.substring(0, s.indexOf("="));
            boolean isMod = Boolean.parseBoolean(s.substring(s.indexOf("=")));
            if (isMod) {
                mods[modsPtr++] = modName;
            }
        }

        return new MethodTest().setMethodName(methodName).setModifiers(mods).
                setThrows(classReferenceMethod.getExceptionTypes()).
                setParameters(parameters).
                setReturnType(classReferenceMethod.getReturnType()).build();
    }
}
