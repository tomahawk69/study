package org.study.hibernate.onetoone;

import javax.persistence.*;

@Entity
public class OneToOnePerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @OneToOne
    private OneToOneEmail email;

    public OneToOnePerson() {
    }

    public OneToOnePerson(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public OneToOneEmail getEmail() {
        return email;
    }

    public void setEmail(OneToOneEmail email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OneToOnePerson that = (OneToOnePerson) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return email != null ? email.equals(that.email) : that.email == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OneToOnePerson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email=" + (email == null ? null : email.getEmail()) +
                '}';
    }

}
