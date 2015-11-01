package org.study.core.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * Created by iovchynnikov on 9/25/2015.
 */
public class VisibilityDemo implements Runnable {
    private Value value;

    public VisibilityDemo(Value value) {
        this.value = value;
    }

    public static void main(String[] args) {
        Value value = new Value();
        CountDownLatch latch = new CountDownLatch(1);
        Thread thread1 = new Thread(new VisibilityDemo(value));
        Thread thread2 = new Thread(new Printer(value));
        thread2.start();
        thread1.start();
        ;
        latch.countDown();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        value.setValue(10);
        System.out.println("changed value " + value);
    }
}

class Printer implements Runnable {
    private Value value;

    Printer(Value value) {
        this.value = value;
    }

    @Override
    public void run() {
        System.out.println(value);
        while (true) {
            synchronized (value) {
                System.out.println(value);
            }
            if (Thread.interrupted()) {
                break;
            }
        }
    }
}

class Value {
    int value = 0;

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Value{" +
                "value=" + value +
                '}';
    }
}