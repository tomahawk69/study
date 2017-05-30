package algorithms.common.quickfindunion;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Improved version of Quick union
 * Added arrays of weights
 *
 * Union: O(lg(n))
 * Find: O(lg(n))
 */
public class WeightedQuickUnion extends QuickUnion {
    private final int[] weight;

    public WeightedQuickUnion(int size) {
        super(size);
        weight = IntStream.range(0, size).map(i -> 1).toArray();
    }

    @Override
    public void union(int u, int v) {
        int vu = findRoot(u);
        int vv = findRoot(v);
        if (heap[vv] == vu || heap[vu] == vv) {
            return;
        }
        if (weight[vu] > weight[vv]) {
            heap[vv] = heap[vu];
            weight[vu] = weight[vv] + weight[vu];
        } else {
            heap[vu] = heap[vv];
            weight[vv] = weight[vv] + weight[vu];
        }
    }

    public int getWeight(int u) {
        return weight[u];
    }

    @Override
    public String toString() {
        return "WeightedQuickUnion{" +
                "heap=" + Arrays.stream(heap).boxed().map(String::valueOf).collect(Collectors.joining(" "));
    }

}
