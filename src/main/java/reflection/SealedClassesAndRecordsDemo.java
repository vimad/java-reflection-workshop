package reflection;

import java.util.Arrays;

public class SealedClassesAndRecordsDemo {

    public static sealed class A permits B{}
    public static final class B extends A{}
    public static record Person(String firstName, String lastName) {}

    public static void main(String[] args) {
        Arrays.stream(A.class.getPermittedSubclasses())
            .map(Class::getSimpleName)
            .forEach(System.out::println); //output: B

        //output:
        // java.lang.String firstName
        // java.lang.String lastName
        Arrays.stream(Person.class.getRecordComponents())
            .forEach(System.out::println);
    }
}
