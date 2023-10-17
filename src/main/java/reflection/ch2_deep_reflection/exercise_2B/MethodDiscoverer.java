package reflection.ch2_deep_reflection.exercise_2B;

import java.lang.reflect.Method;
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
        throw new UnsupportedOperationException("TODO");
    }
}
