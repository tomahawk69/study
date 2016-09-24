package algorithms.sorting;

import org.study.concurrency.CompletableFeatureFun;

/**
 * Get the k-th top element
 * O(n log n)
 */
public class QuickSelect {
    private final QuickSort quickSort = new QuickSort();

    public Comparable select(Comparable[] items, int k) {
        new Shuffling().sort(items);
        int lo = 0;
        int hi = items.length - 1;
        while (hi > lo) {
            int j = quickSort.partition(items, lo, hi);
            if (k < j) {
                hi = j - 1;
            } else if (k > j) {
                lo = j + 1;
            } else {
                return items[k];
            }
        }
        return items[k];
    }



}
