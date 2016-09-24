package algorithms.tasks.hedgehog;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.atMost;

public class SolutionDijkstraTest {
    private ThreadLocalRandom random = ThreadLocalRandom.current();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testIterateGarden3x3() throws Exception {
        SolutionDijkstra solution = spy(SolutionDijkstra.class);
        Animal animal = new Hedgehog();

        Garden garden = new Garden(3, 3);

        garden.setApplesCount(0, 0, 1);
        garden.setApplesCount(0, 1, 2);
        garden.setApplesCount(0, 2, 3);

        garden.setApplesCount(1, 0, 1);
        garden.setApplesCount(1, 1, 2);
        garden.setApplesCount(1, 2, 3);

        garden.setApplesCount(2, 0, 1);
        garden.setApplesCount(2, 1, 2);
        garden.setApplesCount(2, 2, 3);

        assertEquals(12, solution.getMaxApples(garden, animal));
        verify(solution, atMost(3 * 3)).processField(any(), any(), any(), any(), any());
    }

    @Test
    public void testIterateGarden10x10() throws Exception {
        SolutionDijkstra solution = spy(SolutionDijkstra.class);
        Animal animal = new Hedgehog();
        int size = 5;

        Garden garden = new Garden(size, size);

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                garden.setApplesCount(x, y, random.nextInt(size));
            }
        }
        assertTrue(solution.getMaxApples(garden, animal) > 0);
        verify(solution, atMost(size * size)).processField(any(), any(), any(), any(), any());
    }

    @Test
    public void testIterateEmpty() throws Exception {
        SolutionDijkstra solution = spy(SolutionDijkstra.class);
        Animal animal = new Hedgehog();
        int size = 0;

        Garden garden = new Garden(size, size);

        assertEquals(0, solution.getMaxApples(garden, animal));
    }

    @Test
    public void testIterateNull() throws Exception {
        SolutionDijkstra solution = spy(SolutionDijkstra.class);
        Animal animal = new Hedgehog();

        Garden garden = null;

        expectedException.expect(NullPointerException.class);
        assertEquals(0, solution.getMaxApples(garden, animal));
    }

}