package algorithms.tasks;

import algorithms.tasks.EquilibriumIndex;
import org.junit.Test;

import static org.junit.Assert.*;

public class EquilibriumIndexTest {

    @Test
    public void solutionBasic() throws Exception {
        int[] array = {-1, 3, -4, 5, 1, -6, 2, 1};
        int expected = 1;
        int result = new EquilibriumIndex().solution(array);
        assertEquals(expected, result);
    }

    @Test
    public void solutionSingle() throws Exception {
        int[] array = {0};
        int expected = 0;
        int result = new EquilibriumIndex().solution(array);
        assertEquals(expected, result);
    }

    @Test
    public void solutionDouble() throws Exception {
        int[] array = {-1, 0};
        int expected = 0;
        int result = new EquilibriumIndex().solution(array);
        assertEquals(expected, result);
    }

    @Test
    public void solutionBig() throws Exception {
        int[] array = {1, Integer.MAX_VALUE, 1, 1, Integer.MAX_VALUE};
        int expected = 2;
        int result = new EquilibriumIndex().solution(array);
        assertEquals(expected, result);
    }

    @Test
    public void solutionBig2() throws Exception {
        int[] array = {1, Integer.MAX_VALUE, 1, 1, Integer.MAX_VALUE};
        int expected = 2;
        int result = new EquilibriumIndex().solution(array);
        assertEquals(expected, result);
    }

    @Test
    public void solutionNegative() throws Exception {
        int[] array = {1, 1, 1, 1};
        int expected = -1;
        int result = new EquilibriumIndex().solution(array);
        assertEquals(expected, result);
    }

    @Test
    public void solution0() throws Exception {
        int[] array = {400, 1, -1};
        int expected = 0;
        int result = new EquilibriumIndex().solution(array);
        assertEquals(expected, result);
    }

    @Test
    public void solutionLast() throws Exception {
        int[] array = {1, -1, 0};
        int expected = 2;
        int result = new EquilibriumIndex().solution(array);
        assertEquals(expected, result);
    }

}