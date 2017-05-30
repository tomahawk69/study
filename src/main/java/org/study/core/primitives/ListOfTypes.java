package org.study.core.primitives;

import java.math.BigInteger;

public class ListOfTypes {
    private static BigInteger divider = BigInteger.valueOf(2);

    private static int getBitsCount(Object maxValue, Object minValue) {
        BigInteger bigInteger = objectToBigInteger(maxValue).add(objectToBigInteger(minValue).negate());
        int result = 0;
        while (bigInteger.compareTo(BigInteger.ZERO) > 0) {
            bigInteger = bigInteger.divide(divider);
            result++;
        }
        return result;
    }

    private static BigInteger objectToBigInteger(Object object) {
        BigInteger bigInteger;
        if (object instanceof BigInteger) {
            bigInteger = (BigInteger) object;
        } else if (object instanceof Character) {
            bigInteger = BigInteger.valueOf(objectToLong((Character) object + 0));
        } else {
            bigInteger = BigInteger.valueOf(objectToLong(object));
        }
        return bigInteger;
    }

    private static Long objectToLong(Object object) {
        return Long.parseLong(objectToString(object));
    }

    private static String objectToString(Object object) {
        return String.valueOf(object);
    }

    public static void main(String[] args) {
        System.out.print("Byte: from " + Byte.MAX_VALUE + " to " + Byte.MIN_VALUE);
        System.out.println("; bits count is " + getBitsCount(Byte.MAX_VALUE, Byte.MIN_VALUE ));
        System.out.print("Char: from " + (int) Character.MAX_VALUE + " to " + (int) Character.MIN_VALUE);
        System.out.println("; bits count is " + getBitsCount(Character.MAX_VALUE, Character.MIN_VALUE));
        System.out.print("Short: from " + Short.MAX_VALUE + " to " + Short.MIN_VALUE);
        System.out.println("; bits count is " + getBitsCount(Short.MAX_VALUE, Short.MIN_VALUE));
        System.out.print("Integer: from " + Integer.MAX_VALUE + " to " + Integer.MIN_VALUE);
        System.out.println("; bits count is " + getBitsCount(Integer.MAX_VALUE, Integer.MIN_VALUE));
        System.out.print("Long: from " + Long.MAX_VALUE + " to " + Long.MIN_VALUE);
        System.out.println("; bits count is " + Long.SIZE);
        System.out.print("Float: from " + Float.MAX_VALUE + " to " + Float.MIN_VALUE);
        System.out.println("; bits count is " + Float.SIZE);
        System.out.print("Double: from " + Double.MAX_VALUE + " to " + Double.MIN_VALUE);
        System.out.println("; bits count is " + Double.SIZE);

        // BigInteger is unbounded in theory. However it is stored in int[] array, so (again in theory) its size is restricted to the maximum len of array of int: (Integer.MAX_VALUE-1) ^ Integer.MAX_VALUE
        // BigDecimal is done on liked basis: BigInteger as unscaled part and Integer.MAX_VALUE for scale part

    }
}
