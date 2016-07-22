package org.study.core.classes;

public class InnerClass {
    private final String name;

    public InnerClass(String name) {
        this.name = name;
    }

    void go() {
        new RealInnerClass("inner").go();
    }

    RealInnerClass goWithVars() {
        String s = "local var";
        return new RealInnerClass("inner") {
            void go() {
                System.out.println("Method-local inner class has access to local vars: " + s);
            }
        };
    }

    class RealInnerClass {
        private final String name;

        RealInnerClass(String name) {
            this.name = name;
        }

        void go() {
            System.out.println("My name is " + name);
            System.out.println("My parent name is " + InnerClass.this.name);
        }
    }

    public static void main(String[] args) {
        new InnerClass("outer").go();
        new InnerClass("outer").goWithVars().go();
    }
}
