package org.study.hibernate.inheritance;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "classb")
@PrimaryKeyJoinColumn(name = "base_id")
public class ClassB extends BaseClass {
    private String b;

    public ClassB() {
    }

    public ClassB(String b) {
        this.b = b;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "ClassB{" +
                "b='" + b + '\'' +
                '}';
    }
}
