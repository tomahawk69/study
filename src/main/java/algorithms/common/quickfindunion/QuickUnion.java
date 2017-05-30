package algorithms.common.quickfindunion;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Implementation of Quick-union algorithm
 *
 * array of N predefined elements
 * operations: find, union
 * no additions, no deletions, no un-union
 * <p>
 * find: O(n) (O(1) to O(n))
 * union: O(n) (O(1) to O(n))
 * <p>
 * Thread-unsafe
 */
public class QuickUnion implements QuickFindUnion {
    protected final int[] heap;


    /**
     * Create and initialize the array
     *
     * @param size of arrays, greater than 0
     */
    public QuickUnion(int size) {
        if (size < 1) {
            throw new IllegalArgumentException();
        }
        heap = IntStream.range(0, size).toArray();
    }


    public int findRoot(int u) {
        while (heap[u] != u) {
            u = heap[u];
        }
        return u;
    }

    /**
     * Return true if element with index u connected to the same root as element with index v
     *
     * @param u index of first element
     * @param v index of second element
     * @return is u connected to v
     */
    public boolean find(int u, int v) {
        return findRoot(u) == findRoot(v);
    }


    /**
     * Connect elements u to an elements v
     *
     * @param u first element
     * @param v second element
     */
    public void union(int u, int v) {
        heap[findRoot(v)] = findRoot(u);
    }

    @Override
    public String toString() {
        return "QuickUnion{" +
                "heap=" + Arrays.stream(heap).boxed().map(String::valueOf).collect(Collectors.joining(" "));
    }

    public int depth(int u) {
        int result = 0;
        while (heap[u] != u) {
            result++;
            u = heap[u];
        }
        return result;
    }

    public int maxDepth() {
        return IntStream.range(0, heap.length).map(this::depth).max().getAsInt();
    }
}
