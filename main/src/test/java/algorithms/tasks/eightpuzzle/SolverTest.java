package algorithms.tasks.eightpuzzle;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class SolverTest {
    private Board solvableBoard;
    private Board unsolvableBoard;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testNull() throws Exception {
        expectedException.expect(NullPointerException.class);
        new Solver(null);
    }

    @Before
    public void setUp() throws Exception {
        int[][] blocks = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        solvableBoard = new Board(blocks);
        int[][] blocksUnsolvable = {{1, 2, 3}, {4, 5, 6}, {8, 7, 0}};
        unsolvableBoard = new Board(blocksUnsolvable);
    }

    @Test
    public void testIsSolvablePositive() throws Exception {
        Solver solver = new Solver(solvableBoard);
        assertTrue(solver.isSolvable());
        assertEquals(4, solver.moves());
        Iterable<Board> result = solver.solution();
        assertNotNull(result);
    }

    @Test
    public void testSolutionNegative() throws Exception {
        Solver solver = new Solver(unsolvableBoard);
        assertFalse(solver.isSolvable());
        assertNull(solver.solution());
    }
}