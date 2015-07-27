package org.study.org.study.backend;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BaseProduct {
    private final String title = UUID.randomUUID().toString();
    private final Map<String, Object> map = new HashMap<>();
    {
        UUID uuid = UUID.randomUUID();
        map.put(uuid.toString(), uuid);
        uuid = UUID.randomUUID();
        map.put(uuid.toString(), uuid);
    }

    public String getTitle() {
        return title;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
                "title='" + title + '\'' +
                ", map=" + map +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseProduct that = (BaseProduct) o;

        if (!title.equals(that.title)) return false;
        return map.equals(that.map);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + map.hashCode();
        return result;
    }
}
