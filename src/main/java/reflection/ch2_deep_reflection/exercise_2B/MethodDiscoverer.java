package reflection.ch2_deep_reflection.exercise_2B;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
        if (clazz == null || (clazz == Object.class && !includeObjectMethods)) return Set.of();
        Set<Method> methods = new HashSet<>();
        Collections.addAll(methods, clazz.getDeclaredMethods());
        methods.addAll(findAllMethods(clazz.getSuperclass(), includeObjectMethods));
        return Set.copyOf(methods);
    }
}
