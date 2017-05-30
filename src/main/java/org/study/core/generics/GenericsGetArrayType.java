package org.study.core.generics;

public class GenericsGetArrayType {

    private static void getAndDescribeArray(Object object) {
        boolean isArray = object.getClass().isArray();
        System.out.println("=== " + object);
        System.out.println("Object is instance of array: " + isArray);
        if (isArray) {
            System.out.println("Class of array: " + object.getClass().getComponentType());
        }
    }

    public static void main(String[] args) {
        getAndDescribeArray(new int[]{1, 2, 3});
        getAndDescribeArray(new Integer[]{1, 2, 3});
        getAndDescribeArray(123);
    }
}
