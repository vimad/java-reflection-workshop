package reflection.ch1_introduction_to_reflection.solution_1C;

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
        Class<?> clazz = Class.forName(className, true,
                Thread.currentThread().getContextClassLoader());

        // 2. Use the "parameterTypes" to find the Constructor for the class
        Constructor<?> constructor = clazz.getConstructor(parameterTypes);

        // 3. Construct the object by calling the newInstance() method on the
        // Constructor that you have just created, using the "parameters"
        Object object = constructor.newInstance(parameters);

        // 4. Find the method on the class given by "methodName".  You can
        // assume that the method does not have any parameters.
        Method method = clazz.getMethod(methodName);

        // 5. Call the method with "invoke()", passing in the object you created
        // as the first parameter.
        Object result = method.invoke(object);

        // 6. If the return type of the method is not void, print the result to
        // the console with System.out.println()
        if (method.getReturnType() != void.class) {
            System.out.println("result = " + result);
        }
    }
}
