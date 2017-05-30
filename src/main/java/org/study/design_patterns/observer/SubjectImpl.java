package org.study.design_patterns.observer;

public class SubjectImpl extends Subject {
    private final String name;

    public SubjectImpl(String name) {
        this.name = name;
    }

    public void makeSomeWork() {
        try {
            System.out.println("Start hard work " + this);
            Thread.sleep(1000);
            System.out.println("End hard work " + this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyObservers();
    }

    @Override
    public String toString() {
        return "SubjectImpl{" +
                "name='" + name + '\'' +
                '}';
    }
}
