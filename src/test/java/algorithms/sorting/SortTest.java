package algorithms.sorting;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class SortTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    protected Sort getSortObject() {
        throw new UnsupportedOperationException();
    }

    private void testArray(int size) {
        Integer[] array = createRandomArrayOfIntegers(size);
        getSortObject().sort(array);
        assertArrayEquals(IntStream.range(0, size).boxed().toArray(), array);
    }

    protected Integer[] createRandomArrayOfIntegers(int size) {
        List<Integer> list = IntStream.range(0, size).boxed().collect(Collectors.toList());
        Collections.shuffle(list);
        return list.toArray(new Integer[list.size()]);
    }

    public void testPositive() {
        testArray(100);
        testArray(0);
        testArray(1);
    }

    public void validateSort(Integer[] input) {
        for (int i = 1; i < input.length; i++) {
            assertTrue(String.format("[%d] error: %d < %d", i, input[i], input[i - 1]), input[i] >=  input[i - 1]);
        }
    }
}
