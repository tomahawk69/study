package org.study.core.exceptions;

public class TryOpen implements AutoCloseable {

    @Override
    public void close() throws Exception {
        System.out.println("close");
    }

    private void goWithException() throws Exception {
        System.out.println("go with exception is executed");;
        throw new Exception();
    }

    private void goWithoutException() throws Exception {
        System.out.println("go is executed");;
    }

    public static void main(String[] args) {
        try {
            try (TryOpen obj = new TryOpen()) {
                obj.goWithoutException();
            }
        } catch (Exception e) {
            System.out.println("In the catch");
        }
        finally {
            System.out.println("Finally in main method");
        }

        try {
            try (TryOpen obj = new TryOpen()) {
                obj.goWithException();
            }
        } catch (Exception e) {
            System.out.println("In the catch");
        }
        finally {
            System.out.println("Finally in main method");
        }

    }
}
