package org.study.core.collections;

import java.util.Arrays;
import java.util.List;

public class ArrayToListIsBacked {
    public static void main(String[] args) {
        String[] array = {"first", "second", "third"};
        List<String> list = Arrays.asList(array);
        System.out.println("List is equal to array: " + list);
        array[1] = "middle";
        System.out.println("List reflexes changes to array: " + list);
        System.out.println("List is immutable: ");
        try {
            list.add("last");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
