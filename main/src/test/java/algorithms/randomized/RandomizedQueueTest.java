package algorithms.randomized;

import antlr.collections.impl.IntRange;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class RandomizedQueueTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testEnqueueNull() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        expectedException.expect(NullPointerException.class);
        queue.enqueue(null);
    }

    @Test
    public void testDequeEmptyDeque() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        queue.dequeue();
        expectedException.expect(NoSuchElementException.class);
        queue.dequeue();
    }

    @Test
    public void testSampleEmptyDeque() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        assertNotNull(queue.sample());
        assertNotNull(queue.dequeue());
        expectedException.expect(NoSuchElementException.class);
        queue.sample();
    }

    @Test
    public void testRemoveAllAdded() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 10 * 4; i++) {
            queue.enqueue(i);
        }
        for (int i = 10 * 4 - 1; i >= 0; i--) {
            queue.sample();
            queue.dequeue();
        }
        assertEquals(0, queue.size());
    }

    @Test
    public void testIterator() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        int size = 40;
        for (int i = 0; i < size; i++) {
            queue.enqueue(i);
        }
        int n = 0;
        int[] result = new int[size];
        for (Integer value : queue) {
            result[n++] = value;
        }
        assertEquals(size, n);
        Arrays.sort(result);
        int[] expectedResult = IntStream.range(0, size).toArray();
        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void testTwoNestedIterators() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        int size = 10;
        for (int i = 0; i < size; i++) {
            queue.enqueue(i);
        }
        int[] result1 = new int[size];
        int[] result2 = new int[size];
        int n = 0;
        for (int i : queue) {
            result1[n++] = i;
        }
        n = 0;
        for (int i : queue) {
            result2[n++] = i;
        }
        assertFalse(Arrays.equals(result1, result2));
    }
}