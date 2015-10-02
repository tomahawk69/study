package org.study.core.concurrency;

/**
 * Created by iovchynnikov on 9/24/2015.
 */
public class WaitDemo {
    public static void main(String[] args) {
        Object key = new Object();
        Thread threadWaiter1 = new Thread(new Waiter(key));
        Thread threadWaiter2 = new Thread(new Waiter(key));
        Thread threadExecutor = new Thread(new Executor(key));

        threadWaiter1.start();
        threadWaiter2.start();
        threadExecutor.start();
    }
}


class Waiter implements Runnable {
    private final Object key;

    Waiter(Object key) {
        this.key = key;
    }

    @Override
    public void run() {
        System.out.println("Waiter wait");
        synchronized (key) {
            try {
                key.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (key) {
            key.notify();
        }
        System.out.println("Waiter run");
    }
}
class Executor implements Runnable {
    private final Object key;

    Executor(Object key) {
        this.key = key;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(4000);
            synchronized (key) {
                key.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}