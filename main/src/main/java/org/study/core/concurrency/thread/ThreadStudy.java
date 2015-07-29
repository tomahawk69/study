package org.study.core.concurrency.thread;

public class ThreadStudy extends Thread {

    public void run() {
        Thread thread = new ThreadStudy.SimpleLock();
        thread.start();

        Thread runnable = new Thread(new SimpleRunnable());
        runnable.start();

        Thread interrupedRunnable = new Thread(new InterruptedRunnable());
        interrupedRunnable.start();

        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runnable.interrupt();
        interrupedRunnable.interrupt();

        Thread joinRunnable = new Thread(new JoinRunnable("1"));
        System.out.println("joinRunnable start");
        joinRunnable.start();
        try {
            joinRunnable.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("joinRunnable is " + joinRunnable.isAlive());
        try {
            joinRunnable.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("joinRunnable is " + joinRunnable.isAlive());


    }

    public class SimpleLock extends Thread {
        @Override
        public void run() {
            System.out.println("SimpleLock run");
            super.run();
        }

        @Override
        public synchronized void start() {
            System.out.println("SimpleLock start");
            super.start();
        }

    }

    private class SimpleRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("SimpleRunnable run");
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("SimpleRunnable interrupted!!");
            }
            System.out.println("SimpleRunnable done");
        }
    }

    private class JoinRunnable implements Runnable {
        private final String title;
        private boolean isActive;

        private JoinRunnable(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public boolean isActive() {
            return isActive;
        }

        @Override
        public void run() {
            isActive = true;
            System.out.println("JoinRunnable " + title + " run");
            try {
                sleep(10000);
                isActive = false;
            } catch (InterruptedException e) {
                System.out.println("JoinRunnable " + title + " interrupted!!");
            }
            System.out.println("JoinRunnable " + title + " done");
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("JoinRunnable{");
            sb.append("title='").append(title).append('\'');
            sb.append(", isActive=").append(isActive);
            sb.append('}');
            return sb.toString();
        }
    }


    private class InterruptedRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("InterruptedRunnable run");
            while (true) {
                if (interrupted()) {
                    System.out.println("InterruptedRunnable interrupted!!");
                    break;
                }
            }
            System.out.println("InterruptedRunnable done");
        }
    }

    public static void main(String[] args) {
        ThreadStudy threadStudy = new ThreadStudy();
        threadStudy.run();
    }
}
