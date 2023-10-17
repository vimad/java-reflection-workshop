package reflection.ch3_arrays.solution_3B;

import java.lang.reflect.Array;

public class ArraysWithReflectionMagic {
    /**
     * Returns a deep clone of the array source, but
     * does not clone the actual elements of the array
     * (unless they are also arrays). If the source
     * is not an array, simply return it.
     */
    public static <A> A deepClone(A source) {
        if (source == null || !source.getClass().isArray()) return source;
        int length = Array.getLength(source);
        @SuppressWarnings("unchecked")
        A clone = (A) Array.newInstance(
                source.getClass().getComponentType(), length);
        for (int i = 0; i < length; i++) {
            Array.set(clone, i, deepClone(Array.get(source, i)));
        }
        return clone;
    }
}
