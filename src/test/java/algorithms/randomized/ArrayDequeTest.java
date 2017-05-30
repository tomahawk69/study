package algorithms.randomized;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testAddFirstNull() throws Exception {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        expectedException.expect(NullPointerException.class);
        deque.addFirst(null);
    }

    @Test
    public void testAddLastNull() throws Exception {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        expectedException.expect(NullPointerException.class);
        deque.addLast(null);
    }

    @Test
    public void testRemoveFirstEmptyDeque() throws Exception {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.removeFirst();
        expectedException.expect(NoSuchElementException.class);
        deque.removeFirst();
    }

    @Test
    public void testRemoveLastEmptyDeque() throws Exception {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.removeLast();
        expectedException.expect(NoSuchElementException.class);
        deque.removeLast();
    }

    @Test
    public void testRemoveAllAdded() throws Exception {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 10 * 4; i++) {
            if (i % 2 == 0) {
                deque.addFirst(i);
            } else {
                deque.addLast(i);
            }
        }
        assertEquals(40, deque.size());
        for (int i = 10 * 4 - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                assertEquals(i, deque.removeFirst().intValue());
            } else {
                assertEquals(i, deque.removeLast().intValue());

            }
        }
        assertEquals(0, deque.size());
    }

    @Test
    public void testSize() throws Exception {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addFirst(2);
        assertEquals(2, deque.size());
        deque.removeFirst();
        assertEquals(1, deque.size());
        deque.removeLast();
        assertEquals(0, deque.size());
    }

    @Test
    public void testTwoNestedIterators() throws Exception {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int size = 10;
        for (int i = 0; i < size; i++) {
            deque.addLast(i);
        }
        int n = 0;
        for (int i : deque) {
            assertEquals(n++, i);
            int n1 = 0;
            for (int j : deque) {
                assertEquals(n1++, j);
            }
            assertEquals(size, n1);
        }
        assertEquals(size, n);
    }
}