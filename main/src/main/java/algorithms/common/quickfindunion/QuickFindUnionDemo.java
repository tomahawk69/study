package algorithms.common.quickfindunion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class QuickFindUnionDemo {
    private static final Logger logger = LogManager.getLogger(QuickFindUnionDemo.class);

    public static void testVariousImplementations() {
        checkImplementationSize(10000);
        checkImplementationSize(20000);
    }

    private static void checkImplementationSize(int size) {
        logger.info("== " + size);
        long start = 0;
        long end = 0;
        logger.info("-- QuickFind");
        List<String> logs = checkImplementationQuickFind(size);
        logs.stream().forEach(s -> logger.info(s));

        logger.info("-- QuickUnion");
        logs = checkImplementationQuickUnion(size);
        logs.stream().forEach(s -> logger.info(s));

        logger.info("-- WeightedQuickUnion");
        logs = checkImplementationWeightedQuickUnion(size);
        logs.stream().forEach(s -> logger.info(s));

        logger.info("-- WeightedQuickUnionWithPathCompression");
        logs = checkImplementationWeightedQuickUnionWithPathCompression(size);
        logs.stream().forEach(s -> logger.info(s));

    }

    private static List<String> checkImplementationWeightedQuickUnionWithPathCompression(int size) {
        long start = 0;
        long end = 0;
        int depth = 0;
        List<String> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            start = System.currentTimeMillis();
            WeightedQuickUnionWithPathCompression quickFindUnion = new WeightedQuickUnionWithPathCompression(size);
            end = System.currentTimeMillis();
            results = checkImplementation(quickFindUnion, size);
            depth = quickFindUnion.maxDepth();
        }
        results.add(0, "Created in " + (end - start) / 1000.0 + " secs");
        results.add(1, "Depth is " + depth);
        return results;
    }

    private static List<String> checkImplementationWeightedQuickUnion(int size) {
        long start = 0;
        long end = 0;
        int depth = 0;
        List<String> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            start = System.currentTimeMillis();
            WeightedQuickUnion quickFindUnion = new WeightedQuickUnion(size);
            end = System.currentTimeMillis();
            results = checkImplementation(quickFindUnion, size);
            depth = quickFindUnion.maxDepth();
        }
        results.add(0, "Created in " + (end - start) / 1000.0 + " secs");
        results.add(1, "Depth is " + depth);
        return results;
    }

    private static List<String> checkImplementationQuickUnion(int size) {
        long start = 0;
        long end = 0;
        int depth = 0;
        List<String> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            start = System.currentTimeMillis();
            QuickUnion quickFindUnion = new QuickUnion(size);
            end = System.currentTimeMillis();
            results = checkImplementation(quickFindUnion, size);
            depth = quickFindUnion.maxDepth();
        }
        results.add(0, "Created in " + (end - start) / 1000.0 + " secs");
        results.add(1, "Depth is " + depth);
        return results;
    }

    private static List<String> checkImplementationQuickFind(int size) {
        long start = 0;
        long end = 0;
        int depth = 0;
        List<String> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            start = System.currentTimeMillis();
            QuickFindUnion quickFindUnion = new QuickFind(size);
            end = System.currentTimeMillis();
            results = checkImplementation(quickFindUnion, size);
        }
        results.add(0, "Created in " + (end - start) / 1000.0 + " secs");
        results.add(1, "Depth is " + depth);
        return results;
    }

    private static List<String> checkImplementation(QuickFindUnion quickFindUnion, int size) {
        List<String> result = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                quickFindUnion.find(i, j);
            }
        }
        long end = System.currentTimeMillis();
        result.add("Find " + (end - start) / 1000.0 + " secs");
        start = System.currentTimeMillis();
        for (int i = 1; i < size; i++) {
            quickFindUnion.union(i, 0);
        }
        for (int i = 1; i < size - 1; i++) {
            quickFindUnion.union(i, size - i);
        }
        end = System.currentTimeMillis();
        result.add("Union " + (end - start) / 1000.0 + " secs");
        return result;
    }

    public static void testManyFindsOfQuickUnion() {
        for (int k = 0; k < 10; k++) {
            int size = 10000;
            int repetions = 1000;
            long start = System.currentTimeMillis();
            if (k == 9)
                logger.info("-- quickFindUnion");
            QuickUnion quickFindUnion = new QuickUnion(size);
            makeUnions(quickFindUnion, size);
            if (k == 9) {
                long end = System.currentTimeMillis();
                logger.info("Union is " + (end - start) / 1000.0 + " secs");
                start = System.currentTimeMillis();
            }
            for (int i = 1; i < size; i++) {
                for (int j = 1; j < repetions; j++) {
                    quickFindUnion.find(0, i);
                }
            }
            long end = System.currentTimeMillis();
            if (k == 9) {
                logger.info("Find of " + (end - start) / 1000.0 + " secs");
                logger.info("Depth is " + quickFindUnion.maxDepth());
            }

            if (k == 9)
                logger.info("-- WeightedQuickUnion");
            quickFindUnion = new WeightedQuickUnion(size);
            makeUnions(quickFindUnion, size);
            if (k == 9) {
                end = System.currentTimeMillis();
                logger.info("Union is " + (end - start) / 1000.0 + " secs");
                start = System.currentTimeMillis();
            }
            for (int i = 1; i < size; i++) {
                for (int j = 1; j < repetions; j++) {
                    quickFindUnion.find(0, i);
                }
            }
            end = System.currentTimeMillis();
            if (k == 9) {
                logger.info("Find of " + (end - start) / 1000.0 + " secs");
                logger.info("Depth is " + quickFindUnion.maxDepth());
            }

            if (k == 9)
                logger.info("-- WeightedQuickUnionWithPathCompression");
            quickFindUnion = new WeightedQuickUnionWithPathCompression(size);
            makeUnions(quickFindUnion, size);
            if (k == 9) {
                end = System.currentTimeMillis();
                logger.info("Union is " + (end - start) / 1000.0 + " secs");
                start = System.currentTimeMillis();
            }
            for (int i = 1; i < size; i++) {
                for (int j = 1; j < repetions; j++) {
                    quickFindUnion.find(0, i);
                }
            }
            end = System.currentTimeMillis();
            if (k == 9) {
                logger.info("Find is " + (end - start) / 1000.0 + " secs");
                logger.info("Depth is " + quickFindUnion.maxDepth());
            }
        }
    }

    private static void makeUnions(QuickFindUnion quickFindUnion, int size) {
        int i = 0;
        int k = 1;
        // first part
        do {
            for (int j = k; j < size / 2; j += k * 2) {
                quickFindUnion.union(j - 1, j);
            }
            i++;
            if (Math.round(Math.pow(2, i)) > size / 2) {
                i = size;
            } else {
                k = Math.toIntExact(Math.round(Math.pow(2, i)));
            }
        }
        while (Math.pow(2, i) < size / 2);
        for (int j = size / 2 + 1; j < size; j++) {
            quickFindUnion.union(j, j - 1);
        }
    }

    public static void main(String[] args) {
        testManyFindsOfQuickUnion();
//        testVariousImplementations();
    }
}
