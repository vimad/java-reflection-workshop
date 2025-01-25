package reflection;

import java.lang.reflect.Constructor;

public class ConstructorExample {

    public static class Person {
        private final String name;
        public Person(String name) {
            this.name = name;
        }

        public String getName() {return name;}
    }

    public static void main(String[] args) throws ReflectiveOperationException {
        Constructor<Person> constructor = Person.class.getConstructor(String.class);
        Person john = constructor.newInstance("John");
        System.out.println(john.getName());
    }
}
