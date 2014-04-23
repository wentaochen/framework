package com.weixin.infra;

import java.io.Serializable;
import java.util.List;

public interface Repository {

	public <T> Serializable save(T entity);

	public <T> void update(T entity);

	public <T> void delete(T entity);

	public <T> T load(Serializable id);

	public <T> List<T> find(final String hql, final Object... values);

}
