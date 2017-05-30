package org.study.hibernate.onetomany;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    private List<Person> persons;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                '}';
    }
}
