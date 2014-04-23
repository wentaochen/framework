package com.weixin.domain.service.common;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenwentao
 * 
 */
public interface Service<T> {

	boolean save(T t);

	boolean saveOrUpdate(T t);

	boolean update(T t);

	boolean delete(T t);

	boolean delete(Serializable id);

	boolean delete(String deleteHql, Object... values);

	T load(Serializable id);

	T get(Serializable id);

	List<T> findAll();

	List<T> find(String hql, Object... params);

	T findUnique(String hql, Object... values);
}