package reflection.ch3_arrays.exercise_3B;

public class ArraysWithReflectionMagic {
    /**
     * Returns a deep clone of the array source, but
     * does not clone the actual elements of the array
     * (unless they are also arrays). If the source
     * is not an array, simply return it.
     */
    public static <A> A deepClone(A source) {
        return source;
    }
}
