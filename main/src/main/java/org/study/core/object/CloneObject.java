package org.study.core.object;


import org.study.backend.BaseProduct;

public class CloneObject extends BaseProduct implements Cloneable {

    public CloneObject cloneObject() throws CloneNotSupportedException {
        return (CloneObject) super.clone();
    }
}
