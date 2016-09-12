package algorithms.queue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ArrayQueueTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testOverflow() throws Exception {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < ArrayQueue.MIN_SIZE * 4 + 1; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < ArrayQueue.MIN_SIZE * 4 + 1; i++) {
            queue.dequeue();
        }
        assertTrue(true);
    }

    @Test
    public void testEnqueueDequeue() throws Exception {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < ArrayQueue.MIN_SIZE * 4 + 1; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < ArrayQueue.MIN_SIZE * 4 + 1; i++) {
            assertEquals(i, (int) queue.dequeue());
        }
        assertTrue(true);
    }

    @Test
    public void testDequeueOnEmptyQueue() throws Exception {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        queue.enqueue(0);
        queue.dequeue();
        expectedException.expect(NoSuchElementException.class);
        queue.dequeue();
    }

}