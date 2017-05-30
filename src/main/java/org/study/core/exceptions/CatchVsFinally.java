package org.study.core.exceptions;

public class CatchVsFinally {

    static int go(int divider) {
        try {
            return 10/divider;
        } catch (Exception e) {
            return 0;
        }
        finally {
            return 1;
        }
    }

    public static void main(String[] args) {
        System.out.println("2 => " + go(2));
        System.out.println("0 => " + go(0));
    }
}
