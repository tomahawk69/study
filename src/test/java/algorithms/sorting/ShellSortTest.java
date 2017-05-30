package algorithms.sorting;

import org.junit.Test;

public class ShellSortTest extends SortTest {

    @Override
    protected Sort getSortObject() {
        return new ShellSort();
    }

    @Test
    public void testSort() throws Exception {
        testPositive();
    }

    @Test
    public void testSortNull() throws Exception {
        Integer[] array = null;
        expectedException.expect(NullPointerException.class);
        new ShellSort().sort(array);
    }
}