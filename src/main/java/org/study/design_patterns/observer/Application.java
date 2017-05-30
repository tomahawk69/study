package org.study.design_patterns.observer;

public class Application {

    public static void main(String[] args) {
        Observer observer1 = new ObserverImpl("first observer");
        Observer observer2 = new ObserverImpl("second observer");
        SubjectImpl subject1 = new SubjectImpl("first");
        SubjectImpl subject2 = new SubjectImpl("second");

        subject1.registerObserver(observer1);
        subject2.registerObserver(observer1);
        subject1.registerObserver(observer2);

        subject1.makeSomeWork();
        subject2.makeSomeWork();

    }
}
