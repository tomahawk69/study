package org.study.hibernate.inheritance;

import javax.persistence.*;

@Entity
@Table(name =  "baseclass")
@Inheritance(strategy= InheritanceType.JOINED)
public class BaseClass {

    @Id
    @GeneratedValue
    private Integer id;

    private String a;

    public BaseClass() {
    }

    public BaseClass(String a) {
        this.a = a;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "BaseClass{" +
                "a='" + a + '\'' +
                '}';
    }
}
