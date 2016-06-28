package org.study.core.collections;

import java.util.HashSet;
import java.util.Set;

/*
    HashMap, HashSet may used objects without overridden equals()
    However one will not be able to find item by other than initial object
 */
public class ClassWithoutEqualsInMap {
    private final String name;

    public ClassWithoutEqualsInMap(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Set<ClassWithoutEqualsInMap> set = new HashSet<>();
        String name = "test";
        ClassWithoutEqualsInMap testObj = new ClassWithoutEqualsInMap(name);
        ClassWithoutEqualsInMap newObj = new ClassWithoutEqualsInMap(name);
        set.add(testObj);
        System.out.println("is testObj in set? " + set.contains(testObj));
        System.out.println("is newObj in set? " + set.contains(newObj));
    }

}
