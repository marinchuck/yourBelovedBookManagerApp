package com.progmatic.hazi3.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil implements AutoCloseable {

    private static final EntityManagerFactory emf;
    private static EntityManager em;

    static {
        emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
    }

    public static EntityManager getEntityManager() {
        if (em != null && em.isOpen()) {
            return em;
        }
        em = emf.createEntityManager();
        return em;
    }

    public static void closeEntityManager(){
        if(em != null && em.isOpen())
            em.close();
    }

    @Override
    public void close() throws Exception {
        if (em != null) {
            em.close();
        }
        emf.close();
    }
}
