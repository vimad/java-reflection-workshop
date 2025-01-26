package reflection;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class UnReflectDemo {
    public static void main(String[] args) throws Throwable {
        Method toUpperCases = String.class.getMethod("toUpperCase");
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mh = lookup.unreflect(toUpperCases);
        System.out.println(mh.invoke("vinod")); //output: VINOD

        Field field = Integer.class.getDeclaredField("value");
        field.setAccessible(true);
        MethodHandle methodHandle = lookup.unreflectSetter(field);
        methodHandle.invoke(10, 24);
        System.out.printf("10 is %d ", 10); //output: 10 is 24
    }
}
