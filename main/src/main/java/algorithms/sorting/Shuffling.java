package algorithms.sorting;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Knuth (Fisher–Yates, Durstenfeld) shuffle
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
