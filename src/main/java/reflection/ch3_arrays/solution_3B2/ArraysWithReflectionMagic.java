package reflection.ch3_arrays.solution_3B2;

import java.lang.reflect.Array;

public class ArraysWithReflectionMagic {
    /**
     * Returns a deep clone of the array source, but
     * does not clone the actual elements of the array
     * (unless they are also arrays). If the source
     * is not an array, simply return it.
     */
    public static <A> A deepClone(A source) {
        if (source == null || !source.getClass().isArray()) {
            return source;
        }
        @SuppressWarnings("unchecked")
        A clone = (A) deepClone0(source);
        for (int i = 0, length = Array.getLength(source); i < length; i++) {
            Array.set(clone, i, deepClone(Array.get(source, i)));
        }
        return clone;
    }

    private static Object deepClone0(Object source) {
        assert source != null;
        if (source instanceof Object[]) return ((Object[]) source).clone();
        else if (source instanceof byte[]) return ((byte[]) source).clone();
        else if (source instanceof short[]) return ((short[]) source).clone();
        else if (source instanceof int[]) return ((int[]) source).clone();
        else if (source instanceof long[]) return ((long[]) source).clone();
        else if (source instanceof char[]) return ((char[]) source).clone();
        else if (source instanceof float[]) return ((float[]) source).clone();
        else if (source instanceof double[]) return ((double[]) source).clone();
        else if (source instanceof boolean[])
            return ((boolean[]) source).clone();
        else throw new AssertionError("Not an array");
    }
}