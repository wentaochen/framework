package com.weixin.infra;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


//@Scope("singleton")
//@Repository("cacheRepository")
//@SuppressWarnings("unchecked")
public class CacheRepository implements com.weixin.infra.Repository {

	public <T> Serializable save(T entity) {
		//Cache.customer.put(t., value)
		return null;
	}

	public <T> void update(T entity) {

	}

	public <T> void delete(T entity) {

	}

	public <T> List<T> find(String hql, Object... values) {
		return null;
	}

	public <T> T load(Serializable id) {
		return (T) Cache.customer.get(id);
	}

}
