package org.study.org.study.design_patterns.prototype.solution;

import org.study.org.study.backend.BaseProduct;

public class Product extends BaseProduct implements Cloneable {

    public Product clone() throws CloneNotSupportedException {
        return (Product) super.clone();
    }
}
