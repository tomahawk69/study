package org.study.core.concurrency.thread;

/**
 * Created by iovchynnikov on 7/29/2015.
 */
public class Synchronizations {

    public void run() {
        Counter counter = new Counter();
        Thread thread1 = new Thread(new RunnableObject("test1", counter, new Boolean[]{true, false, true, true}));
        Thread thread2 = new Thread(new RunnableObject("test2", counter, new Boolean[]{false, true, true, false}));
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter);
    }

    private class Counter {
        private int counter = 0;

        public int increment() {
            counter++;
            return counter;
        }
        public int decrement() {
            counter--;
            return counter;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Counter{");
            sb.append("counter=").append(counter);
            sb.append('}');
            return sb.toString();
        }
    }

    private class RunnableObject implements Runnable {
        private final String title;
        private final Counter counter;
        private final Boolean[] operations;

        private RunnableObject(String title, Counter counter, Boolean[] operations) {
            this.title = title;
            this.counter = counter;
            this.operations = operations;
        }

        @Override
        public void run() {
            System.out.println(this);
            for (Boolean operation : operations) {
                System.out.println(this + " " + operation);
                if (operation) {
                    counter.increment();
                } else {
                    counter.decrement();
                }
            }
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("RunnableObject{");
            sb.append("title='").append(title).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        new Synchronizations().run();
    }

}
