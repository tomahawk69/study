package org.study.concurrency;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerPerformance {
    private int intValue;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private void incrementInt() {
        intValue++;
    }
    private synchronized void incrementIntWithSynchronize() {
        intValue++;
    }
    private void incrementAtomicInteger() {
        atomicInteger.incrementAndGet();
    }

    private void reset() {
        intValue = Integer.MIN_VALUE;
        atomicInteger.set(Integer.MIN_VALUE);
    }

    public static void main(String[] args) {
        AtomicIntegerPerformance obj = new AtomicIntegerPerformance();

//        obj.reset();
//        LocalDateTime startDate = LocalDateTime.now();
//        for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
//            obj.incrementInt();
//        }
//        LocalDateTime endDate = LocalDateTime.now();
//        System.out.println("incrementInt " + dateBetweenDates(endDate, startDate));
//
//        obj.reset();
//        startDate = LocalDateTime.now();
//        for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
//            obj.incrementIntWithSynchronize();
//        }
//        endDate = LocalDateTime.now();
//        System.out.println("incrementIntWithSynchronize " + dateBetweenDates(endDate, startDate));

        obj.reset();
        LocalDateTime startDate = LocalDateTime.now();
        for (int i = 0; i < 1000000; i++) {
            obj.incrementAtomicInteger();
        }
        LocalDateTime endDate = LocalDateTime.now();
        System.out.println("incrementAtomicInteger " + dateBetweenDates(endDate, startDate));
    }

    private static long dateBetweenDates(LocalDateTime endDate, LocalDateTime startDate) {
        return startDate.until( endDate, ChronoUnit.MILLIS);
    }

}
