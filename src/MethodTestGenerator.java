import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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
        // Check for bad entries and return null if entry is considered invalid.
        if (className == null || methodName == null || className.isEmpty() || methodName.isEmpty()) {
            return null;
        }

        // Grab the class referenced by the {@code String} variant of the class name
        Class<?> classReference;
        try {
            classReference = Class.forName(className);
        } catch (ClassNotFoundException e) {
            //if class can't be found, a MethodTestFailure is thrown
            return new MethodTestFailure(e);
        }

        // Now that you have the class referenced, grab the method by its name and parameters
        Method classReferenceMethod;
        try {
            classReferenceMethod = classReference.getDeclaredMethod(methodName, parameters);
        } catch (NoSuchMethodException e) {
            //if no method by this exists, then throw MethodTestFailure
            return new MethodTestFailure(e);
        }

        // Convert text modifiers to actual modifiers
        String modifierString = Modifier.toString(classReferenceMethod.getModifiers());
        //split modifiers by braces and then by space
        String[] modifiers = modifierString.split(" ");

        // return a built method with all the parts from before
        return new MethodTest().setMethodName(methodName).setModifiers(modifiers).
                setThrows(classReferenceMethod.getExceptionTypes()).
                setParameters(parameters).
                setReturnType(classReferenceMethod.getReturnType()).build();
    }
}
