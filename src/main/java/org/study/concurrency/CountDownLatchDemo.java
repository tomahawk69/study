package org.study.concurrency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

    public static void main(String[] args) {
        int count = 10;
        Random random = new Random();
        CountDownLatch latch = new CountDownLatch(count);
        ExecutorService executor = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            executor.submit(() -> {
                System.out.println("In thread");
                int value = random.nextInt(10);
                if (value > 5) {
                    throw new RuntimeException("Just an exception");
                }
                latch.countDown();
                System.out.println("Down");
            });
        }
        executor.shutdown();
        System.out.println("Starting waiting for...");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finishing waiting");
    }
}
