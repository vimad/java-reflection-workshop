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
        if (source instanceof Object[] src) return src.clone();
        else if (source instanceof byte[] src) return src.clone();
        else if (source instanceof short[] src) return src.clone();
        else if (source instanceof int[] src) return src.clone();
        else if (source instanceof long[] src) return src.clone();
        else if (source instanceof char[] src) return src.clone();
        else if (source instanceof float[] src) return src.clone();
        else if (source instanceof double[] src) return src.clone();
        else if (source instanceof boolean[] src)return src.clone();
        else throw new AssertionError("Not an array");
    }
}