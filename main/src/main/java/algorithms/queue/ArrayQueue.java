package algorithms.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayQueue<T> implements Iterable<T> {
    public static final int MIN_SIZE = 10;
    private T[] heap;
    private int start = 0;
    private int end = 0;

    public ArrayQueue() {
        this.heap = (T[]) new Object[MIN_SIZE];
    }

    public void enqueue(T value) {
        if (end == heap.length) {
            shrink();
        }
        heap[end++] = value;
    }

    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        T result = heap[start++];
        heap[start - 1] = null;
        if (heap.length > MIN_SIZE && end - start < heap.length / 4.0) {
            shrink();
        }
        return result;
    }

    private boolean isEmpty() {
        return end <= start;
    }

    private void shrink() {
        int size = (end - start);
        if (size < heap.length / 4) {
            size = Math.max(MIN_SIZE, heap.length / 2);
        } else if (size > heap.length * 3 / 4.0) {
            size = Math.max(MIN_SIZE, heap.length * 2);
        } else {
            size = Math.max(MIN_SIZE, heap.length);
        }
        T[] newHeap = (T[]) new Object[size];
        for (int i = 0; i < end - start; i++) {
            newHeap[i] = heap[start + i];
        }
        end = end - start;
        start = 0;
        heap = newHeap;
    }

    private class ArrayQueueIterator implements Iterator<T> {

        @Override
        public boolean hasNext() {
            return !isEmpty();
        }

        @Override
        public T next() {
            return dequeue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    public Iterator<T> iterator() {
        return new ArrayQueueIterator();
    }

    @Override
    public String toString() {
        return "ArrayQueue{" +
                "heap=" + IntStream.range(start, end).mapToObj(i -> String.valueOf(heap[i])).collect(Collectors.joining(" ")) +
                ", size=" + heap.length +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
