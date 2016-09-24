package algorithms.sorting;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuickSelectTest extends SortTest {


    @Test
    public void testSelect() throws Exception {
        int size = 100;
        Integer[] array = createRandomArrayOfIntegers(size);
        Integer expectedResult = 1;
        assertEquals(expectedResult, (Integer) new QuickSelect().select(array, 1));
        expectedResult = 99;
        assertEquals(expectedResult, (Integer) new QuickSelect().select(array, 99));
    }

    @Test
    public void testSortNull() throws Exception {
        Integer[] array = null;
        expectedException.expect(NullPointerException.class);
        new QuickSelect().select(array, 1);
    }

    @Test
    public void testSortArrayIndexOutOfBound() throws Exception {
        int size = 10;
        Integer[] array = createRandomArrayOfIntegers(size);
        expectedException.expect(ArrayIndexOutOfBoundsException.class);
        new QuickSelect().select(array, size);
    }

}