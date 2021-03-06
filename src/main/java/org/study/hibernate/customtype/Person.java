package org.study.hibernate.customtype;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.study.hibernate.converter.ConvertAddress;

import javax.persistence.*;

/**
 * Created by yur on 25.10.2016.
 */
@Entity
@Table(name = "person_embedded")
public class Person {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Integer id;

    @Basic
    private String name;

    @Column
    @Type(type = "org.study.hibernate.customtype.AddressUserType")
    private Address address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
