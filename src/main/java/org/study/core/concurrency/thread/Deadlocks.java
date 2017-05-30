package org.study.core.concurrency.thread;

import java.util.concurrent.locks.Lock;

/**
 * Created by iovchynnikov on 7/29/2015.
 */
public class Deadlocks {

    public void run() {
        Thread thread1 = new Deadlocks.DeadlockThread();
    }


    private class DeadlockThread extends Thread {
        @Override
        public void run() {


        }
    }
}
