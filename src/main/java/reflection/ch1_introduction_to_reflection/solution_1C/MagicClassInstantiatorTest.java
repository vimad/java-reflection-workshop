package reflection.ch1_introduction_to_reflection.solution_1C;

public class MagicClassInstantiatorTest {
    public static void main(String... args) throws Exception {
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
