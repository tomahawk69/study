package algorithms.tasks;

import algorithms.tasks.CountMultiplicativePairs;
import org.junit.Test;

import static org.junit.Assert.*;

public class CountMultiplicativePairsTest {

    @Test
    public void soultion() throws Exception {
        int[] a = {0,       1,          2,  2,  3,  5};
        int[] b = {500_000, 500_000,    0,  0,  0,  20_000};
        int expected = 8;
        int result = new CountMultiplicativePairs().soultion(a, b);
        assertEquals(expected, result);
    }

    @Test
    public void soultionN() throws Exception {
        int[] a = {0,       1,          2,  2,  3,  5};
        int[] b = {500_000, 500_000,    0,  0,  0,  20_000};
        int expected = 8;
        int result = new CountMultiplicativePairs().soultionN(a, b);
        assertEquals(expected, result);
    }

}