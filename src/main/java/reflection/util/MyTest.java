package reflection.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MyTest {
    public static void main(String[] args) throws ReflectiveOperationException {
        Class<String> stringClass = String.class;
        Method toUpperCase = stringClass.getMethod("toUpperCase");
        System.out.println(toUpperCase.invoke("hello!")); // HELO!

        //Use java.lang.reflect.Modifier to check access modifiers
        boolean aPublic = Modifier.isPublic(toUpperCase.getModifiers());
        System.out.println(aPublic); // output: true
    }
}
