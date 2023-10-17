package reflection.ch3_arrays.solution_3A;

import java.lang.reflect.Array;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CollectionConverter {
    /**
     * Converts an array to a stream, either with the
     * elements being the wrapper class of the primitive
     * array elements, or if it is an Object[] then the
     * containing objects.
     *
     * @throws IllegalArgumentException if parameter is
     *                                  not an array
     */
    public static Stream<?> asStream(Object array) {
        Class<?> clazz = array.getClass();
        if (!clazz.isArray())
            throw new IllegalArgumentException(
                    "Argument type not an array, but " +
                            clazz.getTypeName());
        return IntStream.range(0, Array.getLength(array))
                .mapToObj(i -> Array.get(array, i));
    }
}