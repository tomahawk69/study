package algorithms.tasks.eightpuzzle;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

import static org.junit.Assert.*;

public class BoardTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testNull() throws Exception {
        expectedException.expect(NullPointerException.class);
        new Board(null);
    }

    @Test
    public void testNeighbors() throws Exception {
        int[][] blocks = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board = new Board(blocks);

        List<Board> expectedResults = new ArrayList<>();

        int[][] blocksResult1 = {{8, 1, 3}, {0, 4, 2}, {7, 6, 5}};
        expectedResults.add(new Board(blocksResult1));

        int[][] blocksResult2 = {{8, 1, 3}, {4, 2, 0}, {7, 6, 5}};
        expectedResults.add(new Board(blocksResult2));

        int[][] blocksResult3 = {{8, 0, 3}, {4, 1, 2}, {7, 6, 5}};
        expectedResults.add(new Board(blocksResult3));

        int[][] blocksResult4 = {{8, 1, 3}, {4, 6, 2}, {7, 0, 5}};
        expectedResults.add(new Board(blocksResult4));

        Set<Board> results = new HashSet<>();
        Iterable<Board> iterables = board.neighbors();
        assertNotNull(iterables);
        for (Board b : iterables) {
            assertTrue(results.add(b));
            assertTrue(expectedResults.contains(b));
        }
        assertEquals(expectedResults.size(), results.size());
    }

    @Test
    public void testNeighborsTop() throws Exception {
        int[][] blocks = {{0, 1, 3}, {4, 8, 2}, {7, 6, 5}};
        Board board = new Board(blocks);

        List<Board> expectedResults = new ArrayList<>();

        int[][] blocksResult1 = {{1, 0, 3}, {4, 8, 2}, {7, 6, 5}};
        expectedResults.add(new Board(blocksResult1));

        int[][] blocksResult2 = {{4, 1, 3}, {0, 8, 2}, {7, 6, 5}};
        expectedResults.add(new Board(blocksResult2));

        Set<Board> results = new HashSet<>();
        Iterable<Board> iterables = board.neighbors();
        assertNotNull(iterables);
        for (Board b : iterables) {
            assertTrue(results.add(b));
            assertTrue(expectedResults.contains(b));
        }
        assertEquals(expectedResults.size(), results.size());
    }

    @Test
    public void testNeighborsBottom() throws Exception {
        int[][] blocks = {{5, 1, 3}, {4, 8, 2}, {7, 6, 0}};
        Board board = new Board(blocks);

        List<Board> expectedResults = new ArrayList<>();

        int[][] blocksResult1 = {{5, 1, 3}, {4, 8, 2}, {7, 0, 6}};
        expectedResults.add(new Board(blocksResult1));

        int[][] blocksResult2 = {{5, 1, 3}, {4, 8, 0}, {7, 6, 2}};
        expectedResults.add(new Board(blocksResult2));

        Set<Board> results = new HashSet<>();
        Iterable<Board> iterables = board.neighbors();
        assertNotNull(iterables);
        for (Board b : iterables) {
            assertTrue(results.add(b));
            assertTrue(expectedResults.contains(b));
        }
        assertEquals(expectedResults.size(), results.size());
    }

    @Test
    public void testEqualsPositive() throws Exception {
        int[][] blocks = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board1 = new Board(blocks.clone());
        Board board2 = new Board(blocks.clone());
        assertTrue(board1.equals(board2));
        assertTrue(board2.equals(board1));
    }

    @Test
    public void testEqualsNegative() throws Exception {
        int[][] blocks1 = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board1 = new Board(blocks1);
        int[][] blocks2 = {{8, 1, 4}, {3, 0, 2}, {7, 6, 5}};
        Board board2 = new Board(blocks2);
        assertFalse(board1.equals(board2));
        assertFalse(board2.equals(board1));
    }

    @Test
    public void testEqualsNegativeDimensions() throws Exception {
        int[][] blocks1 = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board1 = new Board(blocks1);
        int[][] blocks2 = {{1, 2}, {3, 0}};
        Board board2 = new Board(blocks2);
        assertFalse(board1.equals(board2));
        assertFalse(board2.equals(board1));
    }

    @Test
    public void testHamming() throws Exception {
        int[][] blocks = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board = new Board(blocks);
        assertEquals(5, board.hamming());
    }

    @Test
    public void testIsGoal() throws Exception {
        int[][] blocks = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Board board = new Board(blocks);
        assertTrue(board.isGoal());
    }

    @Test
    public void testManhattan() throws Exception {
        int[][] blocks = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board = new Board(blocks);
        assertEquals(10, board.manhattan());
    }
}