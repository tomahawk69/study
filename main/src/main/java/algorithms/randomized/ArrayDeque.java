package algorithms.randomized;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double-ended queue or deque (pronounced "deck") is a generalization of a stack and a queue
 * that supports adding and removing items from either the front or the back of the data structure
 * <p>
 * Corner cases. Throw a java.lang.NullPointerException if the client attempts to add a null item;
 * throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque;
 * throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator;
 * throw a java.util.NoSuchElementException if the client calls the next() method in the iterator
 * and there are no more items to return.
 * <p>
 * Performance requirements.
 * Your deque implementation must support each deque operation in constant worst-case time.
 * A deque containing n items must use at most 48n + 192 bytes of memory.
 * and use space proportional to the number of items currently in the deque.
 * Additionally, your iterator implementation must support each operation (including construction)
 * in constant worst-case time.
 */
public class ArrayDeque<Item> implements Iterable<Item> {
    private static final int MIN_SIZE = 10;
    private Item[] pool;
    private int start = MIN_SIZE / 2;
    private int end = start;

    // construct an empty deque
    public ArrayDeque() {
        pool = (Item[]) new Object[MIN_SIZE];
    }

    // is the deque empty?
    public boolean isEmpty() {
        return end == start;
    }

    // return the number of items on the deque
    public int size() {
        return end - start;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (start <= 1) {
            resize();
        }
        pool[--start] = item;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (end == pool.length) {
            resize();
        }
        pool[end++] = item;
    }

    private void resize() {
        int size = size();
        if (size > pool.length * 3 / 4) {
            size = pool.length * 2;
        } else if (size < pool.length / 4) {
            // decrease
            size = Math.max(MIN_SIZE, pool.length / 2);
        } else {
            // de-fragmentation
            size = pool.length;
        }
        Item[] newPool = (Item[]) new Object[size];
        int median = (size - (end - start)) / 2;
        for (int i = start; i < end; i++) {
            newPool[i - start + median] = pool[i];
        }
        end = median + end - start;
        start = median;
        pool = newPool;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (end - start < pool.length / 4.0) {
            resize();
        }
        Item result = pool[start];
        pool[start++] = null;
        return result;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (end - start < pool.length / 4.0) {
            resize();
        }
        Item result = pool[--end];
        pool[end] = null;
        return result;
    }

    private class DequeIterator implements Iterator<Item> {
        private int startIndex;
        private final int endIndex;

        public DequeIterator(int start, final int end) {
            this.startIndex = start;
            this.endIndex = end;
        }

        @Override
        public boolean hasNext() {
            return endIndex > startIndex;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return pool[startIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator(start, end);
    }

    // unit testing
    public static void main(String[] args) {

    }

}