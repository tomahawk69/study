package org.study.core.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
    Binary search does not make difference between sorted and unsorted lists
    It returns negated first element that is greater than the search key in case of unsuccessful result
 */
public class BinarySearchExample {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(1);
        list.add(5);
        list.add(12);
        list.add(3);
        System.out.println("Binary search in unsorted list: " + Collections.binarySearch(list, 1));
        System.out.println("Binary search in unsorted list: " + Collections.binarySearch(list, 11));
        Collections.sort(list);
        System.out.println("Binary search in sorted list: " + Collections.binarySearch(list, 1));
        System.out.println("Unsuccessful binary search in sorted list: " + Collections.binarySearch(list, 6));
        System.out.println("Search in the empty list: " + Collections.binarySearch(new ArrayList<Integer>(), 6));
    }
}
