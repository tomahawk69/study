package algorithms.sorting;

/**
 * Sort arrays "in place", no extra space is required
 * Not a stable
 * O(n lg n)
 * <p>
 * Medians:
 * - middle element for small arrays
 * - median of 3 for middle-size arrays
 * - Turkey's ninther for big arrays
 */
public class QuickSort extends Sort {
    private static final int CUTOFF = 10;
    private static final int MAX_SMALL_ARRAY = 30;
    private static final int MAX_MIDDLE_ARRAY = 1000;
    private InsertionSort insertionSort = new InsertionSort();


    @Override
    public void sort(Comparable[] items) {
        //new Shuffling().sort(items);
        sort(items, 0, items.length - 1);
    }

    @Override
    public void sort(Comparable[] items, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        if (hi - lo < CUTOFF) {
            insertionSort.sort(items, lo, hi + 1);
        } else {
            //swap(items, lo, findMedian(items, lo, hi));
            int j = partition(items, lo, hi);
            sort(items, lo, j - 1);
            sort(items, j + 1, hi);
        }
    }

    private int findMedian(Comparable[] items, int lo, int hi) {
        int arraySize = hi - lo;
        if (arraySize < MAX_SMALL_ARRAY) {
            return (hi + lo) / 2;
        } else if (arraySize < MAX_MIDDLE_ARRAY) {
            return findMedianOf3(items, lo, hi, (hi + lo) / 2);
        } else {
            return findMedianOfNinther(items, lo, hi);
        }
    }

    private int findMedianOfNinther(Comparable[] items, int lo, int hi) {
        int bound1 = (hi + lo) / 3;
        int bound2 = bound1 + bound1 - lo;
        return findMedianOf3(items,
                findMedianOf3(items, lo, bound1, (lo + bound1)/2),
                findMedianOf3(items, bound1 + 1, bound2, (bound1 + 1 + bound2)/2),
                findMedianOf3(items, bound2 + 1, hi, (bound2 + 1 + hi)/2));
    }

    private int findMedianOf3(Comparable[] items, int lo, int hi, int me) {
        if (items[lo].compareTo(items[me]) <= 0) {
            if (items[me].compareTo(items[hi]) <= 0) {
                return me;
            } else {
                if (items[lo].compareTo(items[hi]) <= 0) {
                    return hi;
                } else {
                    return lo;
                }
            }
        } else {
            if (items[lo].compareTo(items[hi]) <= 0) {
                return lo;
            } else {
                if (items[me].compareTo(items[hi]) <= 0) {
                    return hi;
                } else {
                    return me;
                }
            }
        }
    }

    int partition(Comparable[] items, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(items, ++i, lo) && i < hi) ;
            while (less(items, lo, --j) && j > lo) ; // Omitted  && j > lo
            if (i >= j) break;
            swap(items, i, j);
        }
        swap(items, lo, j);
        return j;
    }

}

