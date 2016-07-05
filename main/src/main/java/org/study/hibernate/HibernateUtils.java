package org.study.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Simple utility class to return session
 */
public class HibernateUtils {
    private static HibernateUtils instance = new HibernateUtils();
    private SessionFactory sessionFactory;

    private HibernateUtils() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public static HibernateUtils getInstance() {
        return instance;
    }
}
