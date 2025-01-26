package reflection;

import java.lang.reflect.Field;

public class DeepReflection {

    public static class Person {
        private final String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {return name;}
    }

    public static void main(String[] args) throws ReflectiveOperationException {
        Person person = new Person("john");
        System.out.println(person.getName());
        Field name = Person.class.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person, "vinod");
        System.out.println(person.getName());

        Field value = Integer.class.getDeclaredField("value");
        value.setAccessible(true);
        value.set(10, 30);
        System.out.printf("value of 10 is %d", 10); //output: value of 10 is 30
    }
}
