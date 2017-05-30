package org.study.interfaces;

public class InterfaceWithDefaultMethodImpl implements InterfaceWithDefaultMethod {

    @Override
    public void callMe() {
        System.out.println("Call me, anonymous");
    }

    // will not compile
    // private void callMe(String name) { }

    // will compile and override default method
    public void callMe(String name) { System.out.println("Call off, " + name); }

    public static void main(String[] args) {
        InterfaceWithDefaultMethod obj = new InterfaceWithDefaultMethodImpl();
        obj.callMe();
        obj.callMe("Evan");
    }
}
