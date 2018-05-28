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

    @Test
    public void testSpecific1() throws Exception {
//        Integer[] array = {0, 1, 0, 2, 0, 7, 0, 4, 0, 2, 0};
        Integer[] array = {0, 7, 0, 2, 3, 2, 2, 8, 6, 8, 1, 0};
        new QuickSort().sort(array);
        validateSort(array);
    }

    @Test
    public void testSpecific2() throws Exception {
        Integer[] array = {9, 1, 0, 2, 0, 7, 0, 4, 0, 2, 0};
        new QuickSort().sort(array);
        validateSort(array);
    }

}