package reflection.ch1_introduction_to_reflection.exercise_1D;

import java.util.Collections;
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
        if (!root.isSealed()) throw new IllegalArgumentException(root + " must be sealed");
        var permitted = new HashSet<Class<?>>();
        Collections.addAll(permitted, root.getPermittedSubclasses());
        for (var subclass : root.getPermittedSubclasses()) {
            if (subclass.isSealed()) permitted.addAll(find(subclass));
        }
        return Set.copyOf(permitted);
    }
}
