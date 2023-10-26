package reflection.ch1_introduction_to_reflection.exercise_1B;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListMangler {
    /**
     * Returns a new immutable list, based on the input list parameter, but
     * with the Collections.methodName applied to it. Valid options for
     * methodName are "sort", "reverse" and "shuffle". The original list parameter
     * is not changed.
     *
     * @param list       the list that our manipulation is based on.
     * @param methodName the name of the method to call on Collections, taking a List
     *                   as a parameter. Valid options are "sort", "reverse" and "shuffle".
     * @return an immutable list, based on the input list, with the manipulation applied.
     * @throws IllegalArgumentException if the method is not found.
     * @throws AssertionError           if an IllegalAccessException is internally caused.
     *                                  These methods should all be accessible.
     * @throws IllegalStateException    if the cause of an exception occurred whilst
     *                                  invoking the method is a checked exception.
     */
    public static List<String> reorder(List<String> list, String methodName) {
        if (list.isEmpty()) return List.of();
        // Create a new ArrayList containing the items in the "list" parameter
        try {
            var copy = new ArrayList<>(list);
            // Find the method in the Collections class, with List as a parameter
            var method = Collections.class.getMethod(methodName, List.class);
            // Invoke the static method, passing in the copy of the list as a parameter
            method.invoke(null, copy);
            // Return an immutable list with List.copyOf() of our copy
            return List.copyOf(copy);
        } catch (NoSuchMethodException e) {
            // If method is not found, throw an IllegalArgumentException
            throw new IllegalArgumentException("Method " + methodName + " not found");
        } catch (IllegalAccessException e) {
            // If an IllegalAccessException is thrown, throw an AssertionError
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            // If an InvocationTargetException is thrown, if the cause is unchecked throw it,
            // otherwise wrap the cause with an IllegalStateException
            try {
                throw e.getCause();
            } catch (Error | RuntimeException unchecked) {
                throw unchecked;
            } catch (Throwable cause) {
                throw new IllegalStateException(cause);
            }
        }
    }
}
