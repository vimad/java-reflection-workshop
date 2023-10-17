package reflection.ch4_java_lang_invoke.solution_4A;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.*;

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
        MethodHandle constructor;
        try {
            constructor = MethodHandles.lookup().findConstructor(
                    clazz, MethodType.methodType(void.class, parameterTypes)
            );
        } catch (ReflectiveOperationException e) {
            constructor = MethodHandles.privateLookupIn(clazz,
                    MethodHandles.lookup()).findConstructor(clazz,
                    MethodType.methodType(void.class, parameterTypes));
        }

        // 2. Construct the object from the MethodHandle
        Object object = constructor.invokeWithArguments(parameters);

        // 3. Find the method on the class given by "methodName".  Since we
        // don't know the return type, it is easiest to first find the method
        // via reflection and then to unreflect that.
        Method method = clazz.getMethod(methodName);
        MethodHandle mh_method = MethodHandles.lookup().unreflect(method);

        // 4. Call the method with "invoke()", passing in the object you created
        // as the first parameter.
        Object result = mh_method.invoke(object);

        if (method.getReturnType() != void.class) {
            System.out.println("result = " + result);
        }
    }
}
