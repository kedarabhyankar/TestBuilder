public class TestBuilder {

    private String methodName;
    private int[] modifiers;
    private Class<?>[] throwsClazz;

    /**
     * The {@code TestBuilder} constructor. In order to use this test builder, use this as a base and then build a
     * test case out of it.
     */
    public TestBuilder() {
        //do nothing; this can't be used to make a test case. Build from here!
    }

    /**
     * Set the expected method name
     *
     * @param methodName the method name to set
     * @return the {@code TestBuilder} with the newly built expected method name.
     */
    public TestBuilder setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    /**
     * Adds an expected modifier to the {@code TestBuilder}.
     *
     * @param modifierName the modifier to expect
     * @return the {@code TestBuilder} with the newly built modifier.
     */
    public TestBuilder addModifier(String modifierName) {
        if (this.modifiers == null) {
            this.modifiers = new int[12];
        }

        return observeModifier(modifierName, 1);
    }

    /**
     * Adds an expected set of modifiers to the {@code TestBuilder}
     *
     * @param modifierNames the modifiers to expect.
     * @return the {@code TestBuilder} with the newly built expected modifiers.
     */
    public TestBuilder setModifiers(String[] modifierNames) {
        this.modifiers = new int[12];
        for (String s : modifierNames) {
            //map the modifiers into int descriptors
            observeModifier(s, 1);
        }
        return this;
    }

    /**
     * Removes a modifier from the {@code TestBuilder}.
     *
     * @param modifierName the modifier to remove.
     * @return the {@code TestBuilder} with updated expected modifiers.
     */
    public TestBuilder removeModifier(String modifierName) {
        if (this.modifiers == null) return this;

        return observeModifier(modifierName, 0);
    }

    /**
     * Removes multiple modifiers from the {@code TestBuilder}.
     *
     * @param modifierNames the modifiers to remove.
     * @return the {@code TestBuilder} with updated expected modifiers.
     */
    public TestBuilder removeModifiers(String[] modifierNames) {
        if (modifierNames.length == 12) {
            this.modifiers = new int[12];
            return this;
        }
        for (String s : modifierNames) {
            observeModifier(s, 0);
        }
        return this;
    }

    /**
     * A method to add a throws clause to the expected method.
     *
     * @param throwClazz the class to throw. The class passed in must extend exception!
     * @return the {@code TestBuilder} with the updated throw clause.
     */
    public TestBuilder addThrowsClause(Class<?> throwClazz) {
        if (!throwClazz.getSuperclass().equals(Exception.class)) return this;
        if (this.throwsClazz == null) {
            this.throwsClazz = new Class<?>[1];
        } else {
            Class<?>[] temp = new Class<?>[this.throwsClazz.length];
            System.arraycopy(throwsClazz, 0, temp, 0, throwsClazz.length);
            this.throwsClazz = new Class<?>[temp.length + 1];
            System.arraycopy(temp, 0, this.throwsClazz, 0, temp.length);
            this.throwsClazz[temp.length] = throwClazz;
        }

        return this;
    }

    /**
     * Removes a specified throws clause from the {@code TestBuilder}.
     *
     * @param throwClazz the class to remove as a throwable. The class passed in must extend exception!
     * @return the {@code TestBuilder} with the updated throw clause.
     */
    public TestBuilder removeThrowsClause(Class<?> throwClazz) {
        if (!throwClazz.getSuperclass().equals(Exception.class) || this.throwsClazz == null) return this;
        for (int i = 0; i < this.throwsClazz.length; i++) {
            if (throwsClazz[i].equals(throwClazz)) {
                throwsClazz[i] = null;
                return this;
            }
        }
        return this;
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
     * @param state        the state, either 1 to expect it, or 0 to not expect it. By default, there are no
     *                     modifiers expected
     * @return the {@code TestBuilder} with the modified modifiers.
     */
    private TestBuilder observeModifier(String modifierName, int state) {
        switch (modifierName) {
            case "public" -> modifiers[0] = state;
            case "private" -> modifiers[1] = state;
            case "protected" -> modifiers[2] = state;
            case "static" -> modifiers[3] = state;
            case "final" -> modifiers[4] = state;
            case "transient" -> modifiers[5] = state;
            case "abstract" -> modifiers[6] = state;
            case "native" -> modifiers[7] = state;
            case "interface" -> modifiers[8] = state;
            case "strict" -> modifiers[9] = state;
            case "synchronized" -> modifiers[10] = state;
            case "volatile" -> modifiers[11] = state;
        }

        return this;
    }
}
