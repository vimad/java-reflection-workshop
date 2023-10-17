package reflection.ch4_java_lang_invoke.exercise_4A;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MagicClassInstantiator {
    public static void main(String... args) throws Throwable {
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

        Class<?> clazz = Class.forName(className);

        // 1. Use privateLookupIn() to discover the constructor, even if private
        MethodHandle constructor = null;  // TODO

        // 2. Construct the object from the MethodHandle, using invokeWithArguments
        // or create a Spreader
        Object object = null; // TODO

        // 3. Find the method on the class given by "methodName".  Since we
        // don't know the return type, it is easiest to first find the method
        // via reflection and then to unreflect that.
        Method method = clazz.getMethod(methodName);
        MethodHandle mh_method = null; // TODO

        // 4. Call the method with "invoke()", passing in the object you created
        // as the first parameter.
        Object result = null; // TODO

        if (method.getReturnType() != void.class) {
            System.out.println("result = " + result);
        }
    }
}
