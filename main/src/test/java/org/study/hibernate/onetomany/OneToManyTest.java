package org.study.hibernate.onetomany;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.study.hibernate.HibernateUtils;

import static org.junit.Assert.*;

public class OneToManyTest {

    @Test
    public void testOneToMany() throws Exception {
        Person person = new Person();
        person.setName("little person");

        Email email1 = new Email();
        Email email2 = new Email();
        email1.setEmail("first@my.mail");
        email1.setPerson(person);
        email2.setEmail("second@fun.job");
        email2.setPerson(person);

        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        session.save(person);
        session.save(email1);
        session.save(email2);

        tx.commit();
        session.close();


        session = HibernateUtils.getInstance().getSession();
        tx = session.beginTransaction();

        Person personResult = session.get(Person.class, person.getId());

        tx.commit();
        session.close();

        assertNotNull(personResult);
        assertNotNull(personResult.getEmails());
        assertTrue(personResult.getEmails().size() > 0);


    }
}