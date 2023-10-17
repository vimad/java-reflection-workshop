package reflection.ch2_deep_reflection.solution_2B;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class MethodDiscoverer {
    /**
     * Returns a set of all the methods defined on clazz and
     * its superclasses, including private, protected,
     * public, and package private methods.
     *
     * @param includeObjectMethods true if the Set should
     *                             include Object's methods
     */
    public static Set<Method> findAllMethods(
            Class<?> clazz, boolean includeObjectMethods) {
        if (clazz == null || (clazz == Object.class && !includeObjectMethods))
            return Set.of();
        Set<Method> methods = Arrays.stream(clazz.getDeclaredMethods())
                .collect(Collectors.toSet());
        methods.addAll(findAllMethods(clazz.getSuperclass(), includeObjectMethods));
        return Set.copyOf(methods);
    }
}
