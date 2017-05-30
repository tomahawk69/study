package org.study.core.references;

import java.lang.ref.*;

/**
 * ReferenceQueue may be useful when we need know when not-strong referenced objects are collected by GC
 * E.g. we may subclass Reference, internally store wrapped object and make some things before deleting the object
 * Doesn't work with interns (object4)
 *
 * Run configuration:
 * -Xmx32M -XX:SoftRefLRUPolicyMSPerMB=100
 */
public class RefQueue {
    private static String object1;
    private static String object2;
    private static String object3;
    private static String object4;

    private static class RefQueueWeak<T> extends WeakReference<Object> {

        public RefQueueWeak(T referent, ReferenceQueue<? super Object > q) {
            super(referent, q);
        }

        public void cleanUp() {
            System.out.println("I'm going to do some cleanup things");
        }
    }


    private static String getFourthObject() {
        return new String("testIntern").intern();
    }

    public static void main(String[] args) {
        object1 = new String("testSoft");
        object2 = new String("testWeak");
        object3 = new String("testPhantom");
        object4 = getFourthObject();

        ReferenceQueue<String> queue = new ReferenceQueue<>();

        Reference<String> refSoft = new SoftReference<>(object1, queue);
        Reference<String> refWeak = new RefQueueWeak(object2, queue);
//        Reference<String> refWeak = new WeakReference<>(object2, queue);
        Reference<String> refPhantom = new PhantomReference<>(object3, queue);
        Reference<String> refWeakIntern = new WeakReference(object4, queue);

        Thread thread = createThread();
        thread.start();

        Integer adder = new Integer(3);

        try {
            while (adder > 0) {
                Reference<?> ref = queue.remove();
                if (ref == refWeak) {
                    System.out.println("Weak reference removed");
                    if (ref instanceof RefQueueWeak) {
                        ((RefQueueWeak) ref).cleanUp();
                    }
                    adder--;
                } else if (ref == refPhantom) {
                    System.out.println("Phantom reference removed");
                    adder--;
                } else if (ref == refSoft) {
                    System.out.println("Soft reference removed");
                    adder--;
                }
            }
            thread.interrupt();
            System.out.println("refWeakIntern is " + refWeakIntern.get());
        } catch (Exception e) {
            System.out.println("remove interrupted");
        }
    }


    private static Thread createThread() {
        return new Thread(() -> {
            while (true) {
                try {
                    System.out.println("sleep i sec");
                    Thread.sleep(1000);
                    object1 = null;
                    object2 = null;
                    object3 = null;
                    System.out.println("invoking gc");
                    System.gc();
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                    break;
                }
            }
        });

    }
}
