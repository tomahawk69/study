package org.study.hibernate.inheritance;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.study.hibernate.HibernateUtils;

public class InheritanceTest {

    @Test
    public void testInheritance() throws Exception {
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        BaseClass a = new BaseClass("a");
        ClassB b = new ClassB("b");
        ClassC c = new ClassC("c");
        ClassCD cd = new ClassCD("cd");

        System.out.println("saving a");
        session.save(a);
        System.out.println("a saved");

        System.out.println("saving b");
        session.save(b);
        System.out.println("b saved");

        System.out.println("saving c");
        session.save(c);
        System.out.println("c saved");

        System.out.println("saving cd");
        session.save(cd);
        System.out.println("cd saved");

        tx.commit();
        session.close();


    }
}
