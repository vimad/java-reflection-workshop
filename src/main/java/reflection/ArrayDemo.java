package reflection;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayDemo {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        Class<?> componentType = arr.getClass().getComponentType();
        System.out.println(componentType); //output: int

        Class<?> aClass = int.class.arrayType();
        System.out.println(aClass); //output: class [I

        System.out.println(Array.get(arr, 0)); //output: 1

        int[] arr2 = (int[]) Array.newInstance(int.class, 2);
        arr2[0] = 1;
        arr2[1] = 2;
        System.out.println(Arrays.toString(arr2)); //output: [1, 2]
    }
}
