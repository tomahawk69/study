package org.study.interfaces;

public interface InterfaceWithDefaultMethod {
    void callMe();

    static void callMeTwice(String name) {
        // couldn't execute default and non-static methods
        // callMe(name);
        // callMe();
    }

    default void callMe(String name) {
        if (name == null) {
            callMe();
        } else {
            System.out.println("Call me, " + name);
        }
    }
}
