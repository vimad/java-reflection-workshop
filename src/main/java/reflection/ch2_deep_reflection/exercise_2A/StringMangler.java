package reflection.ch2_deep_reflection.exercise_2A;

import java.lang.reflect.Field;

// Run with JVM option --add-opens java.base/java.lang=ALL-UNNAMED
public class StringMangler {
    public static void main(String... args) throws ReflectiveOperationException {
        OtherPrinter otherPrinter = new OtherPrinter();
        otherPrinter.print();
        replaceStringContents("Hello World", "Goodbye, cruel world");
        System.out.println("Hello World");
        otherPrinter.print();
    }

    /**
     * Replace the value array inside the original String with the
     * value array from the newContent String. This code should work
     * from Java 7 until Java 21.
     */
    public static void replaceStringContents(String original, String newContent)
            throws ReflectiveOperationException {
        Field valueField = String.class.getDeclaredField("value");
        valueField.setAccessible(true);
        valueField.set(original, valueField.get(newContent));
    }
}
