package reflection.ch3_arrays.exercise_3A;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
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
        if (!array.getClass().isArray())
            throw new IllegalArgumentException(
                    "Argument type not an array, but " +
                            array.getClass().getTypeName());
        return IntStream.range(0, Array.getLength(array))
                .mapToObj(i -> Array.get(array, i));
    }
}