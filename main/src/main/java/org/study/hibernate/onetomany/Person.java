package org.study.hibernate.onetomany;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private List<Email> emails;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public List<Email> getEmails() {
        return emails;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emails=" + (emails == null ? null : emails.stream().map(x -> x.getEmail()).collect(Collectors.toList())) +
                '}';
    }
}
