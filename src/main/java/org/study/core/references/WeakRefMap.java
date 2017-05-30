package org.study.core.references;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;


/**
 * WeakHashMap implements weak references on keys
 * After GC all key objects that have only weak referenced are removed from map
 */
public class WeakRefMap {
    private final String name;

    private static WeakHashMap<WeakRefMap, String> map = new WeakHashMap<>();
    private static String secondKeyName = "second";
    private static String uuid1 = UUID.randomUUID().toString();
    private static WeakRefMap obj2 = new WeakRefMap(secondKeyName);

    public WeakRefMap(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        map.put(new WeakRefMap("first"), uuid1);
        map.put(obj2, UUID.randomUUID().toString());
        System.out.println("Map with size=" + map.size() + " before GC: ");
        for (Map.Entry<WeakRefMap, String> entry : map.entrySet()) {
            System.out.println("Entry: " + entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("Get 'first' element: " + map.get(new WeakRefMap("first")));
        System.out.println("Get 'second' element: " + map.get(new WeakRefMap(secondKeyName)));

        System.gc();

        System.out.println("Map isEmpty = " + map.isEmpty());
        System.out.println("Map with size=" + map.size() + " after GC: ");
        for (Map.Entry<WeakRefMap, String> entry : map.entrySet()) {
            System.out.println("Entry: " + entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("Get 'first' element (no strong refs on key, there is strong ref on value): " + map.get(new WeakRefMap("first")));
        System.out.println("Get 'second' element (strong ref exists): " + map.get(new WeakRefMap(secondKeyName)));
    }

    @Override
    public String toString() {
        return "WeakRefMap{" +
                "name='" + name + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeakRefMap that = (WeakRefMap) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
