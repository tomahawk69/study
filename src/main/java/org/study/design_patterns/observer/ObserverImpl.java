package org.study.design_patterns.observer;

public class ObserverImpl implements Observer {
    private final String name;

    public ObserverImpl(String name) {
        this.name = name;
    }

    @Override
    public void handle(Subject subject) {
        System.out.println(this + ": Subject " + subject + " just finished some hard work");
    }

    @Override
    public String toString() {
        return "ObserverImpl{" +
                "name='" + name + '\'' +
                '}';
    }
}
