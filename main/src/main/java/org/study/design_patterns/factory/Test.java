package org.study.design_patterns.factory;

import org.study.design_patterns.factory.solution.SimpleProductFactory;

public class Test {

    public static void main(String[] args) {
        System.out.println("1) simple factory");
        SimpleProductFactory factory1 = new SimpleProductFactory();
        System.out.println(factory1.createProduct("product1").getClass());
        System.out.println(factory1.createProduct("product2").getClass());
    }
}
