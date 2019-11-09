package br.com.ifce.dao;

import java.util.List;

import org.hibernate.Criteria;

public interface IDaoGeneric<T> {
  void save(T t) throws DaoException;;
  void update(T t) throws DaoException;;
  void remove(T t) throws DaoException;;
  T find(Long id) throws DaoException;;
  List<T> all() throws DaoException;;
  Criteria getCriteria();   
}