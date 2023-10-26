package reflection.playground;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class PrivateMethodCalls {
    private void foo() {
        System.out.println("PrivateMethodCalls.foo()");
    }
    public static void main(String... args)
            throws Throwable {
        var obj = new PrivateMethodCalls();
        MethodHandles.lookup().findVirtual(
                PrivateMethodCalls.class, "foo",
                MethodType.methodType(void.class))
                .invoke(obj); // OK
        System.out.println(MethodHandles.lookup().findVirtual(
                PrivateMethodCalls.class, "toString",
                MethodType.methodType(String.class))
                .invoke(obj)); // OK
        System.out.println(PrivateMethodCalls.class.getMethod("toString")
                .invoke(obj)); // NoSuchMethodException
        System.out.println(PrivateMethodCalls.class.getDeclaredMethod("toString")
                .invoke(obj)); // NoSuchMethodException

        PackageAccessMethodCalls.class.getMethod("bar")
                .invoke(new PackageAccessMethodCalls());
        PrivateMethodCalls.class.getMethod("foo")
                .invoke(obj); // NoSuchMethodException
    }
}