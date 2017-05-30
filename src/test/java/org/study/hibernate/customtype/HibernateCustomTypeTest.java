package org.study.hibernate.customtype;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.study.hibernate.HibernateUtils;

import static org.junit.Assert.*;

/**
 * Created by yur on 25.10.2016.
 */
public class HibernateCustomTypeTest {
    private Address address1;
    private Address address2;
    private Person person1;
    private Person person2;
    private Person person3;

    @Before
    public void setUp() throws Exception {
        address1 = new Address();
        address1.setAddress("address1");
        address1.setCity("city");

        address2 = new Address();
        address2.setAddress("address2");
        address2.setCity("city");

        person1 = new Person();
        person1.setAddress(address1);
        person1.setName("person1");

        person2 = new Person();
        person2.setAddress(address2);
        person2.setName("person2");

        person3 = new Person();
        person3.setAddress(address1);
        person3.setName("person3");
    }

    @Test
    public void testEmbedding() throws Exception {
        try (Session session = HibernateUtils.getInstance().getSession()) {
            Transaction tx = session.beginTransaction();
            session.save(person1);
            session.save(person2);
            session.save(person3);
            tx.commit();
            Person person11 = session.get(Person.class, person1.getId());
            Person person12 = session.get(Person.class, person2.getId());
            Person person13 = session.get(Person.class, person3.getId());

            assertNotNull(person11);
            assertNotNull(person12);
            assertNotNull(person13);
            assertEquals(person1, person11);
            assertEquals(person2, person12);
            assertEquals(person3, person13);
        }

    }

}