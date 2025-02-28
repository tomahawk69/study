package algorithms.randomized;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**Á
 * A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random
 * from items in the data structure
 * Corner cases. The order of two or more iterators to the same randomized queue must be mutually independent;
 * each iterator must maintain its own random order. Throw a java.lang.NullPointerException if the client attempts
 * to add a null item; throw a java.util.NoSuchElementException if the client attempts to sample or dequeue an item
 * from an empty randomized queue; throw a java.lang.UnsupportedOperationException if the client
 * calls the remove() method in the iterator; throw a java.util.NoSuchElementException if the client
 * calls the next() method in the iterator and there are no more items to return.
 * <p>
 * Performance requirements.
 * Your randomized queue implementation must support each randomized queue operation (besides creating an iterator)
 * in constant amortized time.
 * That is, any sequence of m randomized queue operations (starting from an empty queue) should take
 * at most cm steps in the worst case,
 * for some constant c. A randomized queue containing n items must use at most 48n + 192 bytes of memory.
 * Additionally, your iterator implementation must support operations next() and hasNext() in constant worst-case time;
 * and construction in linear time; you may (and will need to) use a linear amount of extra memory per iterator.
 * generic parameter
 * <p>
 * Array implementation
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int MIN_SIZE = 1;
    private Item[] pool;
    private int end = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        pool = (Item[]) new Object[MIN_SIZE];
    }

    // is the queue empty?
    public boolean isEmpty() {
        return end == 0;
    }

    // return the number of items on the queue
    public int size() {
        return end;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (end == pool.length) {
            resize(end * 2);
        }
        pool[end++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (pool.length > MIN_SIZE && end < pool.length / 4) {
            resize(Math.max(MIN_SIZE, pool.length / 2));
        }
        int i = getUniformIndex();
        Item result = pool[i];
        if (i < end - 1) {
            pool[i] = pool[end - 1];
        }
        pool[--end] = null;
        return result;
    }

    private int getUniformIndex() {
        return StdRandom.uniform(end);
    }

    private void resize(int newSize) {
        Item[] newPool = (Item[]) new Object[newSize];
        for (int i = 0; i < end; i++) {
            newPool[i] = pool[i];
        }
        pool = newPool;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int i = getUniformIndex();
        return pool[i];
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int[] indices;
        private int index = 0;

        public RandomizedQueueIterator(int size) {
            this.indices = new int[size];
            for (int i = 0; i < size; i++) {
                this.indices[i] = i;
            }
            StdRandom.shuffle(this.indices);
        }

        @Override
        public boolean hasNext() {
            return index < indices.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return pool[indices[index++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator(end);
    }

    // unit testing
    public static void main(String[] args) {

    }
}