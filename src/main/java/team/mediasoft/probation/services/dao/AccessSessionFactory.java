package team.mediasoft.probation.services.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Class downloads session factory.
 */
public class AccessSessionFactory {

    /**
     * Variable SessionFactory.
     */
    private static SessionFactory sessionFactory;

    /**
     * Private constructor.
     */
    private AccessSessionFactory() {
    }

    /**
     * Getter SessionFactory.
     * @return variable sessionFactory
     */
    public static synchronized SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").
                    buildSessionFactory();
        }
        return sessionFactory;
    }

}
