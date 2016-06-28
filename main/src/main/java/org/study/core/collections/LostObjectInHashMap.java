package org.study.core.collections;

import java.util.HashSet;
import java.util.Set;

/*
Object will be lost in hashMap or hashSet if hashSet or equals methods returns non consistent values
 */
public class LostObjectInHashMap {
    private String name;

    public LostObjectInHashMap(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LostObjectInHashMap that = (LostObjectInHashMap) o;

        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public static void main(String[] args) {
        LostObjectInHashMap obj1 = new LostObjectInHashMap("test");
        Set<LostObjectInHashMap> set = new HashSet<>();
        set.add(obj1);
        System.out.println("set size is " + set.size());
        System.out.println("obj1 is in set? " + set.contains(obj1));
        System.out.println("changing name of obj1 ");
        obj1.setName("New glory name");
        System.out.println("obj1 is in set? " + set.contains(obj1));
        System.out.println("adding obj1 again " + set.add(obj1));
        System.out.println("set size is " + set.size());
        System.out.println("obj1 is in set? " + set.contains(obj1));
   }
}
