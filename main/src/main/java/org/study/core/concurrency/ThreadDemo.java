package org.study.core.concurrency;

/**
 * Created by iovchynnikov on 9/21/2015.
 */
public class ThreadDemo extends Thread {
    private final String title;

    public ThreadDemo(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "ThreadDemo {" + title + "}";
    }

    @Override
    public void run() {
        System.out.println(toString() + " run...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(toString() + " done...");
    }


    public static void main(String[] args) {
        ThreadDemo thread1 = new ThreadDemo("thread1");
        thread1.start();
    }
}
