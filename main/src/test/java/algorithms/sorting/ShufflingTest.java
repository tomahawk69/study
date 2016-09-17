package algorithms.sorting;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class ShufflingTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void sort() throws Exception {
        int size = 100;
        Integer[] items = IntStream.range(0, size).boxed().toArray(Integer[]::new);
        Shuffling shuffling = new Shuffling();
        Integer[] unexpectedResult = Arrays.copyOf(items, size);
        shuffling.sort(items);
        assertFalse(Arrays.equals(unexpectedResult, items));
    }

    @Test
    public void sortZeroSize() throws Exception {
        int size = 0;
        Integer[] items = IntStream.range(0, size).boxed().toArray(Integer[]::new);
        Shuffling shuffling = new Shuffling();
        Integer[] expectedResult = Arrays.copyOf(items, size);
        shuffling.sort(items);
        assertTrue(Arrays.equals(expectedResult, items));
    }

    @Test
    public void sortNull() throws Exception {
        Integer[] items = null;
        expectedException.expect(NullPointerException.class);
        new Shuffling().sort(items);
    }

}