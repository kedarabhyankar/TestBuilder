import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Build a {@code FieldTest} from a class name and field name. You can then
 * use this to check against another {@code FieldTest} to check for equality.
 *
 * @author kedarabhyankar
 * @version 11/07/2020
 */
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
            return new FieldTestFailure(e);
        }

        Field fieldReference;
        try {
            fieldReference = classReference.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            return new FieldTestFailure(e);
        }

        return new FieldTest().setFieldName(fieldReference.getName())
                .setFieldType(fieldReference.getType())
                .setModifiers(Modifier.toString(fieldReference.getModifiers()).substring(1,
                        Modifier.toString(fieldReference.getModifiers()).length() - 1).split(" "))
                .build();
    }
}
