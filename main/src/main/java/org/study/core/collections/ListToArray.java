package org.study.core.collections;

import java.util.ArrayList;
import java.util.List;

/*
    May be constructed used asArray method
    To Object[] without parameter and to explicit array using parameter
 */
public class ListToArray {
    private Object[] listToArray1(List<String> list) {
        return list.toArray();
    }

    private String[] listToArray2(List<String> list) {
        return list.toArray(new String[list.size()]);
    }

    private String[] listToArray3(List<String> list) {
        return list.stream().toArray(String[]::new);
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        ListToArray conv = new ListToArray();
        System.out.println("General method: " + conv.listToArray1(list));
        String[] array = conv.listToArray2(list);
        System.out.println("Safe method: " + array);
        list.add("test");
        System.out.println("Array is not backed: " + array.length);
        System.out.println("Modern method: " + conv.listToArray3(list));
    }
}
