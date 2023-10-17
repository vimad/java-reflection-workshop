package reflection.ch1_introduction_to_reflection.solution_1D;

import java.util.HashSet;
import java.util.Set;

public class PermittedSubclassExplorer {
    /**
     * Return all permitted subclasses underneath a root
     * class or interface. The root must be a sealed type.
     *
     * @param root class to find permitted subtypes under
     * @return a set of all permitted subclasses
     * @throws IllegalArgumentException if root isn't sealed
     */
    public static Set<Class<?>> find(Class<?> root) {
        if (!root.isSealed())
            throw new IllegalArgumentException(
                    root + " is not sealed"
            );
        Set<Class<?>> subclasses = new HashSet<>();
        for (Class<?> subclass : root.getPermittedSubclasses()) {
            subclasses.add(subclass);
            if (subclass.isSealed()) subclasses.addAll(find(subclass));
        }
        return Set.copyOf(subclasses);
    }
}
