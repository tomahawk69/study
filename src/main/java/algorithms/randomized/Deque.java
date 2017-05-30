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
 *
 * LinkedList implementation
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int size = 0;

    // is the deque empty?
    public boolean isEmpty() {
        return last == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node node = new Node(item);
        if (first != null) {
            first.previous = node;
            node.next = first;
        }
        first = node;
        if (last == null) {
            last = node;
        }
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node node = new Node(item);
        if (last != null) {
            last.next = node;
            node.previous = last;
        }
        last = node;
        if (first == null) {
            first = node;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item result = first.value;
        first = first.next;
        if (first != null) {
            first.previous = null;
        } else {
            last = null;
        }
        size--;
        return result;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item result = last.value;
        last = last.previous;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        size--;
        return result;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current;

        public DequeIterator(Node startNode) {
            this.current = startNode;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item result = current.value;
            current = current.next;
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator(first);
    }

    private class Node {
        private Node previous;
        private Node next;
        private final Item value;

        private Node(Item value) {
            this.value = value;
        }
    }

    // unit testing
    public static void main(String[] args) {

    }

}