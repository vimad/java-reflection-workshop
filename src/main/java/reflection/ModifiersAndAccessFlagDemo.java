package reflection;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Modifier;

public class ModifiersAndAccessFlagDemo {
    public static void methodWithArray(Object[] objects) {}
    public static void methodWithVarargs(Object... objects) {}


    public static void main(String[] args) throws ReflectiveOperationException {

        // There are issues with bitset where it changes based on the context
        int classModifiers = ModifiersAndAccessFlagDemo.class.getModifiers();
        int maModifier = ModifiersAndAccessFlagDemo.class.getMethod("methodWithArray", Object[].class).getModifiers();
        int mvModifier = ModifiersAndAccessFlagDemo.class.getMethod("methodWithVarargs", Object[].class).getModifiers();
        System.out.println(Modifier.toString(classModifiers)); //output: public
        System.out.println(Modifier.toString(maModifier)); //output: public static
        System.out.println(Modifier.toString(mvModifier)); //output: public static transient
        System.out.println(Modifier.isTransient(mvModifier)); //output: true

        // In java 20 it introduced AccessFlag enum
        var classModifiers2 = ModifiersAndAccessFlagDemo.class.accessFlags();
        var maAccessFlags = ModifiersAndAccessFlagDemo.class.getMethod("methodWithArray", Object[].class).accessFlags();
        var mvAccessFlags = ModifiersAndAccessFlagDemo.class.getMethod("methodWithVarargs", Object[].class).accessFlags();
        System.out.println(classModifiers2); //output: [PUBLIC, SUPER]
        System.out.println(maAccessFlags); //output: [PUBLIC, STATIC]
        System.out.println(mvAccessFlags); //output: [PUBLIC, STATIC, VARARGS]
        System.out.println(mvAccessFlags.contains(AccessFlag.TRANSIENT)); //output: false
        System.out.println(mvAccessFlags.contains(AccessFlag.VARARGS)); //output: true

        // can convert modifiers to access flags
        System.out.println(AccessFlag.maskToAccessFlags(Modifier.TRANSIENT, AccessFlag.Location.FIELD)); //output: [TRANSIENT]
        System.out.println(AccessFlag.maskToAccessFlags(Modifier.TRANSIENT, AccessFlag.Location.METHOD)); //output [VARARGS]
    }
}
