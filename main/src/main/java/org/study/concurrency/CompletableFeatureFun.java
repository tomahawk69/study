package org.study.concurrency;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Sample to work with CompletableFuture
 * Given the random sample of 100 elements roughly 1/10 of elements produced exceptions
 * The task is proceed all the items and determine failed elements
 */
public class CompletableFeatureFun {
    private ThreadLocalRandom random = ThreadLocalRandom.current();

    private Holder execute(Holder holder) {
        int result = random.nextInt(-1, 10);
        if (result < 0) {
            throw new RuntimeException("ughhhh");
        }
        return holder.setValue(result);
    }

    private Holder execute1(Holder holder) {
        try {
            return execute(holder);
        } catch (Exception e) {
            System.out.println("Exception...");
            return holder.setError(true);
        }
    }

    private Holder execute2(Holder holder) {
        try {
            Thread.sleep(random.nextInt(1, 10) * 1000);
            System.out.println(holder.id + " is ready");
        } catch (Exception e) {
        }
        return holder;
    }

    private void run() {
        List<CompletableFuture<Holder>> futures =
                getIntStream()
                        .map(i -> new Holder(i))
                        .map(h -> CompletableFuture.supplyAsync(() -> execute(h)))
                        .collect(Collectors.toList());
        List<Holder> randoms =
                futures.stream()
                        .map(f -> f.join())
                        .collect(Collectors.toList());
    }

    /**
     * This method used try-catch to handle exceptions and join (or get)
     * Lost items cannot be recovered
     */
    private void runWithCatch() {
        List<CompletableFuture<Holder>> futures =
                getIntStream()
                        .map(i -> new Holder(i))
                        .map(h -> CompletableFuture.supplyAsync(() -> execute(h)))
                        .collect(Collectors.toList());
        List<Holder> randoms =
                futures.stream()
                        .map(f -> {
                                    try {
                                        return f.join();
                                    } catch (Exception e) {
                                        System.out.println("Catch exception when join");
                                        return null;
                                    }
                                }
                        )
                        .collect(Collectors.toList());
    }

    /**
     * This method used exceptionally to handle exceptions
     * Lost items cannot be recovered
     */
    private List<Holder> runWithComplete() {
        List<CompletableFuture<Holder>> futures =
                getIntStream()
                        .map(i -> new Holder(i))
                        .map(h -> CompletableFuture.supplyAsync(() -> execute(h)))
                        .collect(Collectors.toList());
        List<Holder> randoms =
                futures.stream()
                        .map (f -> f.exceptionally(throwable -> {
                            System.out.println(throwable);
                            return null;
                        }))
                        .map(f -> f.join())
                        .filter(i -> i != null)
                        .collect(Collectors.toList());
        return randoms;
    }

    /**
     * This method used internal handling exceptions
     * All items recovered
     */
    private List<Holder> runWithInternalExceptionsHandling() {
        List<CompletableFuture<Holder>> futures =
                getIntStream()
                        .map(i -> new Holder(i))
                        .map(h -> CompletableFuture.supplyAsync(() -> execute1(h)))
                        .collect(Collectors.toList());
        List<Holder> randoms =
                futures.stream()
                        .map(f -> f.join())
                        .collect(Collectors.toList());
        return randoms;
    }

    private List<Holder> runWithLongRunningThreads() {
        ExecutorService service = Executors.newFixedThreadPool(50);
        List<CompletableFuture<Holder>> futures =
                getIntStream()
                        .parallel()
                        .map(i -> new Holder(i))
                        .map(h -> CompletableFuture.supplyAsync(() -> execute2(h), service))
                        .collect(Collectors.toList());
        service.shutdown();
        System.out.println("convert to allOf future");
        CompletableFuture<Void> allDoneFuture =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        System.out.println("join allOf future and convert it to list of holders");
        List<Holder> randoms = allDoneFuture.thenApply(v ->
                        futures.stream().
                                map(future -> future.join()).
                                collect(Collectors.toList())).join();
        System.out.println("finally done");
        return randoms;
    }


    private Stream<Integer> getIntStream() {
        return IntStream.range(0, 100)
                .boxed();
    }

    public static void main(String[] args) {
        CompletableFeatureFun obj = new CompletableFeatureFun();
        List<Holder> result;
        try {
            System.out.println("=== run");
            obj.run();
            System.out.println("!!!!! Cannot reach this point");
        } catch (Exception e) {
            System.out.println("Catch exception " + e);
        }
        System.out.println("=== runWithCatch");
        obj.runWithCatch();
        System.out.println("Should reach this point");
        System.out.println("=== runWithComplete");
        result = obj.runWithComplete();
        System.out.println("should complete");
        System.out.println("count is " + result.size());
        System.out.println("=== runWithInternalExceptionsHandling");
        result = obj.runWithInternalExceptionsHandling();
        System.out.println("should complete");
        System.out.println("count is " + result.size());
        System.out.println("erroneous are " + result.stream().filter(h -> h.isError).count());
        System.out.println("=== runWithLongRunningThreads");
        result = obj.runWithLongRunningThreads();
        System.out.println("should complete");
        System.out.println("count " + result.size());
    }

    class Holder {
        private final int id;
        private int value;
        private boolean isError;

        public Holder(int id) {
            this.id = id;
        }

        public Holder setValue(int value) {
            this.value = value;
            return this;
        }

        public Holder setError(boolean error) {
            isError = error;
            return this;
        }
    }
}
