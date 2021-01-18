import java.util.Arrays;

/**
 * A specialized way to build a specific test case for a Field. Fields are then compared for equality
 * by invocation of the {@code assertEquality} method.
 *
 * @author kedarabhyankar
 * @version 07/31/2021
 */
@SuppressWarnings("unused")
public class FieldTest {

    /**
     * The name of this field
     */
    private String fieldName;

    /**
     * The alternative names for this field
     */
    private String[] alternativeNames;

    /**
     * The type of this field
     */
    private Class<?> fieldType;
    /**
     * The modifier(s) of this field
     */
    private int[] modifiers;

    /**
     * A default constructor for an {@code FieldTest}.
     */
    public FieldTest() {
        //do nothing
        this.alternativeNames = new String[1];
    }

    /**
     * Set the expected field name.
     *
     * @param fieldName The name of this field.
     * @return the modified {@code FieldTest} object.
     */
    @SuppressWarnings("unused")
    public FieldTest setFieldName(String fieldName) {
        this.fieldName = fieldName;
        this.alternativeNames[0] = fieldName;
        return this;
    }

    /**
     * Set the expected field type for a field.
     *
     * @param fieldType the class representative of the type of this field.
     * @return the modified {@code FieldTest} object.
     */
    @SuppressWarnings("unused")
    public FieldTest setFieldType(Class<?> fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    /**
     * Add an alternative name to the field.
     *
     * @param alternativeName An alternative name to add to the field
     * @return the modified {@code FieldTest} object.
     */
    public FieldTest addAlternativeName(String alternativeName) {
        String[] tempAlternativeNames = new String[this.alternativeNames.length];
        System.arraycopy(alternativeNames, 0, tempAlternativeNames, 0, alternativeNames.length);
        this.alternativeNames = new String[tempAlternativeNames.length + 1];
        System.arraycopy(tempAlternativeNames, 0, this.alternativeNames, 0, tempAlternativeNames.length);
        this.alternativeNames[tempAlternativeNames.length] = alternativeName;
        return this;
    }

    /**
     * Adds an expected modifier to the {@code FieldTest}.
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
    public FieldTest setModifiers(String[] modifierNames) {
        this.modifiers = new int[12];
        for (String s : modifierNames) {
            //map the modifiers into int descriptors
            observeModifier(s);
        }
        return this;
    }

    /**
     * A build method to satisfy the method contract of a builder framework.
     *
     * @return the built {@code FieldTest}.
     */
    @SuppressWarnings("unused")
    public FieldTest build() {
        return this;
    }

    /**
     * Get a String array of modifiers representative of the modifiers that the method that
     * the {@code MethodTest} object represents.
     *
     * @return A modifier list of the modifiers applicable to the method
     */
    @SuppressWarnings("DuplicatedCode")
    private String[] getNamedModifiers() {
        //count number of valid modifiers
        //noinspection DuplicatedCode
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
     * Determine if equality exists between two {@code FieldTest} objects. Invoke this method from the
     * expected object, and pass the actual object to it.
     *
     * @param fieldTest The field to test equality against.
     * @return a boolean value indicative of if the two Fields are equal.
     */
    @SuppressWarnings("unused")
    public TestingTuple assertEquality(FieldTest fieldTest) {
        if (!this.fieldName.equals(fieldTest.fieldName) || !compareNameEqualityAcrossAlternativeNames(fieldTest.fieldName)) {
            return new TestingTuple(false, "Ensure that your field names match! Your " +
                    "field name was " + fieldTest.fieldName + ", when it should have been " + this.fieldName + ".");
        }
        if (!this.fieldType.equals(fieldTest.fieldType)) {
            return new TestingTuple(false, "Ensure that your field type matches the" +
                    " expected type! Yours was of type " + fieldType.getName() + ", but it should have been " +
                    this.fieldType.getName() + ".");
        }
        if (!Arrays.equals(this.modifiers, fieldTest.modifiers)) {
            return new TestingTuple(false, "Ensure that your field modifiers match! Your" +
                    "field had modifiers " + Arrays.toString(fieldTest.getNamedModifiers()) + ", when it should" +
                    "have been " + Arrays.toString(this.getNamedModifiers()) + ".");
        }
        return new TestingTuple(true, "");
    }

    private boolean compareNameEqualityAcrossAlternativeNames(String fieldName) {
        for (String associatedName : this.alternativeNames) {
            if (fieldName.equals(associatedName)) {
                return true;
            }
        }
        return false;
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
