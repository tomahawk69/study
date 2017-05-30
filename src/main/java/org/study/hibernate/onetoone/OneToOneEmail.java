package org.study.hibernate.onetoone;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
public class OneToOneEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Type(type = "string")
    private String email;

    @OneToOne(mappedBy = "email")
    private OneToOnePerson person;

    public OneToOneEmail() {
    }

    public OneToOneEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public OneToOnePerson getPerson() {
        return person;
    }

    public void setPerson(OneToOnePerson person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OneToOneEmail that = (OneToOneEmail) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return person != null ? person.equals(that.person) : that.person == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (person != null ? person.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OneToOneEmail{" +
                "id=" + id +
                ", email='" + email  + '\'' +
                ", person=" + (person  == null ? null : person.getName()) +
                '}';
    }
}
