package org.study.core.concurrency;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CountDownLatch;

/**
 * Created by iovchynnikov on 9/24/2015.
 */
public class CountDownLatchDemo implements Runnable {
    private final CountDownLatch countDownLatch;
    private final CountDownLatch endLatch;
    private final String title;
    private static final Logger logger = LogManager.getLogger(CountDownLatchDemo.class);

    public CountDownLatchDemo(CountDownLatch countDownLatch, CountDownLatch endLatch, String title) {
        this.countDownLatch = countDownLatch;
        this.endLatch = endLatch;
        this.title = title;
    }

    @Override
    public synchronized void run() {
        logger.info(this + " calling");
        try {
            countDownLatch.await();
            logger.info(this + " starting");
            wait(2000);
            endLatch.countDown();
        } catch (InterruptedException e) {
            logger.info(this + " interrupted");
        }
        logger.info(this + " ended");
    }

    @Override
    public String toString() {
        return "CountDownLatchDemo{" +
                ", title='" + title + '\'' +
                '}';
    }

    public static void main(String[] args) throws InterruptedException {
        int numberOfCounts = 10;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(numberOfCounts);
        for (int i = 0; i < numberOfCounts * 2; i++) {
            new Thread(new CountDownLatchDemo(startLatch, endLatch, String.valueOf(i))).start();
        }
        logger.info("started");
        startLatch.countDown();
        endLatch.await();
        logger.info("finished");
    }
}
