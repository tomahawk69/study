package org.study.concurrency.dateutiles;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

public class AbstractDateTimeFormatterTest {

    @Test
    public void testNotThreadSafe() throws Exception {
        for (int i = 0; i < 10000; i++) {
            new Thread(new SimpleDateFormatterNotThreadSafe()).start();
        }
    }

    @Test
    public void testThreadSafe() throws Exception {
        for (int i = 0; i < 10000; i++) {
            new Thread(new SimpleDateFormatThreadSafe()).start();
        }
        System.out.println(SimpleDateFormatThreadSafe.getFormatterInstancesCount());
    }

    @Test
    public void testThreadSafeInPool() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10000; i++) {
            service.submit(new SimpleDateFormatThreadSafe());
        }
        service.shutdown();
        System.out.println(SimpleDateFormatThreadSafe.getFormatterInstancesCount());
    }

    @Test
    public void testThreadSafeJava8() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10000; i++) {
            service.submit(new DateTimeFormatterThreadSafeJava8());
        }
        service.shutdown();
    }
}