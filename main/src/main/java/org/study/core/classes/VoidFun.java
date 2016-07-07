package org.study.core.classes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.*;

public class VoidFun {

    void testVoidFuture() {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future future = service.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                System.out.println("Thread started");
                Thread.sleep(1000);
                System.out.println("Thread stopped");
                return null;
            }
        });
        service.shutdown();
        try {
            System.out.println("Future is " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void testVoidClass() {
        try {
            Class c1 = VoidFun.class.getMethod("testVoidClass", null).getReturnType();
            System.out.println("class is " + c1);
            System.out.println("class is type of Void = " + (c1 == Void.TYPE));
            System.out.println("class is Void = " + (c1 == Void.class));
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        }
    }


    public void createVoidClass() {
        try {
            Constructor<Void> c = Void.class.getDeclaredConstructor();
            c.setAccessible(true);
            Void obj = c.newInstance();
            System.out.println("Void object is " + obj);
            System.out.println("class is = " + obj.getClass());
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        VoidFun obj = new VoidFun();
        obj.testVoidFuture();
        obj.testVoidClass();
        obj.createVoidClass();
    }
}
