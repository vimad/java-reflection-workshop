package reflection.ch2_deep_reflection.exercise_2B;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodDiscovererTest {
    public static class A {
        public String foo() {
            return "A.foo()";
        }

        private String bar() {
            return "A.bar()";
        }

        String baz() {
            return "A.baz()";
        }

        protected String qux() {
            return "A.qux()";
        }
    }
    public static class B extends A {
        public String foo() {
            return "B.foo()";
        }

        private String bar() {
            return "B.bar()";
        }

        String baz() {
            return "B.baz()";
        }

        protected String qux() {
            return "B.qux()";
        }
    }
    public static class C extends B {
        public String foo() {
            return "C.foo()";
        }

        private String bar() {
            return "C.bar()";
        }

        String baz() {
            return "C.baz()";
        }

        protected String qux() {
            return "C.qux()";
        }

        @Override
        public String toString() {
            return "C.toString()";
        }
    }

    @Test
    public void testMethods() throws ReflectiveOperationException {
        C c = new C();
        Set<Method> methods = MethodDiscoverer.findAllMethods(C.class, false);
        methods.stream().sorted(
                Comparator.comparing(Method::getName)
                        .thenComparing(method ->
                                method.getDeclaringClass().getSimpleName())
        ).forEach(System.out::println);
        List<String> outputs = new ArrayList<>();
        for (Method method : methods) {
            switch (method.getName()) {
                case "foo", "bar", "baz", "qux", "toString" -> {
                    method.setAccessible(true);
                    String result = (String) method.invoke(c);
                    System.out.println(result);
                    outputs.add(result);
                }
            }
        }
        String actualString = convertToString(outputs);
        List<String> expected = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            expected.add("C.foo()");
            expected.add("C.baz()");
            expected.add("C.qux()");
            expected.add("" + (char) ('A' + i) + ".bar()");
        }
        expected.add("C.toString()");
        String expectedString = convertToString(expected);
        assertEquals(expectedString, actualString);
    }

    private static String convertToString(List<String> list) {
        return list.stream()
                .sorted()
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
