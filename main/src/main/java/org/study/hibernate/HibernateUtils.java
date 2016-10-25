package org.study.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.study.hibernate.customtype.Address;
import org.study.hibernate.customtype.AddressUserType;

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
        MetadataSources sources = new MetadataSources(registry);
        MetadataBuilder metadataBuilder = sources.getMetadataBuilder();
        metadataBuilder.applyBasicType(AddressUserType.INSTANCE, "address");
        sessionFactory = sources.buildMetadata().buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public static HibernateUtils getInstance() {
        return instance;
    }
}
