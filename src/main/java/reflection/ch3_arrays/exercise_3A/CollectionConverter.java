package reflection.ch3_arrays.exercise_3A;

import java.util.ArrayList;
import java.util.Collection;
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

        if (true)
            throw new UnsupportedOperationException("TODO - Simplify");

        Collection<Object> result = new ArrayList<>();
        String name = array.getClass().getName();
        switch (name.charAt(1)) {
            case 'Z' -> {
                for (Object x : (boolean[]) array) result.add(x);
            }
            case 'B' -> {
                for (Object x : (byte[]) array) result.add(x);
            }
            case 'C' -> {
                for (Object x : (char[]) array) result.add(x);
            }
            case 'D' -> {
                for (Object x : (double[]) array) result.add(x);
            }
            case 'F' -> {
                for (Object x : (float[]) array) result.add(x);
            }
            case 'I' -> {
                for (Object x : (int[]) array) result.add(x);
            }
            case 'J' -> {
                for (Object x : (long[]) array) result.add(x);
            }
            case 'S' -> {
                for (Object x : (short[]) array) result.add(x);
            }
            case 'L', '[' -> {
                for (Object x : (Object[]) array) result.add(x);
            }
            default -> throw new IllegalArgumentException(
                    "Unexpected array element type: " + name
            );
        }
        return result.stream();
    }
}