package org.study.core.concurrency;

import java.util.Random;

/**
 * Created by iovchynnikov on 9/24/2015.
 */
public class GuardedBlockDemo  {

    public static void main(String[] args) {
        Drop drop = new Drop();
        Thread producer = new Thread(new Producer(drop));
        Thread consumer = new Thread(new Consumer(drop));

        consumer.start();
        producer.start();
    }
}


class Drop {
    private String title;
    private boolean empty = true;

    public synchronized String take() {
        System.out.println("taking...");
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
        empty = true;
        System.out.println("took " + title);
        notifyAll();
        return title;
    }

    public synchronized void put(String title) {
        System.out.println("putting " + title + "...");
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
        this.title = title;
        empty = false;
        System.out.println("putted " + title);
        notifyAll();
    }
}

class Producer implements Runnable {
    private final Drop drop;

    Producer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };
        Random random = new Random();

        for (int i = 0; i < importantInfo.length; i++) {
            drop.put(importantInfo[i]);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
        drop.put("DONE");
    }
}

class Consumer implements Runnable {
    private final Drop drop;

    Consumer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {

        Random random = new Random();
        while (true) {
            String title = drop.take();
            if ("DONE".equals(title)) break;
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}