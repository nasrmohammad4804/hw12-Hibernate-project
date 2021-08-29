package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {


    private static class CreateEntityManager {


        private static final EntityManagerFactory entityManagerFactory = Persistence.
                createEntityManagerFactory("default");

    }

    public static EntityManagerFactory entityManagerFactory() {

        return CreateEntityManager.entityManagerFactory;
    }
}
