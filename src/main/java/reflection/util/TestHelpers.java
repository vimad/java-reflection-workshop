package reflection.util;

import org.junit.jupiter.api.Assertions;

import java.util.Set;
import java.util.stream.Collectors;

public class TestHelpers {
    public static void assertClassesEquals(Set<Class<?>> expectedClasses, Set<Class<?>> actualClasses) {
        assertStringsClassesEquals(convert(expectedClasses), actualClasses);
    }

    public static void assertStringsClassesEquals(Set<String> expected, Set<Class<?>> actual) {
        String expectedString = beautify(expected);
        String actualString = beautify(convert(actual));
        Assertions.assertEquals(expectedString, actualString);
    }

    private static Set<String> convert(Set<Class<?>> classes) {
        return classes.stream()
                .map(Class::getSimpleName)
                .collect(Collectors.toSet());
    }

    public static String beautify(Set<String> interfaces) {
        return interfaces.stream()
                .sorted()
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
