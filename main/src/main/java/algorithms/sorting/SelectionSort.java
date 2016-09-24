package algorithms.sorting;

/**
 * For every next element i find min value in the next values and swap it with element i
 * (N^2)/2 - quadratic
 * Not stable
 */
public class SelectionSort extends Sort {

    @Override
    public void sort(Comparable[] items) {
        sort(items, 0, items.length);
    }

    @Override
    public void sort(Comparable[] items, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            int min = i;
            for (int j = i + 1; j < hi; j++) {
                if (items[j].compareTo(items[min]) < 0) {
                    min = j;
                }
            }
            swap(items, i, min);
        }
    }
}
