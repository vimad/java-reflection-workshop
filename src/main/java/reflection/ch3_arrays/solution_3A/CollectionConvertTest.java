package reflection.ch3_arrays.solution_3A;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionConvertTest {
    @Test
    public void testStringArray() {
        Stream<?> stream;
        String[] array = "Hello world how are you".split(" ");

        System.out.println(Arrays.toString(array));
        stream = CollectionConverter.asStream(array);
        assertTrue(stream.allMatch(c -> c instanceof String));

        stream = CollectionConverter.asStream(array);
        assertEquals(5, stream.count());

        String s = CollectionConverter.asStream(array)
                .map(String.class::cast)
                .collect(Collectors.joining());
        assertEquals("Helloworldhowareyou", s);

        stream = CollectionConverter.asStream(new String[0]);
        assertEquals(0, stream.count());
    }

    @Test
    public void testCharArray() {
        Stream<?> stream;
        char[] array = "Hello world".toCharArray();

        stream = CollectionConverter.asStream(array);
        assertTrue(stream.allMatch(c -> c instanceof Character));

        stream = CollectionConverter.asStream(array);
        assertEquals(11, stream.count());

        String s = CollectionConverter.asStream(array)
                .map(c -> "" + c)
                .collect(Collectors.joining());
        assertEquals("Hello world", s);

        stream = CollectionConverter.asStream(new char[0]);
        assertEquals(0, stream.count());
    }

    @Test
    public void testIntArray() {
        Stream<?> stream;
        int[] array = {3, 1, 4, 1, 5, 9};

        stream = CollectionConverter.asStream(array);
        assertTrue(stream.allMatch(c -> c instanceof Integer));

        stream = CollectionConverter.asStream(array);
        assertEquals(6, stream.count());

        long sum = CollectionConverter.asStream(array)
                .map(Integer.class::cast)
                .mapToInt(Integer::intValue)
                .sum();
        assertEquals(23, sum);

        stream = CollectionConverter.asStream(new int[0]);
        assertEquals(0, stream.count());
    }

    @Test
    public void testByteArray() {
        Stream<?> stream;
        byte[] array = {3, 1, 4, 1, 5, 9};

        stream = CollectionConverter.asStream(array);
        assertTrue(stream.allMatch(c -> c instanceof Byte));

        stream = CollectionConverter.asStream(array);
        assertEquals(6, stream.count());

        long sum = CollectionConverter.asStream(array)
                .map(Byte.class::cast)
                .mapToInt(Byte::intValue)
                .sum();
        assertEquals(23, sum);

        stream = CollectionConverter.asStream(new byte[0]);
        assertEquals(0, stream.count());
    }

    @Test
    public void testShortArray() {
        Stream<?> stream;
        short[] array = {3, 1, 4, 1, 5, 9};

        stream = CollectionConverter.asStream(array);
        assertTrue(stream.allMatch(c -> c instanceof Short));

        stream = CollectionConverter.asStream(array);
        assertEquals(6, stream.count());

        long sum = CollectionConverter.asStream(array)
                .map(Short.class::cast)
                .mapToInt(Short::intValue)
                .sum();
        assertEquals(23, sum);

        stream = CollectionConverter.asStream(new short[0]);
        assertEquals(0, stream.count());
    }

    @Test
    public void testLongArray() {
        Stream<?> stream;
        long[] array = {3, 1, 4, 1, 5, 9};

        stream = CollectionConverter.asStream(array);
        assertTrue(stream.allMatch(c -> c instanceof Long));

        stream = CollectionConverter.asStream(array);
        assertEquals(6, stream.count());

        long sum = CollectionConverter.asStream(array)
                .map(Long.class::cast)
                .mapToInt(Long::intValue)
                .sum();
        assertEquals(23, sum);

        stream = CollectionConverter.asStream(new long[0]);
        assertEquals(0, stream.count());
    }

    @Test
    public void testFloatArray() {
        Stream<?> stream;
        float[] array = {3.14f, 0.0015f, 0.00009f};

        stream = CollectionConverter.asStream(array);
        assertTrue(stream.allMatch(c -> c instanceof Float));

        stream = CollectionConverter.asStream(array);
        assertEquals(3, stream.count());

        double sum = CollectionConverter.asStream(array)
                .map(Float.class::cast)
                .mapToDouble(Float::floatValue)
                .sum();
        assertEquals(3.14159f, sum, 0.000001);

        stream = CollectionConverter.asStream(new float[0]);
        assertEquals(0, stream.count());
    }

    @Test
    public void testDoubleArray() {
        Stream<?> stream;
        double[] array = {3.14, 0.0015, 0.00009};

        stream = CollectionConverter.asStream(array);
        assertTrue(stream.allMatch(c -> c instanceof Double));

        stream = CollectionConverter.asStream(array);
        assertEquals(3, stream.count());

        double sum = CollectionConverter.asStream(array)
                .map(Double.class::cast)
                .mapToDouble(Double::doubleValue)
                .sum();
        assertEquals(3.14159f, sum, 0.000001);

        stream = CollectionConverter.asStream(new double[0]);
        assertEquals(0, stream.count());
    }

    @Test
    public void testBooleanArray() {
        Stream<?> stream;
        boolean[] array = {true, true, false, true, false};

        stream = CollectionConverter.asStream(array);
        assertTrue(stream.allMatch(c -> c instanceof Boolean));

        stream = CollectionConverter.asStream(array);
        assertEquals(5, stream.count());

        long trues = CollectionConverter.asStream(array)
                .map(Boolean.class::cast)
                .filter(Boolean::booleanValue)
                .count();
        assertEquals(3, trues);

        stream = CollectionConverter.asStream(new boolean[0]);
        assertEquals(0, stream.count());
    }

    @Test
    public void testMultiDimensionalIntArray() {
        Stream<?> stream;
        int[][][] array = {{{3, 1}, {4}}, {{1, 5}}, {{9}}};

        stream = CollectionConverter.asStream(array);
        assertTrue(stream.allMatch(c -> c instanceof int[][]));

        stream = CollectionConverter.asStream(array);
        assertEquals(3, stream.count());

        long sum = CollectionConverter.asStream(array)
                .map(int[][].class::cast)
                .flatMap(CollectionConverter::asStream)
                .flatMap(CollectionConverter::asStream)
                .map(Integer.class::cast)
                .mapToInt(Integer::intValue)
                .sum();
        assertEquals(23, sum);

        stream = CollectionConverter.asStream(new int[0][][]);
        assertEquals(0, stream.count());
    }

    @Test
    public void testBadInput() {
        assertThrows(IllegalArgumentException.class,
                () -> CollectionConverter.asStream("Hello world"));
        assertThrows(IllegalArgumentException.class,
                () -> CollectionConverter.asStream(42));
        assertThrows(IllegalArgumentException.class,
                () -> CollectionConverter.asStream(List.of(1, 2, 3)));
    }
}
