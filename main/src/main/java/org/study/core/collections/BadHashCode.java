package org.study.core.collections;

import java.util.HashMap;
import java.util.Map;

/*
In this example equals is more wide than hashCode:
- we need good distributed hashCode
- objects are equals when their name is equal
As result, one may expect that he or she may get appropriate element by constructing element with only matching name
 */
public class BadHashCode {
    private final Integer id;
    private final String name;

    public BadHashCode(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BadHashCode that = (BadHashCode) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BadHashCode{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public static void main(String[] args) {
        BadHashCode obj1 = new BadHashCode("test", 12);
        Map<BadHashCode, Integer> map = new HashMap<>();
        System.out.println("Adding obj1 to set is ok: ");
        map.put(obj1, obj1.id);
        System.out.println("Obj1 is in map: " + map.get(obj1));
        BadHashCode obj2 = new BadHashCode("test", 0);
        System.out.println("obj1 and obj2 are equal: " + obj1.equals(obj2));
        System.out.println("However we cannot find Obj2 in map: " + map.get(obj2));
    }
}
