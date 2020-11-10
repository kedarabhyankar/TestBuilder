import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * A specialized way to build a specific test case for a Method. Methods are then compared for equality
 * by invocation of the {@code assertEquality} method.
 *
 * @author kedarabhyankar
 * @version 11/07/2020
 */
@SuppressWarnings("unused")
public class MethodTest {

    private String methodName;
    private int[] modifiers;
    private Class<?>[] throwsClazz;
    private Parameter[] parameterClazz;
    private Class<?> returnType;

    /**
     * The {@code MethodTest} constructor. In order to use this test builder, use this as a base and then build a
     * test case out of it.
     */
    public MethodTest() {
        //do nothing; this can't be used to make a test case. Build from here!
    }

    /**
     * Set the expected method name
     *
     * @param methodName the method name to set
     * @return the {@code MethodTest} with the newly built expected method name.
     */
    @SuppressWarnings("unused")
    public MethodTest setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    /**
     * Adds an expected modifier to the {@code MethodTest}.
     *
     * @param modifierName the modifier to expect
     */
    @SuppressWarnings("unused")
    private void addModifier(String modifierName) {
        if (this.modifiers == null) {
            this.modifiers = new int[12];
        }
        observeModifier(modifierName);
    }

    /**
     * Adds an expected set of modifiers to the {@code MethodTest}
     *
     * @param modifierNames the modifiers to expect.
     * @return the {@code MethodTest} with the newly built expected modifiers.
     */
    @SuppressWarnings("unused")
    public MethodTest setModifiers(String[] modifierNames) {
        this.modifiers = new int[12];
        for (String s : modifierNames) {
            //map the modifiers into int descriptors
            observeModifier(s);
        }
        return this;
    }

    /**
     * Set the throws for this method, if any.
     *
     * @param throwsClazz an array of classes representing the exceptions thrown.
     * @return the built {@code MethodTest}.
     */
    public MethodTest setThrows(Class<?>[] throwsClazz) {
        for (Class<?> c : throwsClazz) {
            this.addThrowsClause(c);
        }
        return this;
    }

    /**
     * A method to add a throws clause to the expected method.
     *
     * @param throwClazz the class to throw. The class passed in must extend exception!
     */
    @SuppressWarnings("unused")
    private void addThrowsClause(Class<?> throwClazz) {
        if (!throwClazz.getSuperclass().equals(Exception.class)) return;
        if (this.throwsClazz == null) {
            this.throwsClazz = new Class<?>[1];
        } else {
            Class<?>[] temp = new Class<?>[this.throwsClazz.length];
            System.arraycopy(throwsClazz, 0, temp, 0, throwsClazz.length);
            this.throwsClazz = new Class<?>[temp.length + 1];
            System.arraycopy(temp, 0, this.throwsClazz, 0, temp.length);
            this.throwsClazz[temp.length] = throwClazz;
        }
    }

    /**
     * A method to set the parameters of a {@code MethodTest}.
     *
     * @param parameters the parameters to add to a {@code MethodTest}
     * @return the new {@code MethodTest}.
     */
    public MethodTest setParameters(Parameter[] parameters) {
        for (Parameter p : parameters) {
            this.addParameter(p);
        }
        return this;
    }

    /**
     * Add a parameter to this {@code MethodTest} object.
     *
     * @param paramClazz the parameter to add to the {@code MethodTest}.
     */
    @SuppressWarnings("unused")
    private void addParameter(Parameter paramClazz) {
        if (this.parameterClazz == null) {
            this.parameterClazz = new Parameter[1];
            this.parameterClazz[0] = paramClazz;
        }

        Parameter[] tempClazz = new Parameter[this.parameterClazz.length];
        //noinspection ManualArrayCopy
        for (int i = 0; i < this.parameterClazz.length; i++) {
            tempClazz[i] = this.parameterClazz[i];
        }

        this.parameterClazz = new Parameter[this.parameterClazz.length + 1];
        //noinspection ManualArrayCopy
        for (int i = 0; i < tempClazz.length; i++) {
            this.parameterClazz[i] = tempClazz[i];
        }
        this.parameterClazz[tempClazz.length] = paramClazz;
    }

    public MethodTest setReturnType(Class<?> clazz) {
        this.returnType = returnType;
        return this;
    }

    /**
     * A build method to return the object. Should be called at the very end of the builder calls.
     *
     * @return this {@code MethodTest}.
     */
    public MethodTest build() {
        return this;
    }

    /**
     * Get a String array of modifiers representative of the modifiers that the method that
     * the {@code MethodTest} object represents.
     *
     * @return A modifier list of the modifiers applicable to the method
     */
    private String[] getNamedModifiers() {
        //count number of valid modifiers
        int numMods = 0;
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < modifiers.length; i++) {
            if (modifiers[i] == 1) {
                numMods++;
            }
        }

