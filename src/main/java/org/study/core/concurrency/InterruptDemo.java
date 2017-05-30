package org.study.core.concurrency;

/**
 * Created by iovchynnikov on 9/24/2015.
 */
public class InterruptDemo implements Runnable {
    private boolean isInterrupted = false;

    @Override
    public synchronized void run() {
        try {
            wait();
        } catch (InterruptedException e) {
            isInterrupted = true;
            System.out.println("Interrupted");
        }
        System.out.println(Thread.interrupted());
    }

    public boolean isInterrupted() {
        return isInterrupted;
    }

    @Override
    public String toString() {
        return "InterruptDemo{" +
                "isInterrupted=" + Thread.interrupted() +
                '}';
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new InterruptDemo());
        thread1.start();
        System.out.println(thread1);
        thread1.interrupt();
        synchronized(thread1) {
            thread1.wait();
        };
        System.out.println(thread1.isInterrupted());
    }
}
