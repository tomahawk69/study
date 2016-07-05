package org.study.hibernate.enums;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.study.hibernate.HibernateUtils;

import static org.junit.Assert.*;

public class PojoEnumTest {

    @Test
    public void testEnumSave() throws Exception {
        Person person = new Person();
        person.setName("test me");
        person.setSex(Sex.WOMAN);
        person.setActive(true);
        person.setTestMe("test me");

        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        session.save(person);

        tx.commit();
        session.close();

        session = HibernateUtils.getInstance().getSession();
        tx = session.beginTransaction();

        Person personResult = session.get(Person.class, person.getId());

        tx.commit();
        session.close();

        assertNotNull(personResult);
        assertEquals(person.getSex(), personResult.getSex());
        assertEquals(person.isActive, personResult.isActive);
        assertEquals(person.getTestMe(), personResult.getTestMe());

    }
}