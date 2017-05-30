package algorithms.sorting;

import org.junit.Test;

public class QuickSortTest extends SortTest {

    @Override
    protected Sort getSortObject() {
        return new QuickSort();
    }

    @Test
    public void testSort() throws Exception {
        testPositive();
    }

    @Test
    public void testSortNull() throws Exception {
        Integer[] array = null;
        expectedException.expect(NullPointerException.class);
        new QuickSort().sort(array);
    }

}