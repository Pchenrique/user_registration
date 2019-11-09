package br.com.ifce.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.ifce.utils.PersistenceUtil;

public abstract class DaoGenericAbs<T> implements IDaoGeneric<T> {

  EntityManager manager;
  private Class classe;

  public DaoGenericAbs() {
    manager = PersistenceUtil.getEntityManager();
    classe = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  public void save(T t) throws DaoException {
    try {
      manager.getTransaction().begin();
      manager.persist(t);
      manager.getTransaction().commit();
    } catch (Exception e) {
      manager.getTransaction().rollback();
      e.printStackTrace();
      throw new DaoException("...");
    }
  }

  public void update(T t) throws DaoException {
    try {
      manager.getTransaction().begin();
      manager.merge(t);
      manager.getTransaction().commit();
    } catch (Exception e) {
      manager.getTransaction().rollback();
      e.printStackTrace();
      throw new DaoException("...");
    }
  }

  public T find(Long id) throws DaoException {
    try {
      return (T) manager.find(this.classe, id);
    } catch (Exception e) {
      manager.getTransaction().rollback();
      e.printStackTrace();
      throw new DaoException("...");
    }
  }

  public void remove(T t) throws DaoException {
    try {
      manager.getTransaction().begin();
      manager.remove(t);
      manager.getTransaction().commit();
    } catch (Exception e) {
      manager.getTransaction().rollback();
      e.printStackTrace();
      throw new DaoException("...");
    }
  }
  public List<T> all() throws DaoException {
    try {
      Criteria criteria = getCriteria();
      criteria.addOrder(Order.asc("id")); // orderBy id;
      return (List<T>) criteria.list();
    } catch (Exception e) {
      manager.getTransaction().rollback();
      e.printStackTrace();
      throw new DaoException("...");
    }
  }

  public Criteria getCriteria() {
    Session session = ((Session) manager.getDelegate());
    return session.createCriteria(this.classe);
  }
}  