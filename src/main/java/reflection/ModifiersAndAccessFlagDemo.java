package reflection;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Modifier;

public class ModifiersAndAccessFlagDemo {
    public static void printAll(Object[] objects) {}
    public static void showAll(Object... objects) {}


    public static void main(String[] args) throws ReflectiveOperationException {

        // There are issues with bitset where it changes based on the context
        int classModifiers = ModifiersAndAccessFlagDemo.class.getModifiers();
        int printAll = ModifiersAndAccessFlagDemo.class.getMethod("printAll", Object[].class).getModifiers();
        int showAll = ModifiersAndAccessFlagDemo.class.getMethod("showAll", Object[].class).getModifiers();
        System.out.println(Modifier.toString(classModifiers));
        System.out.println(Modifier.toString(printAll));
        System.out.println(Modifier.toString(showAll));
        System.out.println(Modifier.isTransient(showAll));

        // In java 20 it introduced AccessFlag enum
        var classModifiers2 = ModifiersAndAccessFlagDemo.class.accessFlags();
        var printAll2 = ModifiersAndAccessFlagDemo.class.getMethod("printAll", Object[].class).accessFlags();
        var showAll2 = ModifiersAndAccessFlagDemo.class.getMethod("showAll", Object[].class).accessFlags();
        System.out.println(classModifiers2);
        System.out.println(printAll2);
        System.out.println(showAll2);
        System.out.println(showAll2.contains(AccessFlag.TRANSIENT));
        System.out.println(showAll2.contains(AccessFlag.VARARGS));

        // can convert modifiers to access flags
        System.out.println(AccessFlag.maskToAccessFlags(Modifier.TRANSIENT, AccessFlag.Location.FIELD));
        System.out.println(AccessFlag.maskToAccessFlags(Modifier.TRANSIENT, AccessFlag.Location.METHOD));
    }

}
