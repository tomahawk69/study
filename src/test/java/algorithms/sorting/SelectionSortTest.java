package algorithms.sorting;

import org.junit.Test;

public class SelectionSortTest extends SortTest {

    @Override
    protected Sort getSortObject() {
        return new SelectionSort();
    }

    @Test
    public void testSort() throws Exception {
        testPositive();
    }

    @Test
    public void testSortNull() throws Exception {
        Integer[] array = null;
        expectedException.expect(NullPointerException.class);
        new SelectionSort().sort(array);
    }

}