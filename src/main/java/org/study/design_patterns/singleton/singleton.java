package org.study.design_patterns.singleton;

public class Singleton {
    private Singleton singleton;

    private Singleton() {

    }

    public synchronized Singleton getInstance() {
        if (singleton != null) {
            singleton = new Singleton();
        }
        return singleton;
    }



}
