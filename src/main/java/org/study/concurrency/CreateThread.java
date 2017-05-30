package org.study.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CreateThread {
    public static void main(String[] args) {
        create1();
        create2();
        create3();
        create4();
        create5();
    }

    private static void create1() {
        new Thread(() -> {
            System.out.println("First run");
        }).start();
    }

    private static void create2() {
        new Thread(() -> System.out.println("Second run")).start();
    }

    private static void create3() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> System.out.println("Third run"));
        service.shutdown();
        while (!service.isTerminated()) {
            try {
                service.awaitTermination(100, TimeUnit.MICROSECONDS);
            } catch (InterruptedException e) {
                System.out.println("Service is interrupted");
                System.out.println(e);
            }
        }
    }

    private static void create4() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Boolean> future = service.submit(() -> { System.out.println("Fourth run"); return true; });
        service.shutdown();
        while (!future.isDone()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Thread is interrupted");
                System.out.println(e);
            }
        }
    }

    private static void create5() {
        IntStream.of(1, 2, 3 ,4 , 5).parallel().forEach(i -> new Thread(() -> System.out.println("Five thread #" + i)).start());
    }
}
