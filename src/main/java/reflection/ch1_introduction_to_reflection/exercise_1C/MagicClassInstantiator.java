package reflection.ch1_introduction_to_reflection.exercise_1C;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MagicClassInstantiator {
    public static void main(String... args) throws Exception {
        System.out.println("Invoked with arguments " + Arrays.toString(args));

        if (args.length < 2) {
            System.err.println("Usage: MagicClassInstantiator className "
                    + "[param1 param2 param3 ...] methodName");
            return;
        }

        String className = args[0];
        Class<?>[] parameterTypes = new Class<?>[args.length - 2];
        Arrays.fill(parameterTypes, String.class);
        Object[] parameters = Arrays.copyOfRange(
                args, 1, args.length - 1, Object[].class);
        String methodName = args[args.length - 1];

        // 1. Use the "className" to load the class with Class.forName()
        Class<?> clazz = null; // TODO

        // 2. Use the "parameterTypes" to find the Constructor for the class
        Constructor<?> constructor = null; // TODO

        // 3. Construct the object by calling the newInstance() method on the
        // Constructor that you have just created, using the "parameters"
        Object object = null; // TODO

        // 4. Find the method on the class given by "methodName".  You can
        // assume that the method does not have any parameters.
        Method method = null; // TODO

        // 5. Call the method with "invoke()", passing in the object you created
        // as the first parameter.

        // 6. If the return type of the method is not void, print the result to
        // the console with System.out.println()
    }
}
