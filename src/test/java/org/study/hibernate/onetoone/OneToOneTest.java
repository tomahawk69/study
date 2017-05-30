package org.study.hibernate.onetoone;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.study.hibernate.HibernateUtils;

import static org.junit.Assert.*;

public class OneToOneTest {

    @Test
    public void testSaveBoth() throws Exception {
        OneToOneEmail email = new OneToOneEmail("test@aaa.com");
        OneToOnePerson person = new OneToOnePerson("test person");

        //email.setPerson(person); we don't need it, it is populated automatically by mappedBy
        person.setEmail(email); // produces stack overflow if mappedBy is absent

        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        session.save(person);
        session.save(email);

        tx.commit();
        session.close();

        OneToOneEmail email1 = readEmail(email.getId());
        OneToOnePerson person1 = readPerson(person.getId());

        assertNotNull(email1);
        assertNotNull(person1);
        assertNotNull(email1.getPerson());
        assertNotNull(person1.getEmail());
//        assertEquals(person, person1);
    }

    private OneToOnePerson readPerson(Long id) {
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        OneToOnePerson result = session.get(OneToOnePerson.class, id);

        tx.commit();
        session.close();

        return result;
    }

    private OneToOneEmail readEmail(Long id) {
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        OneToOneEmail result = session.get(OneToOneEmail.class, id);

        tx.commit();
        session.close();

        return result;
    }

}