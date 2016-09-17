package algorithms.sorting;

/**
 * For every next element i find min value in the next values and swap it with element i
 * (N^2)/2 - quadratic
 */
public class SelectionSort extends Sort {

    @Override
    public void sort(Comparable[] items) {
        for (int i = 0; i < items.length; i++) {
            int min = i;
            for (int j = i + 1; j < items.length; j++) {
                if (items[j].compareTo(items[min]) < 0) {
                    min = j;
                }
            }
            swap(items, i, min);
        }
    }
}
