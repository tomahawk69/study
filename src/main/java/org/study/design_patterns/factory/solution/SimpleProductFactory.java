package org.study.design_patterns.factory.solution;

public class SimpleProductFactory {

    public ProductInterface createProduct(String productName) {
        switch (productName.toLowerCase()) {
            case "product1":
                return new Product1();
            case "product2":
                return new Product2();
            default:
                return null;
        }
    }
}
