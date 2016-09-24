package algorithms.sorting;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSort3WayTest extends SortTest {

    @Override
    protected Sort getSortObject() {
        return new QuickSort3Way();
    }

    @Test
    public void testSort() throws Exception {
        testPositive();
    }

    @Test
    public void testSortNull() throws Exception {
        Integer[] array = null;
        expectedException.expect(NullPointerException.class);
        new QuickSort3Way().sort(array);
    }

}