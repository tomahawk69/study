package algorithms.sorting;

/**
 * For every i element swap it with every larger element to its left
 * (N^2)/4 or quadratic (N to (N^2)/2)
 * Good for partially-sorted or sorted arrays
 * Stable
 */
public class InsertionSort extends Sort {

    @Override
    public void sort(Comparable[] items) {
        sort(items, 0, items.length);
    }

    @Override
    public void sort(Comparable[] items, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            for (int j = i; j > lo; j--) {
                if (items[j - 1].compareTo(items[j]) > 0) {
                    swap(items, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}
