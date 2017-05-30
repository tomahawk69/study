package org.study.hibernate.inheritance;

import javax.persistence.*;

@Entity(name = "classc")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@PrimaryKeyJoinColumn(name="base_id")
public class ClassC extends BaseClass {
    private String c;

    public ClassC() {
    }

    public ClassC(String c) {
        this.c = c;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "ClassC{" +
                "c='" + c + '\'' +
                '}';
    }
}
