package org.study.hibernate.criterias;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.study.hibernate.HibernateUtils;

import java.util.List;

import static org.junit.Assert.*;

public class CriteriaTest {


    @Before
    public void setUp() throws Exception {
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        session.save(new Person(Department.DEV, "dev1", 2000, 25));
        session.save(new Person(Department.DEV, "dev2", 3000, 40));
        session.save(new Person(Department.DEV, "dev3", 3100, 36));

        session.save(new Person(Department.QA, "qa1", 1000, 21));
        session.save(new Person(Department.QA, "qa2", 2000, 33));
        session.save(new Person(Department.QA, "qa3", 2100, 22));

        tx.commit();
        session.close();
    }

    @Test
    public void testCriteria() throws Exception {
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        Criteria cr = session.createCriteria(Person.class);
        cr.add(Restrictions.eq("name", "dev1"));
        Person result = (Person) cr.uniqueResult();

        tx.commit();
        session.close();
        assertNotNull(result);
    }

    @Test
    public void testCriteriaList() throws Exception {
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        Criteria cr = session.createCriteria(Person.class);
        cr.add(Restrictions.eq("department", Department.DEV));
        List<Person> result = cr.list();

        tx.commit();
        session.close();
        assertNotNull(result);
        assertEquals(3, result.size());
    }


    @Test
    public void testCriteriaAnd() throws Exception {
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        Criteria cr = session.createCriteria(Person.class);
        cr.add(Restrictions.and(Restrictions.eq("department", Department.DEV), Restrictions.gt("salary", 2000)));
        List<Person> result = cr.list();

        tx.commit();
        session.close();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testCriteriaAndOr() throws Exception {
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        Criteria cr = session.createCriteria(Person.class);
        LogicalExpression and = Restrictions.and(Restrictions.eq("department", Department.DEV), Restrictions.gt("salary", 2000));
        LogicalExpression or = Restrictions.or(and, Restrictions.eq("name", "dev1"));
        cr.add(or);
        List<Person> result = cr.list();

        tx.commit();
        session.close();
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    public void testCriteriaOrder() throws Exception {
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        Criteria cr = session.createCriteria(Person.class);
        cr.addOrder(Order.desc("salary"));
        cr.setMaxResults(1);
        Person result = (Person) cr.uniqueResult();

        tx.commit();
        session.close();
        assertNotNull(result);
        assertEquals("dev3", result.getName());
    }
}