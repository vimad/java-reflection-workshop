package reflection.ch4_java_lang_invoke.exercise_4A;

public class MagicClassInstantiatorTest {
    public static void main(String... args) throws Throwable {
        String packageName = MagicClassInstantiatorTest.class.getPackageName();
        String className = packageName + ".MagicTestClass";
        MagicClassInstantiator.main(className, "howdie", "print");
        MagicClassInstantiator.main(className, "howdie", "there", "print");
        MagicClassInstantiator.main(className, "howdie", "there", "partner", "print");
        MagicClassInstantiator.main("java.lang.String", "heinz Kabutz", "toUpperCase");
        MagicClassInstantiator.main("java.util.ArrayList", "size");
        MagicClassInstantiator.main("java.util.ArrayList", "clear");
    }
}
