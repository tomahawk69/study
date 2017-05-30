package org.study.design_patterns.observer;

import java.util.HashSet;
import java.util.Set;

public abstract class Subject {
    private Set<Observer> observers = new HashSet<>();

    void registerObserver(Observer observer) {
        observers.add(observer);
    }

    protected void notifyObservers() {
        for (Observer observer : observers) {
            observer.handle(this);
        }
    }

}
