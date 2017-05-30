package org.study.concurrency;

public class StopThread implements Runnable {
    private volatile boolean running = true;


    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("stopped in try-catch");
                running = false;
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void startStop1() {
        Thread thread = new Thread(new StopThread());
        thread.start();
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println("1----");
        startStop1();
        System.out.println("2----");
        startStop2();
    }

    private static void startStop2() {
        StopThread ttt = new StopThread();
        Thread thread = new Thread(ttt);
        thread.start();
        ttt.terminate();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void terminate() {
        System.out.println("Stopped in terminate");
        running = false;
    }
}
