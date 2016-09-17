package algorithms.sorting;

import org.junit.Test;

public class InsertionSortTest extends SortTest {

    @Override
    protected Sort getSortObject() {
        return new InsertionSort();
    }

    @Test
    public void testSort() throws Exception {
        testPositive();
    }

    @Test
    public void testSortNull() throws Exception {
        Integer[] array = null;
        expectedException.expect(NullPointerException.class);
        new InsertionSort().sort(array);
    }


}