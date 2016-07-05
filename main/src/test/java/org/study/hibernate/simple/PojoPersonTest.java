package org.study.hibernate.simple;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;
import org.study.hibernate.HibernateUtils;

import java.util.UUID;

import static org.junit.Assert.*;

public class PojoPersonTest {

    @Test
    public void testSave() throws Exception {

        PojoPerson person = new PojoPerson("test");
        assertNull(person.getId());

        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();


        session.save(person);

        tx.commit();
        session.close();

        assertNotNull(person.getId());
    }

    @Test
    public void testRead() throws Exception {
        String name = UUID.randomUUID().toString();
        Long id = addPerson(name);

        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        PojoPerson person = session.get(PojoPerson.class, id);
        tx.commit();
        session.close();

        assertNotNull(person);
        assertEquals(name, person.getName());
    }

    @Test
    public void testUpdate() throws Exception {
        String name = UUID.randomUUID().toString();
        String newName = UUID.randomUUID().toString();

        Long id = addPerson(name);

        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        PojoPerson person = session.get(PojoPerson.class, id);

        person.setName(newName);

        tx.commit();
        session.close();

        assertEquals(newName, person.getName());
        person = getPersonById(id);
        assertNotNull(person);
        assertEquals(newName, person.getName());
    }

    @Test
    public void testMerge() throws Exception {
        String newName = UUID.randomUUID().toString();

        Long id = addPerson(UUID.randomUUID().toString());

        PojoPerson person = getPersonById(id);

        person.setName(newName);

        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        session.merge(person);

        tx.commit();
        session.close();

        assertEquals(newName, person.getName());
        person = getPersonById(id);
        assertNotNull(person);
        assertEquals(newName, person.getName());
    }

    @Test
    public void testRefresh() throws Exception {
        String name = UUID.randomUUID().toString();
        String newName = UUID.randomUUID().toString();

        Long id = addPerson(name);

        PojoPerson person = getPersonById(id);

        person.setName(newName);

        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        session.refresh(person);

        tx.commit();
        session.close();

        assertEquals(name, person.getName());
        person = getPersonById(id);
        assertNotNull(person);
        assertEquals(name, person.getName());
    }

    @Test
    public void testDelete() throws Exception {
        String name = UUID.randomUUID().toString();

        Long id = addPerson(name);

        PojoPerson person = getPersonById(id);
        assertNotNull(person);

        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        session.delete(person);

        tx.commit();
        session.close();

        person = getPersonById(id);
        assertNull(person);
    }

    private Long addPerson(String name) {
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        PojoPerson person = new PojoPerson(name);

        session.save(person);

        Long id = person.getId();

        tx.commit();
        session.close();

        return id;

    }

    private PojoPerson getPersonById(Long id) {
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        PojoPerson person = session.get(PojoPerson.class, id);

        tx.commit();
        session.close();

        return person;

    }

    @Test
    public void testUuidPojo() throws Exception {
        UUIDPojo person = new UUIDPojo();
        person.setName("Name me");
        person.setTrash("Transient!");

        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        session.save(person);

        tx.commit();
        session.close();

        session = HibernateUtils.getInstance().getSession();
        tx = session.beginTransaction();

        Query query = session.createQuery("from UUIDPojo");
        UUIDPojo personResult = (UUIDPojo) query.uniqueResult();

        tx.commit();
        session.close();

        assertEquals(person.getId(), personResult.getId());
        assertEquals(person.getName(), personResult.getName());
        assertNull(personResult.getTrash());

    }

    @Test
    public void testSequencePojo() throws Exception {
        SequencedPojo obj = new SequencedPojo();

        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        session.save(obj);

        tx.commit();
        session.close();

        assertNotNull(obj.getId());

        session = HibernateUtils.getInstance().getSession();
        tx = session.beginTransaction();


        SequencedPojo objResult = session.get(SequencedPojo.class, obj.getId());

        tx.commit();
        session.close();

        assertNotNull(objResult);

    }

}