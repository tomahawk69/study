package org.study.core.concurrency;

//@Target({ElementType.TYPE_USE})
public class RunnableDemo implements Runnable {

    private final String title;

    public RunnableDemo(String title) {
        this.title = title;
    }


    @Override
    public void run() {
        System.out.println(title + " run...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(title + " is interrupted");
            e.printStackTrace();
        }
        System.out.println(title + " done...");
    }

    public static void main(String[] args) {
        Runnable runnable1 = new RunnableDemo("runnable1");
        runnable1.run();

        Thread thread = new Thread(new RunnableDemo("runnable2"));
        thread.start();
        thread.interrupt();

        Runnable runnable3 = new RunnableDemo(null);
        runnable3.run();

    }
}
