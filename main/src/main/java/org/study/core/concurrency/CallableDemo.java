package org.study.core.concurrency;

import java.util.concurrent.Callable;

/**
 * Created by iovchynnikov on 9/21/2015.
 */
public class CallableDemo implements Callable {


    @Override
    public Object call() throws Exception {
        System.out.println("Callable execute...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Callable done...");
        return null;
    }

    public static void main(String[] args) throws Exception {
        CallableDemo callableDemo1 = new CallableDemo();
        callableDemo1.call();


    }
}
