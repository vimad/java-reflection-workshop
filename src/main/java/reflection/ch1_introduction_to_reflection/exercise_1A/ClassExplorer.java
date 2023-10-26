package reflection.ch1_introduction_to_reflection.exercise_1A;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ClassExplorer {
    /**
     * Returns all interfaces in the inheritance hierarchy as a distinct set.
     */
    public static Set<Class<?>> findAllInterfaces(Class<?> clazz) {
        if (clazz == null) return Set.of();
        Set<Class<?>> interfaces = new HashSet<>();
        if (clazz.isInterface()) interfaces.add(clazz);
        for (Class<?> intf : clazz.getInterfaces()) {
            interfaces.addAll(findAllInterfaces(intf));
        }
        interfaces.addAll(findAllInterfaces(clazz.getSuperclass()));
        return Set.copyOf(interfaces);
    }
}
