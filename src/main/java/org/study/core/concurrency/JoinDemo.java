package org.study.core.concurrency;

/**
 * Created by iovchynnikov on 9/25/2015.
 */
public class JoinDemo implements Runnable {
    public final Thread first;
    public final String title;

    public JoinDemo(Thread first, String title) {
        this.first = first;
        this.title = title;
    }

    @Override
    public void run() {
        System.out.println(title + " Run...");
        try {
            if (first != null) {
                first.join();
            }
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(title + " Ran...");
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new JoinDemo(null, "thread1"));
        Thread thread2 = new Thread(new JoinDemo(thread1, "thread2"));

        thread2.start();
        thread1.start();
    }
}

