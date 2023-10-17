package reflection.ch1_introduction_to_reflection.solution_1A;

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
        for (Class<?> anInterface : clazz.getInterfaces()) {
            interfaces.addAll(findAllInterfaces(anInterface));
        }
        interfaces.addAll(findAllInterfaces(clazz.getSuperclass()));
        return interfaces;
    }
}
