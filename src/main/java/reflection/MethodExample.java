package reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MethodExample {

    public void person(String name, int age) {}

    public static void main(String[] args) throws ReflectiveOperationException {
        Class<String> stringClass = String.class;
        Method toUpperCase = stringClass.getMethod("toUpperCase");
        System.out.println(toUpperCase.invoke("hello!")); // HELO!

        //Use java.lang.reflect.Modifier to check access modifiers
        boolean aPublic = Modifier.isPublic(toUpperCase.getModifiers());
        System.out.println(aPublic); // output: true

        // Object[] a = new Object[]{null};

        //If a static method we pass null as first parameter to invoke
        //If there are arguments to the method we need to pass the type of arguments
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(2);
        numbers.add(1);
        Method sortMethod = Collections.class.getMethod("sort", List.class);
        sortMethod.invoke(null, numbers);
        System.out.println(numbers); //output: [1,2]

        //There is a getMethods function
        for (Method method : Collections.class.getMethods()) {
            if (method.getParameterCount() == 1 && method.getParameters()[0].getType() == List.class) {
                System.out.println(method.getName()); // print values - reverse,sort,shuffle,unmodifiableList,synchroizedList
            }
        }

        Method person = MethodExample.class.getDeclaredMethod("person", String.class, int.class);
        Arrays.stream(person.getParameters())
            .map(Parameter::getName)
            .forEach(System.out::println);

    }
}
