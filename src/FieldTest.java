import java.util.Arrays;

public class FieldTest {

    private String fieldName;
    private Class<?> fieldType;
    private int[] modifiers;

    public FieldTest() {
        //do nothing
    }

    /**
     * Set the expected field name.
     *
     * @param fieldName
     * @return the modified {@code FieldTest} object.
     */
    public FieldTest setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    /**
     * Set the expected field type for a field.
     *
     * @param fieldType the class representative of the type of this field.
     * @return the modified {@code FieldTest} object.
     */
    public FieldTest setFieldType(Class<?> fieldType) {
        this.fieldType = fieldType;
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
     * A build method to satisfy the methdo contract of a builder framework.
     *
     * @return the built {@code FieldTest}.
     */
    public FieldTest build() {
        return this;
    }

    /**
     * Determine if equality exists between two {@code FieldTest} objects. Invoke this method from the
     * expected object, and pass the actual object to it.
     *
     * @param fieldTest The field to test equality against.
     * @return a boolean value indicative of if the two Fields are equal.
     */
    public boolean assertEquality(FieldTest fieldTest) {
        return this.fieldName.equals(fieldTest.fieldName) &&
                this.fieldType.equals(fieldTest.fieldType) &&
                Arrays.equals(this.modifiers, fieldTest.modifiers);
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
