package org.study.hibernate.onetomany;

import javax.persistence.*;

@Entity
public class Email {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    @ManyToOne
    private Person person;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", person=" + (person == null ? null : person.getName()) +
                '}';
    }
}
