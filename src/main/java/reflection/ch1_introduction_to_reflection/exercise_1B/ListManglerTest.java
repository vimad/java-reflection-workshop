package reflection.ch1_introduction_to_reflection.exercise_1B;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListManglerTest {
    protected static final List<String> PI = List.of("3", "1", "4", "1", "5", "9");

    @Test
    public void testIncorrectMethodName() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ListMangler.reorder(
                        Arrays.asList("1", "2", "3"), "unknownMethod"));
    }

    @Test
    public void testNullMethodName() {
        Assertions.assertThrows(NullPointerException.class,
                () -> ListMangler.reorder(
                        Arrays.asList("1", "2", "3"), null));
    }

    @Test
    public void testImmutableInputList() {
        ListMangler.reorder(List.of("1", "2", "3"), "sort");
    }

    @Test
    public void testResultIsNotInputList() {
        List<String> input = new ArrayList<>();
        List<String> output = ListMangler.reorder(input, "sort");
        assertNotSame(input, output);
    }

    @Test
    public void testOriginalListStaysTheSame() {
        List<String> original = new ArrayList<>(PI);
        List<String> sorted = ListMangler.reorder(original, "sort");
        assertEquals("[1, 1, 3, 4, 5, 9]", sorted.toString());
        assertNotEquals(original, sorted);
        assertEquals(PI, original);
    }

    @Test
    public void testSort() {
        List<String> sorted = ListMangler.reorder(PI, "sort");
        assertEquals("[1, 1, 3, 4, 5, 9]", sorted.toString());
    }

    @Test
    public void testShuffle() {
        List<String> shuffle1 = ListMangler.reorder(PI, "shuffle");
        List<String> shuffle2 = ListMangler.reorder(PI, "shuffle");
        assertNotEquals(shuffle1, shuffle2);
        assertNotEquals(PI, shuffle1);
        assertNotEquals(PI, shuffle2);
    }

    @Test
    public void testReverse() {
        List<String> reversed = ListMangler.reorder(PI, "reverse");
        assertEquals("[9, 5, 1, 4, 1, 3]", reversed.toString());
    }
}
