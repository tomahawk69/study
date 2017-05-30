package org.study.design_patterns.prototype.solution;

import org.study.backend.BaseProduct;

public class Product extends BaseProduct implements Cloneable {

    public Product clone() throws CloneNotSupportedException {
        return (Product) super.clone();
    }

    /**
     * Created by yur on 28.07.2015.
     */
    public static class Product1Impl extends Product {

        @Override
        public String toString() {
            return "Product1Impl{" + super.toString() + "}";
        }
    }

    /**
     * Created by yur on 28.07.2015.
     */
    public static class Product2Impl extends Product {

        @Override
        public String toString() {
            return "Product2Impl{" + super.toString() + "}";
        }
    }
}
