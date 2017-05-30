package org.study.core.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * Created by iovchynnikov on 9/25/2015.
 */
public class InterleavingDemo {
    private int value;

    public synchronized void increment() {
        value++;
//        int value = this.value;
//        this.value = value +1;
    }

    public synchronized void decrement() {
        value--;
    }


    @Override
    public String toString() {
        return "InterleavingDemo{" +
                "value=" + value +
                '}';
    }

    public static void main(String[] args) {
        InterleavingDemo value = new InterleavingDemo();
        CountDownLatch latch = new CountDownLatch(1);

        Thread thread1 = new Thread(new PlusMinus(value, true, latch));
        Thread thread2 = new Thread(new PlusMinus(value, false, latch));
        System.out.println(value);
        thread1.start();
        thread2.start();
        latch.countDown();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(value);


    }

}

class PlusMinus implements Runnable {
    private final InterleavingDemo interleaving;
    private final boolean isIncrement;
    private final CountDownLatch latch;

    PlusMinus(InterleavingDemo interleaving, boolean isIncrement, CountDownLatch latch) {
        this.interleaving = interleaving;
        this.isIncrement = isIncrement;
        this.latch = latch;
    }

    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }
        for (int i = 0; i < 1000; i++) {
            if (isIncrement) {
                increment();
            } else {
                decrement();
            }
        }
    }

    public void increment() {
        interleaving.increment();
    }
    public void decrement() {
        interleaving.decrement();
    }
}