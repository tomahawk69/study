package algorithms.sorting;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;

public class SortTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    protected Sort getSortObject() {
        throw new UnsupportedOperationException();
    }

    private void testArray(int size) {
        List<Integer> list = IntStream.range(0, size).boxed().collect(Collectors.toList());
        Collections.shuffle(list);
        Integer[] array = list.toArray(new Integer[list.size()]);
        getSortObject().sort(array);
        assertArrayEquals(IntStream.range(0, size).boxed().toArray(), array);
    }

    public void testPositive() {
        testArray(100);
        testArray(0);
        testArray(1);
    }


}
