package org.study.hibernate.criterias;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.study.hibernate.HibernateUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ProjectionsTest {

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
    public void testProjection() throws Exception {
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        Criteria cr = session.createCriteria(Person.class);
        cr.setProjection(Projections.max("salary"));
        List<Object> result = cr.list();
        tx.commit();
        session.close();
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(3100, result.get(0));
    }

    @Test
    public void testProjectionEx() throws Exception {
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        Criteria cr = session.createCriteria(Person.class);
        cr.setProjection(Projections.projectionList()
                .add(Projections.alias(Projections.max("salary"), "maxSalary"))
                .add(Projections.alias(Projections.min("salary"), "minSalary"))
                .add(Projections.alias(Projections.groupProperty("department"), "dept")));
        cr.addOrder(Order.asc("dept"));
        List<Object> result = cr.list();
        tx.commit();
        session.close();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(3100, ((Object[]) result.get(0))[0]);
    }


}
