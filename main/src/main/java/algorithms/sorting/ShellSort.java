package algorithms.sorting;

import java.util.Comparator;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

/**
 * Insertion sort with the stride length h
 * Basic implementation gives =almost== sorted array
 * Make several decreasing sorts (e.g. 7 - 3 - 1)
 * Increments:
 * - 2^x - 1
 * - 3x + 1
 * 1..5..41..
 *
 * Complexity is O(n^(3/2))
 *
 * Not stable
 */
public class ShellSort extends Sort {
    private static final int GAPS_SIZE_LIMIT = 20;

    @Override
    public void sort(Comparable[] items) {
        sort(items, 0, items.length);
    }

    @Override
    public void sort(Comparable[] items, int lo, int hi) {
        Integer[] gaps = IntStream.generate(new IntSupplier() {
            private int current = lo;
            @Override
            public int getAsInt() {
                return current++ * 3 + 1;
            }
        }).limit(GAPS_SIZE_LIMIT).boxed().sorted(Comparator.reverseOrder()).toArray(Integer[]::new);
        for (Integer gap : gaps) {
            for (int j = lo; j < hi; j += gap) {
                for (int k = j; k > 0; k -= gap) {
                    if (items[k - gap].compareTo(items[k]) > 0) {
                        swap(items, k, k - gap);
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
