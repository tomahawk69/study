package algorithms.tasks.test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyFindArrayTest {

    private MyFindArray findArray;

    @Before
    public void testSetup() throws Exception {
        findArray = new MyFindArray();
    }

    @Test
    public void findArrayFromTaskDescription() throws Exception {
        //given
        final int[] array = {4, 9, 3, 7, 8};
        final int[] subArray = {3, 7};

        //when
        int result = findArray.findArray(array, subArray);

        //then
        assertEquals(2, result);
    }

    @Test
    public void findArrayFromTaskDescription2() throws Exception {
        //given
        final int[] array = {1, 3, 5};
        final int[] subArray = {1};

        //when
        int result = findArray.findArray(array, subArray);

        //then
        assertEquals(0, result);
    }

    @Test
    public void findArrayFromTaskDescription3() throws Exception {
        //given
        final int[] array = {7, 8, 9};
        final int[] subArray = {8, 9, 10};

        //when
        int result = findArray.findArray(array, subArray);

        //then
        assertEquals(-1, result);
    }

    @Test
    public void findArrayFromTaskDescription4() throws Exception {
        //given
        final int[] array = {0, 0, 3, 7, 0, 3, 7, 0};
        final int[] subArray = {3, 7};

        //when
        int result = findArray.findArray(array, subArray);

        //then
        assertEquals(5, result);
    }

    @Test
    public void findArrayNegativeNotFound() throws Exception {
        //given
        final int[] array = {4, 9, 3, 7, 8};
        final int[] subArray = {3, 7, 6};

        //when
        int result = findArray.findArray(array, subArray);

        //then
        assertEquals(-1, result);
    }

    @Test
    public void findArrayNegativeSubArrayIsGreater() throws Exception {
        //given
        final int[] array = {4, 9};
        final int[] subArray = {3, 7, 6};

        //when
        int result = findArray.findArray(array, subArray);

        //then
        assertEquals(-1, result);
    }

    @Test
    public void findArrayProceedDuplicates() throws Exception {
        //given
        final int[] array = {9, 3, 9, 3};
        final int[] subArray = {9, 3};

        //when
        int result = findArray.findArray(array, subArray);

        //then
        assertEquals(2, result);
    }

    @Test
    public void findArrayFindLastPosition2() throws Exception {
        //given
        final int[] array = {4, 9, 0, 9, 3};
        final int[] subArray = {9, 3};

        //when
        int result = findArray.findArray(array, subArray);

        //then
        assertEquals(3, result);
    }

    @Test
    public void findArrayFindSingletonSubArray() throws Exception {
        //given
        final int[] array = {4, 9, 0, 9, 3};
        final int[] subArray = {9};

        //when
        int result = findArray.findArray(array, subArray);

        //then
        assertEquals(3, result);
    }

    @Test(expected = NullPointerException.class)
    public void findArrayArrayIsNull() throws Exception {
        //given
        final int[] array = null;
        final int[] subArray = {9, 3};

        //when
        int result = findArray.findArray(array, subArray);

        //then
        assertEquals(-1, result);
    }

    @Test(expected = NullPointerException.class)
    public void findArraySubArrayIsNull() throws Exception {
        //given
        final int[] array = {4, 9, 0, 9, 3};
        final int[] subArray = null;

        //when
        int result = findArray.findArray(array, subArray);

        //then
        assertEquals(-1, result);
    }

    @Test
    public void findArrayEmptyArray() throws Exception {
        //given
        final int[] array = {};
        final int[] subArray = {3, 7, 6};

        //when
        int result = findArray.findArray(array, subArray);

        //then
        assertEquals(-1, result);
    }

    @Test
    public void findArrayEmptySubArray() throws Exception {
        //given
        final int[] array = {4, 9, 3, 7, 8};
        final int[] subArray = {};

        //when
        int result = findArray.findArray(array, subArray);

        //then
        assertEquals(-1, result);
    }


}