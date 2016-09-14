package algorithms.randomized;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class DequeTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testAddFirstNull() throws Exception {
        Deque<Integer> deque = new Deque<>();
        expectedException.expect(NullPointerException.class);
        deque.addFirst(null);
    }

    @Test
    public void testAddLastNull() throws Exception {
        Deque<Integer> deque = new Deque<>();
        expectedException.expect(NullPointerException.class);
        deque.addLast(null);
    }

    @Test
    public void testRemoveFirstEmptyDeque() throws Exception {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.removeFirst();
        expectedException.expect(NoSuchElementException.class);
        deque.removeFirst();
    }

    @Test
    public void testRemoveLastEmptyDeque() throws Exception {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.removeLast();
        expectedException.expect(NoSuchElementException.class);
        deque.removeLast();
    }

    @Test
    public void testRemoveAllAdded() throws Exception {
        int size = 40;
        Deque<Integer> deque = new Deque<>();
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                deque.addFirst(i);
            } else {
                deque.addLast(i);
            }
        }
        assertEquals(size, deque.size());
        for (int i = size - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                assertEquals(i, deque.removeFirst().intValue());
            } else {
                assertEquals(i, deque.removeLast().intValue());
            }
            assertEquals(i, deque.size());
        }
        assertEquals(0, deque.size());
    }

    @Test
    public void testSize() throws Exception {
        Deque<Integer> deque = new Deque<>();
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
        Deque<Integer> deque = new Deque<>();
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