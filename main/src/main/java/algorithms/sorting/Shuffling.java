package algorithms.sorting;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 1.
 * Generate a random real number for every entry
 * Sort array by this numbers
 *
 * 2. Knuth shuffle
 * for every i pick integer r between 0 and i and swap i and r
 */
public class Shuffling extends Sort {
    ThreadLocalRandom random = ThreadLocalRandom.current();

    @Override
    public void sort(Comparable[] items) {
        for (int i = 1; i < items.length; i++) {
            swap(items, i, random.nextInt(i));
        }
    }
}
