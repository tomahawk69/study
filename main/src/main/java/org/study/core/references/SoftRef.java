package org.study.core.references;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.*;

/**
 * SoftReference is a kind of strong reference with one exception: when JVM run out of memory it may (or not) decide to remove
 * objects that are referenced only softly
 *
 * Object will be marked as softreferred when time from last access (l) is:
 * l = F * SoftRefLRUPolicyMSPerMB
 * where F = free space in Mb
 * SoftRefLRUPolicyMSPerMB - 1000 (1 sec) by default
 *
 * Sample configuration:
 * -Xmx256M -verbose:gc -XX:SoftRefLRUPolicyMSPerMB=10
 * Demo1: 1 sec (1000 ms) sleep => object will be marked as "SoftReferred" when not more than 100Mb free space will remain
 * Demo2: 2 sec (2000 ms) sleep => object will be marked as "SoftReferred" when not more than 200Mb free space will remain
 *
 *
 */
public class SoftRef {

    private static SoftReference createObjs(ReferenceQueue<SoftRef[]> queue) {
        SoftRef[] result = new SoftRef[100000];
        for (int i = 0; i < result.length; i++) {
            result[i] = new SoftRef();
        }
        return new SoftReference(result);
    }

    private static String[] doSomeHardWork() {
        System.out.println("Do some hard work");
        String[] result = new String[50000];
        for (int i = 0; i < result.length; i++) {
            result[i] = UUID.randomUUID().toString();
        }
        return result;
    }

    private static boolean isReleased(SoftReference<SoftRef>[] softs) {
        for (int i = 0; i < softs.length; i++) {
            if (softs[i].get() == null) return true;
        }
        return false;
    }


    private static void runDemo(int sleepTime) throws InterruptedException {
        ReferenceQueue<SoftRef[]> queue = new ReferenceQueue<>();
        SoftReference<SoftRef[]> softs = createObjs(queue);
        System.out.println("Softref is not empty: " + softs.get());
        List<String> myLocal = new ArrayList<>();
        while (softs.get() != null) {
            Collections.addAll(myLocal, doSomeHardWork());
            Thread.sleep(sleepTime);
            System.gc();
        }
        System.out.println("And now it is empty: " + (softs.get() == null));
    }

    public static void main(String[] args) throws InterruptedException {
        runDemo(1000);
        runDemo(2000);
    }


}
