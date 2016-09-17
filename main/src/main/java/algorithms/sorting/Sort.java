package algorithms.sorting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

public abstract class Sort<T extends Comparable<? super T>> {
    private static final Logger LOGGER = LogManager.getLogger(Sort.class);

    public abstract void sort(Comparable<T>[] items);

    void swap(T[] array, int i, int j) {
        T k = array[i];
        array[i] = array[j];
        array[j] = k;
    }

    public static void main(String[] args) {
        testAllSortingImplementations();
    }

    private static void testAllSortingImplementations() {
        LOGGER.info("-- Test all sorting implementation");
        int size = 10000;
        LOGGER.info("-- sample array size is " + size);
        String[] testArray = createTestArray(size);
        for (int i = 0; i < 10; i++) {
            testImpementations(Arrays.copyOf(testArray, testArray.length), i == 9);
        }
    }

    private static void testImpementations(String[] testArray, boolean isReal) {
        if (!isReal) {
            LOGGER.info("Warming up...");
            new InsertionSort().sort(Arrays.copyOf(testArray, testArray.length));
            new SelectionSort().sort(Arrays.copyOf(testArray, testArray.length));
            new ShellSort().sort(Arrays.copyOf(testArray, testArray.length));
        } else {
            LOGGER.info("Testing...");

            LOGGER.info("-- selection sort");
            long start = System.currentTimeMillis();
            new SelectionSort().sort(Arrays.copyOf(testArray, testArray.length));
            long end = System.currentTimeMillis();
            LOGGER.info("Sorted in " + (end - start) / 1000.0 + " secs");

            LOGGER.info("-- insertion sort");
            start = System.currentTimeMillis();
            new InsertionSort().sort(Arrays.copyOf(testArray, testArray.length));
            end = System.currentTimeMillis();
            LOGGER.info("Sorted in " + (end - start) / 1000.0 + " secs");


            LOGGER.info("-- shell sort");
            start = System.currentTimeMillis();
            new ShellSort().sort(Arrays.copyOf(testArray, testArray.length));
            end = System.currentTimeMillis();
            LOGGER.info("Sorted in " + (end - start) / 1000.0 + " secs");
        }
    }

    private static String[] createTestArray(int size) {
        return Stream.generate(UUID::randomUUID).limit(size).map(u -> u.toString()).toArray(String[]::new);
    }
}
