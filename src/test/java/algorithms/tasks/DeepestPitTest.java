package algorithms.tasks;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeepestPitTest {

    @Test
    public void solutionBasic() throws Exception {
        int[] array = {0, 1, 3, -2, 0, 1, 0, -3, 2, 3};
        int expected = 4;
        int result = new DeepestPit().solution(array);
        assertEquals(expected, result);
    }

    @Test
    public void solutionTowPits() throws Exception {
        int[] array = {0, 1, 0, 2, 0, -1, 0, 3};
        int expected = 3;
        int result = new DeepestPit().solution(array);
        assertEquals(expected, result);
    }

    @Test
    public void solutionNegative() throws Exception {
        int[] array = {0, 1, 2, -1, -3, -5};
        int expected = -1;
        int result = new DeepestPit().solution(array);
        assertEquals(expected, result);
    }

    @Test
    public void solutionNegative2() throws Exception {
        int[] array = {0, 1, 2, 3, 4, 5};
        int expected = -1;
        int result = new DeepestPit().solution(array);
        assertEquals(expected, result);
    }

    @Test
    public void solutionTriple() throws Exception {
        int[] array = {0, -1, 2};
        int expected = 1;
        int result = new DeepestPit().solution(array);
        assertEquals(expected, result);
    }

    @Test
    public void solutionNegative3() throws Exception {
        int[] array = {0, -1};
        int expected = -1;
        int result = new DeepestPit().solution(array);
        assertEquals(expected, result);
    }

    @Test
    public void solutionNegative4() throws Exception {
        int[] array = {};
        int expected = -1;
        int result = new DeepestPit().solution(array);
        assertEquals(expected, result);
    }

    @Test
    public void solutionBig() throws Exception {
        int[] array = {0, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 3};
        int expected = 2;
        int result = new DeepestPit().solution(array);
        assertEquals(expected, result);
    }

    @Test
    public void solutionBig2() throws Exception {
        int[] array = {0, -100_000_000, 100_000_000};
        int expected = 100_000_000;
        int result = new DeepestPit().solution(array);
        assertEquals(expected, result);
    }

}