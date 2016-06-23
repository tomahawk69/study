package org.study.core.generics;

public abstract class GenericsGetType<T> {
    protected Class<T> type;
    public Class<T> getType() {
        return type;
    }

    public static void main(String[] args) {
        GenericsGetType<String> obj1 = new GenerycsWorkaround<>(String.class);
        System.out.println(obj1.getType());
    }

    static class GenerycsWorkaround<T> extends GenericsGetType {
        public GenerycsWorkaround(Class<T> type) {
            this.type = type;
        }
    }

}
