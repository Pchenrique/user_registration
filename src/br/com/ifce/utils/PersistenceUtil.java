package br.com.ifce.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class PersistenceUtil {

  private static final String PERSISTENCE_UNIT_NAME = "GenericDAO";
  private static EntityManagerFactory entityManagerFactory = null;
  private static EntityManager entityManager = null;

  private PersistenceUtil() {
  }

  static {
      try {
          entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  //SINGLETON
  public static EntityManager getEntityManager() {
      try {
          if (entityManager == null || !entityManager.isOpen()) {
              entityManager = entityManagerFactory.createEntityManager();
          }
          return entityManager;
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
  }
}