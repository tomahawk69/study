package org.study.hibernate.collections;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.study.hibernate.HibernateUtils;

import java.util.Arrays;

import static org.junit.Assert.*;

public class CollectionTest {

    @Test
    public void testCollection() throws Exception {
        Person person = new Person();
        person.setName("test");
        Email email1 = new Email("aaa@aaa.com");
        Email email2 = new Email("bbb@bbb.com");
        person.setEmails(Arrays.asList(email1, email2));

        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        session.save(person);
        session.save(email1);
        session.save(email2);

        tx.commit();
        session.close();

        session = HibernateUtils.getInstance().getSession();
        tx = session.beginTransaction();

        person = session.get(Person.class, person.getId());
        assertNotNull(person);
        assertNotNull(person.getEmails());
        assertEquals(2, person.getEmails().size());

        tx.commit();
        session.close();



    }
}