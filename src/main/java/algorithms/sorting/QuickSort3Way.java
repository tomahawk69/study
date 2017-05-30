package algorithms.sorting;

/**
 * Fixed QuickSort algorithm to deal with duplicates
 * Instead of one median used low and high boundary for median
 * Entropy-optimal
 *
 */
public class QuickSort3Way extends Sort {

    @Override
    public void sort(Comparable[] items) {
        sort(items, 0, items.length - 1);
    }

    @Override
    public void sort(Comparable[] items, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int lt = lo, gt = hi;
        Comparable v = items[lt];
        int i = lt;
        while (i <= gt) {
            int cmp = items[i].compareTo(v);
            if (cmp < 0) {
                swap(items, lt++, i++);
            } else if (cmp > 0) {
                swap(items, i, gt--);
            } else {
                i++;
            }
        }
        sort(items, lo, lt - 1);
        sort(items, gt + 1, hi);
    }
}
