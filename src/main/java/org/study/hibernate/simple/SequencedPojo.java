package org.study.hibernate.simple;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SequencedPojo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public SequencedPojo() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SequencedPojo{" +
                "id=" + id +
                '}';
    }
}