        String[] namedModifiers = new String[numMods];
        int arrPtr = 0;
        if (modifiers[0] == 1) {
            namedModifiers[arrPtr++] = "public";
        }
        if (modifiers[1] == 1) {
            namedModifiers[arrPtr++] = "private";
        }
        if (modifiers[2] == 1) {
            namedModifiers[arrPtr++] = "protected";
        }
        if (modifiers[3] == 1) {
            namedModifiers[arrPtr++] = "static";
        }
        if (modifiers[4] == 1) {
            namedModifiers[arrPtr++] = "final";
        }
        if (modifiers[5] == 1) {
            namedModifiers[arrPtr++] = "transient";
        }
        if (modifiers[6] == 1) {
            namedModifiers[arrPtr++] = "abstract";
        }
        if (modifiers[7] == 1) {
            namedModifiers[arrPtr++] = "native";
        }
        if (modifiers[8] == 1) {
            namedModifiers[arrPtr++] = "interface";
        }
        if (modifiers[9] == 1) {
            namedModifiers[arrPtr++] = "strict";
        }
        if (modifiers[10] == 1) {
            namedModifiers[arrPtr++] = "synchronized";
        }
        if (modifiers[11] == 1) {
            //noinspection UnusedAssignment
            namedModifiers[arrPtr++] = "volatile";
        }

        return namedModifiers;
    }

    /**
     * A method to check if two methods are equal. Invoke this from the expected object, and pass the
     * generated MethodTest to this method to compare it for equality.
     *
     * @param methodTest the {@code MethodTest} to compare equality. Generally, this is the actual method
     *                   to compare against
     * @return true if equal, false if not.
     */
    @SuppressWarnings("unused")
    public TestingTuple assertEquality(MethodTest methodTest) {
        if (!this.methodName.equals(methodTest.methodName)) {
            return new TestingTuple(false, "Ensure that your methods have the correct" +
                    "name! Your method is named " + methodTest.methodName + ", and it should be named " +
                    methodName);
        }
        if (!Arrays.equals(parameterClazz, methodTest.parameterClazz)) {
            return new TestingTuple(false, "Ensure that your parameters " +
                    "match between methods! For the method " + this.methodName + ", your parameters were " +
                    Arrays.toString(methodTest.parameterClazz) + ", when they should have been " +
                    Arrays.toString(parameterClazz) + ".");
        }
        if (!Arrays.equals(throwsClazz, methodTest.throwsClazz)) {
            return new TestingTuple(false, "Ensure that your throws statements match what's" +
                    "expected! For the method " + this.methodName + ", your throws statements were " +
                    Arrays.toString(methodTest.throwsClazz) + ", when they should have been " +
                    Arrays.toString(throwsClazz) + ".");
        }
        if (!Arrays.equals(this.modifiers, methodTest.modifiers)) {
            return new TestingTuple(false, "Ensure that your modifiers match what's expected!" +
                    " For the method " + this.methodName + ", your modifiers were " +
                    Arrays.toString(methodTest.getNamedModifiers()) + ", when they should have been " +
                    Arrays.toString(this.getNamedModifiers()) + ".");
        }
        if (this.returnType.equals(methodTest.returnType)) {
            return new TestingTuple(false, "Ensure that your return type match what's expected!" +
                    " For the method " + this.methodName + ", you have a return type of " +
                    methodTest.returnType + ", when it should be " + this.returnType + ".");
        }

        return new TestingTuple(true, "");
    }


    /**
     * Updates the modifier array with the given state for its given name. Modifiers are organized in the
     * array in the following fashion.
     * <p>
     * [0] -> public
     * [1] -> private
     * [2] -> protected
     * [3] -> static
     * [4] -> final
     * [5] -> transient
     * [6] -> abstract
     * [7] -> native
     * [8] -> interface
     * [9] -> strict
     * [10] -> synchronized
     * [11] -> volatile
     *
     * @param modifierName The modifier name to set, in all lowercase.
     */
    private void observeModifier(String modifierName) {
        switch (modifierName) {
            case "public" -> modifiers[0] = 1;
            case "private" -> modifiers[1] = 1;
            case "protected" -> modifiers[2] = 1;
            case "static" -> modifiers[3] = 1;
            case "final" -> modifiers[4] = 1;
            case "transient" -> modifiers[5] = 1;
            case "abstract" -> modifiers[6] = 1;
            case "native" -> modifiers[7] = 1;
            case "interface" -> modifiers[8] = 1;
            case "strict" -> modifiers[9] = 1;
            case "synchronized" -> modifiers[10] = 1;
            case "volatile" -> modifiers[11] = 1;
        }
    }
}
