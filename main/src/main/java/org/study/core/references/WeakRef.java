package org.study.core.references;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * WeakReference works similar to SoftReference however objects are eligible to be collected anyway if they have only weak references
 */

public class WeakRef {
    private static Object obj;

    private static void demoWeakRef() {
        System.out.println("-- demoWeakRef");
        obj = new Object();
        Reference<Object> ref = new WeakReference(obj);
        obj = null;
        System.out.println("Ref is " + ref.get());
        try {
            Thread.sleep(1000);
            System.out.println("GC invocation");
            System.gc();
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
        System.out.println("Ref is " + ref.get());
    }

    public static void main(String[] args) {
        demoWeakRef();
        demoWeakRefWithQueue();
    }

    private static void demoWeakRefWithQueue() {
        System.out.println("-- demoWeakRefWithQueue");
        obj = new Object();
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        Reference<Object> ref = new WeakReference(obj, queue);
        obj = null;
        System.out.println("Ref is " + ref.get());
        System.out.println("Queue pull is " + queue.poll());
        try {
            Thread.sleep(1000);
            System.out.println("GC invocation");
            System.gc();
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
        System.out.println("Ref is " + ref.get());
        System.out.println("Queue pull is required var? " + (queue.poll() == ref));
    }




}
