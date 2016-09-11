package algorithms.common;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Implementation of quick-find algorithm:
 * array of N predefined elements
 * operations: find, union
 * no additions, no deletions, no un-union
 * <p>
 * find: O(1)
 * union: O(n)
 * <p>
 * Thread-unsafe
 */
public class QuickFind implements QuickFindUnion {
    private final int[] heap;

    /**
     * Create and initialize the array
     *
     * @param size of arrays, greater than 0
     */
    public QuickFind(int size) {
        if (size < 1) {
            throw new IllegalArgumentException();
        }
        heap = IntStream.range(0, size).toArray();
    }

    /**
     * Return true if element with index u connected to the element with index v
     *
     * @param u index of first element
     * @param v index of second element
     * @return is u connected to v
     */
    public boolean find(int u, int v) {
        return heap[u] == heap[v];
    }


    /**
     * Connect elements of a group u to a group of elements v
     *
     * @param u first element
     * @param v second element
     */
    public void union(int u, int v) {
        int uv = heap[u];
        if (uv != heap[v]) {
            IntStream.range(0, heap.length).filter(i -> heap[i] == uv).forEach(i -> heap[i] = heap[v]);
        }
    }

    @Override
    public String toString() {
        return "QuickFind{" +
                "heap=" + Arrays.stream(heap).boxed().map(String::valueOf).collect(Collectors.joining(" "));
    }
}
