package org.study.hibernate.inheritance;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "classcd")
@PrimaryKeyJoinColumn(name="base_id")
public class ClassCD extends ClassC {
    private String cd;

    public ClassCD() {
    }

    public ClassCD(String cd) {
        this.cd = cd;
    }

    public String getD() {
        return cd;
    }

    public void setD(String d) {
        cd = d;
    }

    @Override
    public String toString() {
        return "ClassCD{" +
                "cd='" +  cd + '\'' +
                '}';
    }
}
