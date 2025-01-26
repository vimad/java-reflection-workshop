package reflection;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;

public class MethodHandlesDemo {

    public static class Person {
        private String name;

        // MethodType.methodType(void.class)
        public Person() {
        }

        // MethodType.methodType(void.class, String.class)
        public void setName(String name) {
            this.name = name;
        }

        // MethodType.methodType(String.class)
        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        Class<?> whoami = lookup.lookupClass();
        System.out.println("whoam i = " + whoami); // output: whoam i = class reflection.MethodHandlesDemo

        // private lookup in Integer
        MethodHandles.Lookup privateLookupIn = MethodHandles.privateLookupIn(Integer.class, lookup);
        System.out.println(privateLookupIn);

        MethodHandle constructor = lookup.findConstructor(Person.class, MethodType.methodType(void.class));
        MethodHandle setName = lookup.findVirtual(Person.class, "setName", MethodType.methodType(void.class, String.class));
        MethodHandle getName = lookup.findVirtual(Person.class, "getName", MethodType.methodType(String.class));

        Person person = (Person) constructor.invoke();
        setName.invoke(person, "vinod");
        System.out.println(getName.invoke(person));

        VarHandle name = lookup.findVarHandle(Person.class, "name", String.class);
        name.set(person, "john");
        System.out.println(name.get(person));

    }
}
