package reflection.ch2_deep_reflection.exercise_2A;

// Run with JVM option --add-opens java.base/java.lang=ALL-UNNAMED
public class StringMangler {
    public static void main(String... args) throws ReflectiveOperationException {
        replaceStringContents("Hello World", "Goodbye, cruel world");
        System.out.println("Hello World");
    }

    /**
     * Replace the value array inside the original String with the
     * value array from the newContent String. This code should work
     * from Java 7 until Java 17.
     */
    public static void replaceStringContents(String original, String newContent)
            throws ReflectiveOperationException {
        throw new UnsupportedOperationException("TODO");
    }
}
