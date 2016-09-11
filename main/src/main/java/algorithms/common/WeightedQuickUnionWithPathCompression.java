package algorithms.common;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Improved WeightedQuickUnion
 * Added path compression
 * <p>
 * Find: O(1)
 * Union: O(lg(n)) (from O(1))
 */
public class WeightedQuickUnionWithPathCompression extends WeightedQuickUnion {

    public WeightedQuickUnionWithPathCompression(int size) {
        super(size);
    }

    @Override
    public int findRoot(int u) {
        while (heap[u] != u) {
            heap[u] = heap[heap[u]];
            u = heap[u];
        }
        return u;
    }

    @Override
    public String toString() {
        return "WeightedQuickUnionWithPathCompression{" +
                "heap=" + Arrays.stream(heap).boxed().map(String::valueOf).collect(Collectors.joining(" "));
    }


}
