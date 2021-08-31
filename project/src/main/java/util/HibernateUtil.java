package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {


    private static EntityManagerFactory entityManagerFactory;

    private HibernateUtil() {

    }

    public static EntityManagerFactory entityManagerFactory() {

        if (entityManagerFactory == null)
            synchronized (HibernateUtil.class) {

                entityManagerFactory = Persistence.createEntityManagerFactory("default");
            }

        return entityManagerFactory;
    }
}
