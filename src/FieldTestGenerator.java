import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@SuppressWarnings("unused")
public class FieldTestGenerator {

    public static FieldTest generateFieldTest(String className, String fieldName) {
        if (className == null || fieldName == null || className.isEmpty() || fieldName.isEmpty()) {
            return null;
        }

        Class<?> classReference;
        try {
            classReference = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        Field fieldReference;
        try {
            fieldReference = classReference.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }

        return new FieldTest().setFieldName(fieldReference.getName())
                .setFieldType(fieldReference.getType())
                .setModifiers(Modifier.toString(fieldReference.getModifiers()).substring(1,
                        Modifier.toString(fieldReference.getModifiers()).length() - 1).split(" "))
                .build();
    }
}
