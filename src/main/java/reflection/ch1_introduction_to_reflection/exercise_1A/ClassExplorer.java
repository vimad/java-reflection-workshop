package reflection.ch1_introduction_to_reflection.exercise_1A;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClassExplorer {
    /**
     * Returns all interfaces in the inheritance hierarchy as a distinct set.
     */
    public static Set<Class<?>> findAllInterfaces(Class<?> clazz) {
        Set<Class<?>> interfaces = new HashSet<>();
        while (clazz != null) {
            for (Class<?> iface : clazz.getInterfaces()) {
                findAllInterfacesRecursively(iface, interfaces);
            }
            clazz = clazz.getSuperclass();
        }
        return interfaces;
    }

    private static void findAllInterfacesRecursively(Class<?> iface, Set<Class<?>> interfaces) {
        if (interfaces.add(iface)) {
            for (Class<?> superIface : iface.getInterfaces()) {
                findAllInterfacesRecursively(superIface, interfaces);
            }
        }
    }
}
