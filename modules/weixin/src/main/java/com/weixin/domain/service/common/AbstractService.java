package com.weixin.domain.service.common;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;

import com.framework.infrastructure.persistence.hibernate.HibernateRepository;
import com.weixin.infra.Repository;
import com.weixin.infra.utils.ReflectionUtils;

/**
 * 
 * @author chenwentao
 * 
 */
@Service("abstractService")
@Transactional
public abstract class AbstractService<T> {

	protected Logger logger = Logger.getLogger(getClass());

	protected Class<T> entityClass;

	protected AbstractService() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	@Inject
	protected HibernateRepository repository;

	public boolean save(T t) {
		try {
			repository.save(t);
			return true;
		} catch (Exception e) {
			logger.error("save 错误", e);
			return false;
		}
	}

	public boolean saveOrUpdate(T t) {
		try {
			repository.saveOrUpdate(t);
			return true;
		} catch (Exception e) {
			logger.error("save 错误", e);
			return false;
		}
	}

	/**
	 * @param object
	 * @return
	 */
	public boolean merge(T entity) {
		try {
			repository.merge(entity);
			return true;
		} catch (Exception e) {
			logger.error("save 错误", e);
			return false;
		}
	}

	public T get(Serializable id) {
		return repository.load(entityClass, id);
	}

	public boolean update(T t) {
		try {
			repository.update(t);
			return true;
		} catch (Exception e) {
			logger.error("update 错误", e);
			return false;
		}
	}

	public boolean delete(Serializable id) {
		try {
			T t = get(id);
			repository.delete(t);
			return true;
		} catch (Exception e) {
			logger.error("delete 错误", e);
			return false;
		}
	}

	public boolean delete(T t) {
		try {
			repository.delete(t);
			return true;
		} catch (Exception e) {
			logger.error("delete 错误", e);
			return false;
		}
	}

	public List<T> find(String hql, Object... values) {
		return repository.find(hql, values);
	}

	public <T> T findUnique(String hql, Object... values) {
		List<T> results = repository.find(hql, values);
		if (CollectionUtils.isEmpty(results)) {
			return null;
		}

		return results.get(0);
	}

}