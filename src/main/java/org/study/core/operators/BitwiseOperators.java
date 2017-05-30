package org.study.core.operators;

public class BitwiseOperators {
    private static void makeOperations(final int seed) {
        System.out.println("== Start with " + seed + " (" + Integer.toBinaryString(seed) + ")");
        System.out.println(">> 1 is " + (seed >> 1) + " (" + Integer.toBinaryString(seed >> 1) + ")");
        System.out.println("<< 1 is " + (seed << 1) + " (" + Integer.toBinaryString(seed << 1) + ")");
    }

    public static void main(String[] args) {
        makeOperations(4);
        makeOperations(100);
        makeOperations(11);
    }
}
