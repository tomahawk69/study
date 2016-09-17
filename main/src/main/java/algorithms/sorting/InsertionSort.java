package algorithms.sorting;

/**
 * For every i element swap it with every larger element to its left
 * (N^2)/4 or quadratic (N to (N^2)/2)
 * Good for partialy-sorted or sorted arrays
 */
public class InsertionSort extends Sort {

    @Override
    public void sort(Comparable[] items) {
        for (int i = 0; i < items.length; i++) {
            for (int j = i; j > 0; j--) {
                if (items[j - 1].compareTo(items[j]) > 0) {
                    swap(items, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}
