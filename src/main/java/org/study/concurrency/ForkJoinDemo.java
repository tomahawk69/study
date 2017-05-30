package org.study.concurrency;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

import static java.lang.Math.min;

public class ForkJoinDemo extends RecursiveTask<Map<Integer, Map<Integer, Integer>>> {
    private static final int MAX_COUNT = 2000;
    private final Map<Integer, Map<Integer, Integer>> matrixA;
    private final Map<Integer, Map<Integer, Integer>> matrixB;
    private final int startX;
    private final int endX;
    private final int startY;
    private final int endY;
    private static final int THRESHOLD_X = 250;
    private static final int THRESHOLD_Y = 250;

    ForkJoinDemo(Map<Integer, Map<Integer, Integer>> matrixA, Map<Integer, Map<Integer, Integer>> matrixB, int startX, int endX, int startY, int endY) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
    }

    @Override
    protected Map<Integer, Map<Integer, Integer>> compute() {
//        System.out.println("starting thread " + Thread.currentThread().getName());
        Map<Integer, Map<Integer, Integer>> result = new ConcurrentHashMap<>();

        if (endX - startX > THRESHOLD_X) {
            List<RecursiveTask<Map<Integer, Map<Integer, Integer>>>> tasks = new ArrayList<>();
            for (int i = startX; i <= endX; i += THRESHOLD_X) {
                tasks.add(new ForkJoinDemo(matrixA, matrixB, i, i + THRESHOLD_X, 0, MAX_COUNT - 1));
            }
            invokeAll(tasks);
            for (RecursiveTask<Map<Integer, Map<Integer, Integer>>> task : tasks) {
                try {
                    result.putAll(task.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } else if (endY - startY > THRESHOLD_Y) {
            IntStream
                    .range(startX, endX)
                    .forEach(i -> {
                        Map<Integer, Integer> rowA = matrixA.get(i);
                        if (rowA != null) {
                            List<RecursiveTask<Map<Integer, Map<Integer, Integer>>>> tasks = new ArrayList<>();
                            IntStream
                                    .iterate(startY, index -> index + THRESHOLD_Y)
                                    .limit((endY - startY) / THRESHOLD_Y)
                                    .forEach(y -> tasks.add(new ForkJoinDemo(matrixA, matrixB, i, i + 1, y, min(y + THRESHOLD_Y, endY))));
                            invokeAll(tasks);
                            Map<Integer, Integer> row = new HashMap<>();
                            tasks.stream()
                                    .forEach(o -> {
                                        try {
                                            Map<Integer, Integer> rowI = o.get().get(i);
                                            if (rowI != null) {
                                                row.putAll(rowI);
                                            }
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        } catch (ExecutionException e) {
                                            e.printStackTrace();
                                        }
                                    });
                            if (row.size() > 0) {
                                result.put(i, row);
                            }
                        }
                    });
        } else {
            for (int i = startX; i < endX; i++) {
                Map<Integer, Integer> rowA = matrixA.get(i);
                if (rowA != null) {
                    Map<Integer, Integer> row = computeDirect(rowA, startY, endY);
                    if (row.size() > 0) {
                        result.put(i, row);
                    }
                }
            }
        }

//        System.out.println("ending thread");
        return result;
    }

    private Map<Integer, Integer> computeDirect(Map<Integer, Integer> rowA, int startY, int endY) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int y = startY; y <= endY; y++) {
            int sum = 0;
            Map<Integer, Integer> rowB = matrixB.get(y);
            if (rowB != null) {
                for (Map.Entry<Integer, Integer> entry : rowA.entrySet()) {
                    if (rowB.containsKey(entry.getKey())) {
                        sum += entry.getValue() * rowB.get(entry.getKey());
                    }
                }
            }
            if (sum > 0) {
                result.put(y, sum);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Map<Integer, Map<Integer, Integer>> matrixA = TaskUtils.generateMatrix(MAX_COUNT, MAX_COUNT, 111);
        Map<Integer, Map<Integer, Integer>> matrixB = TaskUtils.generateMatrix(MAX_COUNT, MAX_COUNT, 121);
        // assume matrixB is already transposed
//        System.out.println("matrixA is " + matrixA);
//        System.out.println("transposed matrixB is " + matrixB);
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(LocalDateTime.now());
        Map<Integer, Map<Integer, Integer>> result = pool.invoke(new ForkJoinDemo(matrixA, matrixB, 0, MAX_COUNT - 1, 0, MAX_COUNT - 1));
        System.out.println(LocalDateTime.now());
        // 25 for 2000:2000
        // 7.5 for 250:250
        // 7.5 for 100:100
        // 9 for 10: 100
//        System.out.println(result);
    }

    static class TaskUtils {
        public static Map<Integer, Map<Integer, Integer>> generateMatrix(int maxX, int maxY, int seed) {
            Map<Integer, Map<Integer, Integer>> result = new HashMap<>();
            Random random = new Random(seed);
            for (int x = 0; x < maxX; x++) {
                Map<Integer, Integer> row = new HashMap<>();
                for (int y = 0; y < maxY; y++) {
                    if (random.nextInt(10) > 8) {
                        row.put(y, random.nextInt(100));
                    }
                }
                if (row.size() > 0) {
                    result.put(x, row);
                }
            }
            return result;
        }
    }
}
