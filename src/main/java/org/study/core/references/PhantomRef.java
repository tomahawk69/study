package org.study.core.references;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.UUID;

public class PhantomRef extends PhantomReference<Object> {
    public static ReferenceQueue<Object> queue = new ReferenceQueue<>();
    private final String id = UUID.randomUUID().toString();

    public PhantomRef(Object referent, ReferenceQueue<? super Object> q) {
        super(referent, q);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalizing " + id);
    }

    public static void main(String[] args) {
        createPhantomRef(new String("first"));
        createPhantomRef(new String("second"));
        new Thread(() -> {
            try {
                System.out.println("Sleeping");
                Thread.sleep(1000);
                System.out.println("Wake up and GC");
                System.gc();
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
        }).start();
        Integer count = 2;
        Reference<?> ref;
        try {
            while (count > 0) {
                System.out.println("Removing ref...");
                if ((ref = queue.remove()) != null) {
                    System.out.println("Removed " + ref);
                    count--;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }

    }

    private static void createPhantomRef(Object obj) {
        new PhantomRef(obj, queue);
    }

    @Override
    public String toString() {
        return "PhantomRef{" +
                "id='" + id + '\'' +
                '}';
    }
}
