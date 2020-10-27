public class TestBuilder {

    private String methodName;
    private int[] modifiers;

    public TestBuilder() {
        //do nothing; this can't be used to make a test case. Build from here!
    }

    public TestBuilder setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public TestBuilder addModifier(String modifierName) {
        if (this.modifiers == null) {
            this.modifiers = new int[12];
        }

        return observeModifier(modifierName, 1);
    }

    public TestBuilder setModifiers(String[] modifierNames) {
        this.modifiers = new int[12];
        for (String s : modifierNames) {
            //map the modifiers into int descriptors
            observeModifier(s, 1);
        }
        return this;
    }

    public TestBuilder removeModifier(String modifierName) {
        if (this.modifiers == null) return this;

        return observeModifier(modifierName, 0);
    }

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
