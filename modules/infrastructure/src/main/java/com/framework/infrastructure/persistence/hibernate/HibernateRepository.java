package com.framework.infrastructure.persistence.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Session.LockRequest;
import org.hibernate.cfg.Environment;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.framework.infrastructure.persistence.Page;
import com.framework.infrastructure.persistence.PropertyFilter;
import com.framework.infrastructure.persistence.PropertyFilter.MatchType;
import com.framework.infrastructure.utils.ReflectionUtils;

/**
 * lock����
 * 
 * <pre> 
 * A call to Session.load(), specifying a LockMode. 
 * A call to Session.lock(). 
 * A call to Query.setLockMode(). 
 * If Session.load() is called
 * with UPGRADE or UPGRADE_NOWAIT, and the requested object was not yet loaded
 * by the session, the object is loaded using SELECT ... FOR UPDATE. If load()
 * is calledChapter 12. Transactions and ... 186 for an object that is already
 * loaded with a less restrictive lock than the one requested, Hibernate calls
 * lock() for that object. Session.lock() performs a version number check if the
 * specified lock mode is READ, UPGRADE or UPGRADE_NOWAIT. In the case of
 * UPGRADE or UPGRADE_NOWAIT, SELECT ... FOR UPDATE is used. If the requested
 * lock mode is not supported by the database, Hibernate uses an appropriate
 * alternate mode instead of throwing an exception. This ensures that
 * applications are portable 
 * 
 * Demo
 * Query query = session.createQuery("FROM IsoDeal d WHERE chunk-clause");
 query.setLockMode("d", LockMode.UPGRADE); //for Inprocess status update
 List<IsoDeal> isoDeals = query.list();
 for (IsoDeal isoDeal : isoDeals) { //update status to Inprocess
 isoDeal.setStatus("Inprocess");
 }
 return isoDeals; 
 * </pre>
 * 
 * 
 * <pre>
 * �Ż�����
 * 1.�����һ������key,����ʹ��load��get����,��Ϊ������һ��Session�л�ʹ��һ������
 * ��hqlȴ����
 * 2.QueryCache�Ͷ��������Ƿֿ���,����û��ֱ�ӵ�������ϵ--����������֤,������������������������ϵ
 * 3.
 * @author chenwentao
 *
 * @version 0.9
 *
 * �޸İ汾: 0.9
 * �޸�����: Nov 17, 2010
 * �޸��� :  chenwentao
 * �޸�˵��: �������
 * ������ ��
 * </pre>
 */
@Scope("singleton")
@Repository("hibernateRepository")
@SuppressWarnings("unchecked")
public class HibernateRepository {

	private Logger logger = LoggerFactory.getLogger(HibernateRepository.class);

	@Inject
	protected SessionFactory sessionFactory;

	/**
	 * Ĭ�ϵ� HibernateRepository ���췽��
	 */
	public HibernateRepository() {
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * ȡ��sessionFactory.
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * ȡ�õ�ǰSession.
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * @param entity
	 */
	public <T> Serializable save(T entity) {
		return getSession().save(entity);
	}

	/**
	 * 
	 */
	public <T> void persist(T entity) {
		getSession().persist(entity);

	}

	/**
	 * ��������
	 * 
	 * @param entities
	 */
	public void save(Collection<?> entities) {
		Session session = getSession();
		// ���ö�������
		session.setCacheMode(CacheMode.IGNORE);
		int i = 0;
		int batchSize = 30;

		try {
			batchSize = Integer
					.parseInt((Environment.getProperties().get(Environment.STATEMENT_BATCH_SIZE).toString()));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

		for (Iterator<?> it = entities.iterator(); it.hasNext();) {
			session.saveOrUpdate(it.next());

			// ÿִ��batchSize�����һ��һ������
			if (++i % batchSize == 0) {
				session.flush();
				session.clear();
			}
		}

		if (i % batchSize != 0) {
			session.flush();
			session.clear();
		}
	}

	/**
	 * @param entity
	 */
	public <T> void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	 * @param entity
	 */
	public <T> void update(T entity) {
		getSession().update(entity);
	}

	/**
	 * @param object
	 * @return
	 */
	public <T> T merge(T entity) {
		return (T) getSession().merge(entity);
	}

	/**
	 * @param persistentEntity
	 */
	public <T> void delete(T entity) {
		Assert.notNull(entity, "entity����Ϊ��");
		getSession().delete(entity);
	}

	/**
	 * ��idɾ������.
	 * 
	 * @param entityClass
	 * @param id
	 */
	public void delete(Class entityClass, Serializable id) {
		delete(get(entityClass, id));
	}

	/**
	 * ����batchExecute����HQL,����ɾ��
	 * 
	 * @param deleteHql
	 * @param id
	 */
	public void delete(String deleteHql, Object... values) {
		batchExecute(deleteHql, values);
	}

	/**
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <T> T get(Class entityClass, final Serializable id) {
		return (T) getSession().get(entityClass, id);
	}

	/**
	 * ��id�б���ȡ�����б�.
	 */
	public <T> List<T> get(final Collection<?> ids, Class entityClass) {
		return find(entityClass, Restrictions.in(getIdName(entityClass), ids));
	}

	/**
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @param lockMode
	 * @return
	 */
	public <T> T get(Class entityClass, Serializable id, LockOptions lockMode) {
		return (T) getSession().get(entityClass, id, lockMode);
	}

	/**
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <T> T load(Class entityClass, Serializable id) {
		return (T) getSession().load(entityClass, id);
	}

	/**
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @param lockMode
	 * @return
	 */
	public <T> T load(Class entityClass, Serializable id, LockOptions lockMode) {
		return (T) getSession().load(entityClass, id, lockMode);
	}

	/**
	 * �����Թ��������б����Ҷ����б�.
	 */
	public <T> List<T> find(Class entityClass, List<PropertyFilter> filters) {
		Criterion[] criterions = buildCriterionByPropertyFilter(filters);
		return find(entityClass, criterions);
	}

	/**
	 * @param <T>
	 * @param hql
	 * @param values
	 * @return
	 */
	public <T> List<T> find(final String hql, final Object... values) {
		return createQuery(null, false, null, null, hql, values).list();
	}

	/**
	 * @param <T>
	 * @param lockMode
	 * @param hql
	 * @param values
	 * @return
	 */
	public <T> List<T> find(final LockOptions lockMode, final String hql, final Object... values) {
		return createQuery(lockMode, false, null, null, hql, values).list();
	}

	/**
	 * ��HQL��ѯ�����б�.
	 * 
	 * @param values
	 *            ��������,�����ư�.
	 */
	public <T> List<T> find(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).list();
	}

	/**
	 * @param <T>
	 * @param needQueryCache
	 * @param cacheRegion
	 * @param chacheMode
	 * @param hql
	 * @param values
	 * @return
	 */
	public <T> List<T> find(final boolean needQueryCache, final String cacheRegion, final CacheMode chacheMode,
			String hql, Object... values) {
		return createQuery(null, needQueryCache, cacheRegion, chacheMode, hql, values).list();
	}

	/**
	 * @param <T>
	 * @param needQueryCache
	 * @param cacheRegion
	 * @param chacheMode
	 * @param hql
	 * @param values
	 * @return
	 */
	public <T> List<T> find(final LockOptions lockMode, final boolean needQueryCache, final String cacheRegion,
			final CacheMode chacheMode, String hql, Object... values) {
		return createQuery(lockMode, needQueryCache, cacheRegion, chacheMode, hql, values).list();
	}

	/**
	 * ��Criteria��ѯ�����б�.
	 * 
	 * @param criterions
	 *            �����ɱ��Criterion.
	 */
	public <T> List<T> find(final Class entityClass, final Criterion... criterions) {
		return createCriteria(entityClass, criterions).list();
	}

	/**
	 * �����Բ��Ҷ����б�,ƥ�䷽ʽΪ���.
	 */
	public <T> List<T> findBy(final Class entityClass, final String propertyName, final Object value) {
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(entityClass, criterion);
	}

	/**
	 * ��id�б���ȡ����.
	 */
	public <T> List<T> findByIds(Class entityClass, List ids) {
		return find(entityClass, Restrictions.in(getIdName(entityClass), ids));
	}

	/**
	 * ��HQL��ѯΨһ����.
	 * 
	 * @param values
	 *            ��������,�����ư�.
	 */
	public <T> T findUnique(final String hql, final Map<String, ?> values) {
		return (T) createQuery(null, hql, values, false, null, null).uniqueResult();
	}

	/**
	 * ��Criteria��ѯΨһ����.
	 * 
	 * @param criterions
	 *            �����ɱ��Criterion.
	 */
	public <T> T findUnique(Class entityClass, final Criterion... criterions) {
		return (T) createCriteria(entityClass, criterions).uniqueResult();
	}

	/**
	 * ��HQL��ѯΨһ����.
	 * 
	 * @param values
	 *            ��������,�����ư�.
	 */
	public <T> T findUnique(final String hql, final Object... values) {
		return (T) createQuery(null, false, null, null, hql, values).uniqueResult();
	}

	/**
	 * �����Բ���Ψһ����, ƥ�䷽ʽΪ���.
	 */
	public <T> T findUniqueBy(Class entityClass, final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName����Ϊ��");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(entityClass, criterion).uniqueResult();
	}

	/**
	 * ��HQL��ҳ��ѯ.
	 * 
	 * @param page
	 *            ��ҳ����. ע�ⲻ֧�����е�orderBy����.
	 * @param hql
	 *            hql���.
	 * @param values
	 *            ��������,�����ư�.
	 * 
	 * @return ��ҳ��ѯ���, ��������б������в�ѯ�������.
	 */
	@SuppressWarnings("unchecked")
	public <T> Page<T> findPage(final Page<T> page, final String hql, final Map<String, ?> values) {
		Assert.notNull(page, "page����Ϊ��");

		Query q = createQuery(hql, values);

		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setTotalCount(totalCount);
		}

		setPageParameterToQuery(q, page);

		List result = q.list();
		page.setResult(result);
		return page;
	}

	/**
	 * ��HQL��ҳ��ѯ.
	 * 
	 * @param page
	 *            ��ҳ����.��֧�����е�orderBy����.
	 * @param hql
	 *            hql���.
	 * @param values
	 *            �����ɱ�Ĳ�ѯ����,��˳���.
	 * 
	 * @return ��ҳ��ѯ���, ��������б������в�ѯʱ�Ĳ���.
	 */
	public <T> Page<T> findPage(final Page<T> page, final String hql, final Object... values) {
		Query q = createQuery(null, false, null, null, hql, values);

		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setTotalCount(totalCount);
		}

		setPageParameterToQuery(q, page);
		List result = q.list();
		page.setResult(result);

		return page;
	}

	/**
	 * ��HQL��ҳ��ѯ.
	 * 
	 * @param page
	 *            ��ҳ����.��֧�����е�orderBy����.
	 * @param hql
	 *            hql���.
	 * @param values
	 *            �����ɱ�Ĳ�ѯ����,��˳���.
	 * 
	 * @return ��ҳ��ѯ���, ��������б������в�ѯʱ�Ĳ���.
	 */
	public <T> Page<T> findPage(final LockOptions lockMode, final Page<T> page, final String hql,
			final Object... values) {
		Query q = createQuery(lockMode, false, null, null, hql, values);

		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setTotalCount(totalCount);
		}

		setPageParameterToQuery(q, page);
		List result = q.list();
		page.setResult(result);

		return page;
	}

	/**
	 * ��HQL��ҳ��ѯ.
	 * 
	 * @param page
	 *            ��ҳ����.��֧�����е�orderBy����.
	 * @param hql
	 *            hql���.
	 * @param values
	 *            �����ɱ�Ĳ�ѯ����,��˳���.
	 * 
	 * @return ��ҳ��ѯ���, ��������б������в�ѯʱ�Ĳ���.
	 */

	public <T> Page<T> findPage(final LockOptions lockMode, final boolean needQueryCache, final String cacheRegion,
			final CacheMode chacheMode, final Page<T> page, final String hql, final Object... values) {
		Query q = createQuery(lockMode, needQueryCache, cacheRegion, chacheMode, hql, values);

		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setTotalCount(totalCount);
		}

		setPageParameterToQuery(q, page);
		List result = q.list();
		page.setResult(result);
		return page;
	}

	/**
	 * ��HQL��ҳ��ѯ.
	 * 
	 * @param page
	 *            ��ҳ����.��֧�����е�orderBy����.
	 * @param hql
	 *            hql���.
	 * @param values
	 *            �����ɱ�Ĳ�ѯ����,��˳���.
	 * 
	 * @return ��ҳ��ѯ���, ��������б������в�ѯʱ�Ĳ���.
	 */

	public <T> Page<T> findPage(final boolean needQueryCache, final String cacheRegion, final CacheMode chacheMode,
			final Page<T> page, final String hql, final Object... values) {
		Query q = createQuery(null, needQueryCache, cacheRegion, chacheMode, hql, values);

		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setTotalCount(totalCount);
		}

		setPageParameterToQuery(q, page);
		List result = q.list();
		page.setResult(result);
		return page;
	}

	/**
	 * ��Criteria��ҳ��ѯ.
	 * 
	 * @param page
	 *            ��ҳ����.
	 * @param criterions
	 *            �����ɱ��Criterion.
	 * 
	 * @return ��ҳ��ѯ���.��������б������в�ѯʱ�Ĳ���.
	 */

	public <T> Page<T> findPage(final Class entityClass, final Page<T> page, final Criterion... criterions) {
		Criteria c = createCriteria(entityClass, criterions);

		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(c);
			page.setTotalCount(totalCount);
		}
		setPageParameterToCriteria(c, page);
		List result = c.list();
		page.setResult(result);

		return page;
	}

	/**
	 * �����Թ��������б���ҳ���Ҷ���.
	 */
	public <T> Page<T> findPage(final Class entityClass, final Page<T> page, final List<PropertyFilter> filters) {
		Criterion[] criterions = buildCriterionByPropertyFilter(filters);

		return findPage(entityClass, page, criterions);
	}

	/**
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> findAll(Class entityClass) {
		String queryString = new StringBuilder().append("from ").append(entityClass.getName()).toString();

		return this.<T> find(queryString);
	}

	/**
	 * ��ȡȫ������, ֧�ְ���������.
	 */
	public <T> List<T> findAll(Class entityClass, String orderByProperty, boolean isAsc) {
		Criteria c = createCriteria(entityClass);
		if (isAsc) {
			c.addOrder(Order.asc(orderByProperty));
		} else {
			c.addOrder(Order.desc(orderByProperty));
		}

		return c.list();
	}

	//==========================================SQL=============================================================//
	public int executeSql(final String sql, final Object... values) {
		SQLQuery queryObject = getSession().createSQLQuery(sql);

		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}

		return queryObject.executeUpdate();
	}

	/**
	 * <pre>
	 * ����ָ����ʵ���ࡢsql����sql�����б�����ѯ�����б�
	 * 	ʾ��:
	 * 		String sql = "select * from cust where id = ? and sex = ? ";
	 * 		List&lt;Cust&gt; custs = genericDao.findBySQL(Cust.class, sql, 100, 1);
	 * </pre>
	 * 
	 * @param <T>
	 *            ʵ������
	 * @param entityClass
	 *            ʵ����, ����������Ӧʵ�壬��null����
	 * @param sql
	 *            ָ����sql���
	 * @param values
	 *            �����б�
	 * @return List&lt;T&gt; ʵ���б�
	 */
	public <T> List<T> findBySQLSimple(final Class entityClass, final String sql, final Object... values) {
		SQLQuery queryObject = getSession().createSQLQuery(sql);

		if (entityClass != null) {
			queryObject.addEntity(entityClass);
		}

		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}

		return queryObject.list();
	}

	/**
	 * ���Ʒ��صĶ��������
	 * 
	 * @param maxCount
	 * 
	 * @param hql
	 * 
	 * @param values
	 * 
	 * @return
	 */
	public <T> List<T> findTop(final int maxCount, final String hql, final Object... values) {
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		query.setMaxResults(maxCount);

		return query.list();
	}

	/**
	 * ��������
	 * 
	 * @param entityClass
	 * @param filters
	 * @return
	 */
	public long count(final Class entityClass, final List<PropertyFilter> filters) {
		Criterion[] criterions = buildCriterionByPropertyFilter(filters);
		Criteria c = createCriteria(entityClass, criterions);
		long totalCount = countCriteriaResult(c);

		return totalCount;
	}

	/**
	 * ����hql����ѯ
	 * 
	 * @param queryString
	 * @param values
	 * @return
	 */
	public QueryBuilder buildQuery(String queryString, Object... values) {
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		QueryBuilder builder = new QueryBuilder(query);

		return builder;
	}

	// /**
	// * ����hql����ѯ
	// *
	// * @param queryString
	// * @param values
	// * @return
	// */
	// public <T> QueryBuilder buildQuery(final Page<T> page ,String
	// queryString, Object... values) {
	// Query query = getSession().createQuery(queryString);
	// if (values != null) {
	// for (int i = 0; i < values.length; i++) {
	// query.setParameter(i, values[i]);
	// }
	// }
	// QueryBuilder builder = new QueryBuilder(query);
	//		
	// Assert.notNull(page, "page����Ϊ��");
	//
	// Query q = createQuery(hql, values);
	//
	// if (page.isAutoCount()) {
	// long totalCount = countHqlResult(hql, values);
	// page.setTotalCount(totalCount);
	// }
	//
	// setPageParameterToQuery(q, page);
	//
	// List result = q.list();
	// page.setResult(result);
	// return page;
	//
	// return builder;
	// }

	/**
	 * ���ݲ�ѯHQL������б�����Query����. ��find()�����ɽ��и������Ĳ���.
	 * 
	 * @param values
	 *            �����ɱ�Ĳ���,��˳���.
	 */
	public Query createQuery(final String queryString, final Object... values) {
		return createQuery(null, false, null, null, queryString, values);
	}

	/**
	 * ���ݲ�ѯHQL������б�����Query����. ��find()�����ɽ��и������Ĳ���.
	 * 
	 * @param values
	 *            ��������,�����ư�.
	 */
	public Query createQuery(final String queryString, final Map<String, ?> values) {
		Assert.hasText(queryString, "queryString����Ϊ��");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}

	/**
	 * ���ݲ�ѯHQL������б�����Query����. .
	 * 
	 * @param lockMode
	 * @param queryString
	 * @param values
	 *            ��������,�����ư�
	 * @param needQueryCache
	 * @param cacheRegion
	 * @param chacheMode
	 * 
	 * @return
	 */
	public Query createQuery(final LockOptions lockMode, final String queryString, final Map<String, ?> values,
			final boolean needQueryCache, final String cacheRegion, final CacheMode chacheMode) {
		Query query = getSession().createQuery(queryString);
		// ������
		if (lockMode != null) {
			query.setLockOptions(lockMode);
		}
		// ��������
		if (needQueryCache) {
			query.setCacheable(needQueryCache);

			if (cacheRegion != null) {
				query.setCacheRegion(cacheRegion);
			}
			if (chacheMode != null) {
				query.setCacheMode(chacheMode);
			}
		}

		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}

	/**
	 * ���ݲ�ѯHQL������б�����Query����.
	 * 
	 * �����װ��find()����ȫ��Ĭ�Ϸ��ض�������ΪT,����ΪTʱʹ�ñ�����.
	 * 
	 * @param values
	 *            �����ɱ�Ĳ���,��˳���.
	 */
	public Query createQuery(final LockOptions lockMode, final boolean needQueryCache, final String cacheRegion,
			final CacheMode chacheMode, final String queryString, final Object... values) {
		Query query = getSession().createQuery(queryString);
		// �Ƿ����
		if (lockMode != null) {
			query.setLockOptions(lockMode);
		}

		// ��������
		if (needQueryCache) {
			query.setCacheable(needQueryCache);

			if (cacheRegion != null) {
				query.setCacheRegion(cacheRegion);
			}
			if (chacheMode != null) {
				query.setCacheMode(chacheMode);
			}
		}
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	/**
	 * ����Criterion��������Criteria.
	 * 
	 * �����װ��find()����ȫ��Ĭ�Ϸ��ض�������ΪT,����ΪTʱʹ�ñ�����.
	 * 
	 * @param criterions
	 *            �����ɱ��Criterion.
	 */
	public Criteria createCriteria(final Class entityClass, final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}

		return criteria;
	}

	/**
	 * ִ��HQL���������޸�/ɾ������.
	 */
	public int batchExecute(final String hql, final Object... values) {
		return createQuery(null, false, null, null, hql, values).executeUpdate();
	}

	/**
	 * ִ��HQL���������޸�/ɾ������.
	 * 
	 * @return ���¼�¼��.
	 */
	public int batchExecute(final String hql, final Map<String, ?> values) {
		return createQuery(null, hql, values, false, null, null).executeUpdate();
	}

	/**
	 * �ж϶��������ֵ�����ݿ����Ƿ�Ψһ.
	 * 
	 * ���޸Ķ�����龰��,����������޸ĵ�ֵ(value)��������ԭ����ֵ(orgValue)�����Ƚ�.
	 */
	public boolean isPropertyUnique(Class entityClass, final String propertyName, final Object newValue,
			final Object oldValue) {
		if (newValue == null || newValue.equals(oldValue)) {
			return true;
		}
		Object object = findUniqueBy(entityClass, propertyName, newValue);
		return (object == null);
	}

	/**
	 * ��ʼ������. ʹ��load()�����õ��Ľ��Ƕ���Proxy, �ڴ���View��ǰ��Ҫ���г�ʼ��.
	 * ֻ��ʼ��entity��ֱ������,�������ʼ���ӳټ��صĹ������Ϻ�����. �����ʼ����������,��ʵ���µĺ���,ִ��:
	 * Hibernate.initialize(user.getRoles())����ʼ��User��ֱ�����Ժ͹�������.
	 * Hibernate.initialize
	 * (user.getDescription())����ʼ��User��ֱ�����Ժ��ӳټ��ص�Description����.
	 */
	public void initProxyProperty(Object proxyProperty) {
		Hibernate.initialize(proxyProperty);
	}

	/**
	 * @param entity
	 * @param lockMode
	 * @param timeOut
	 * @param isLockScope
	 */
	public void lock(Object entity, LockOptions lockMode, int timeOut, boolean isLockScope) {
		LockRequest lockRequest = getSession().buildLockRequest(lockMode);
		lockRequest.setTimeOut(timeOut).setScope(isLockScope).lock(entity);
	}

	/**
	 * @param entity
	 * @param locikMode
	 */
	public void refresh(Object entity, LockOptions lockMode) {
		getSession().refresh(entity, lockMode);
	}

	/**
	 * 
	 */
	public void clear() {
		getSession().clear();
	}

	/**
	 * Flush��ǰSession.
	 */
	public void flush() {
		getSession().flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.team.cmc.infrastructure.persistence.hibernate.I#contains(java.lang
	 * .Object)
	 */
	public boolean contains(Object entity) {
		return getSession().contains(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.team.cmc.infrastructure.persistence.hibernate.I#evict(java.lang.Object
	 * )
	 */
	public void evict(Object entity) {
		getSession().evict(entity);
	}

	/**
	 * ΪQuery����distinct transformer.
	 */
	public Query distinct(Query query) {
		query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return query;
	}

	/**
	 * ΪCriteria����distinct transformer.
	 */
	public Criteria distinct(Criteria criteria) {
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

	/**
	 * ȡ�ö����������.
	 */
	public String getIdName(Class entityClass) {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}

	/**
	 * ִ��count��ѯ��ñ���Hql��ѯ���ܻ�õĶ�������.
	 * 
	 * ������ֻ���Զ������򵥵�hql���,���ӵ�hql��ѯ�����б�дcount����ѯ.
	 */
	protected long countHqlResult(final String hql, final Map<String, ?> values) {
		String countHql = prepareCountHql(hql);

		try {
			Long count = findUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	/**
	 * ִ��count��ѯ��ñ���Hql��ѯ���ܻ�õĶ�������.
	 * 
	 * ������ֻ���Զ������򵥵�hql���,���ӵ�hql��ѯ�����б�дcount����ѯ.
	 */
	protected long countHqlResult(final String hql, final Object... values) {
		String countHql = prepareCountHql(hql);

		try {
			Long count = findUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	private String prepareCountHql(String orgHql) {
		String fromHql = orgHql;
		// select�Ӿ���order by�Ӿ��Ӱ��count��ѯ,���м򵥵��ų�.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");
		String countHql = "select count(*) " + fromHql;

		return countHql;
	}

	/**
	 * ִ��count��ѯ��ñ���Criteria��ѯ���ܻ�õĶ�������.
	 */
	protected long countCriteriaResult(final Criteria c) {
		CriteriaImpl impl = (CriteriaImpl) c;

		// �Ȱ�Projection��ResultTransformer��OrderByȡ����,������ߺ���ִ��Count����
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List) ReflectionUtils.getFieldValue(impl, "orderEntries");
			ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			logger.error("�������׳����쳣:{}", e.getMessage());
		}

		// ִ��Count��ѯ
		Long totalCountObject = (Long) c.setProjection(Projections.rowCount()).uniqueResult();
		long totalCount = (totalCountObject != null) ? totalCountObject : 0;

		// ��֮ǰ��Projection,ResultTransformer��OrderBy�����������ȥ
		c.setProjection(projection);

		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			c.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			logger.error("�������׳����쳣:{}", e.getMessage());
		}

		return totalCount;
	}

	/**
	 * ���÷�ҳ������Query����,��������.
	 */
	protected <T> Query setPageParameterToQuery(final Query q, final Page<T> page) {
		// hibernate��firstResult����Ŵ�0��ʼ
		q.setFirstResult(page.getFirst() - 1);
		q.setMaxResults(page.getPageSize());

		return q;
	}

	/**
	 * ���÷�ҳ������Criteria����,��������.
	 */
	protected <T> Criteria setPageParameterToCriteria(final Criteria c, final Page<T> page) {
		// hibernate��firstResult����Ŵ�0��ʼ
		c.setFirstResult(page.getFirst() - 1);
		c.setMaxResults(page.getPageSize());

		if (page.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(page.getOrder(), ',');

			Assert.isTrue(orderByArray.length == orderArray.length, "��ҳ�������������,�����ֶ���������ĸ��������");

			for (int i = 0; i < orderByArray.length; i++) {
				if (Page.ASC.equals(orderArray[i])) {
					c.addOrder(Order.asc(orderByArray[i]));
				} else {
					c.addOrder(Order.desc(orderByArray[i]));
				}
			}
		}

		return c;
	}

	/**
	 * �����������б�����Criterion����,��������.
	 */
	protected Criterion[] buildCriterionByPropertyFilter(final List<PropertyFilter> filters) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		for (PropertyFilter filter : filters) {
			if (!filter.hasMultiProperties()) { // ֻ��һ��������Ҫ�Ƚϵ����.
				Criterion criterion = buildCriterion(filter.getPropertyName(), filter.getMatchValue(),
						filter.getMatchType());
				criterionList.add(criterion);
			} else {// �������������Ҫ�Ƚϵ����,����or����.
				Disjunction disjunction = Restrictions.disjunction();
				for (String param : filter.getPropertyNames()) {
					Criterion criterion = buildCriterion(param, filter.getMatchValue(), filter.getMatchType());
					disjunction.add(criterion);
				}
				criterionList.add(disjunction);
			}
		}
		return criterionList.toArray(new Criterion[criterionList.size()]);
	}

	/**
	 * ������������������Criterion,��������.
	 */
	protected Criterion buildCriterion(final String propertyName, final Object propertyValue, final MatchType matchType) {
		Assert.hasText(propertyName, "propertyName����Ϊ��");
		Criterion criterion = null;
		// ����MatchType����criterion
		switch (matchType) {
		case EQ:
			criterion = Restrictions.eq(propertyName, propertyValue);
			break;
		case LIKE:
			criterion = Restrictions.like(propertyName, (String) propertyValue, MatchMode.ANYWHERE);
			break;
		case LE:
			criterion = Restrictions.le(propertyName, propertyValue);
			break;
		case LT:
			criterion = Restrictions.lt(propertyName, propertyValue);
			break;
		case GE:
			criterion = Restrictions.ge(propertyName, propertyValue);
			break;
		case GT:
			criterion = Restrictions.gt(propertyName, propertyValue);
			break;
		case ISNULL:
			criterion = Restrictions.isNull(propertyName);
			break;
		case ISNOTNULL:
			criterion = Restrictions.isNotNull(propertyName);
		}
		return criterion;
	}

	/**
	 * <pre>
	 * Bzuilderģʽ
	 * @author chenwentao
	 *
	 * @version 0.9
	 *
	 * �޸İ汾: 0.9
	 * �޸�����: May 11, 2011
	 * �޸��� :  chenwentao
	 * �޸�˵��: �������
	 * ������ ��
	 * </pre>
	 */
	public static class QueryBuilder {

		private Query query;

		public QueryBuilder(Query query) {
			this.query = query;
		}

		public QueryBuilder lock(LockOptions lockOptions) {
			Assert.notNull(lockOptions, "lockOptions����Ϊ��");
			query.setLockOptions(lockOptions);
			return this;
		}

		public QueryBuilder setQueryCache(boolean needQueryCache) {
			query.setCacheable(needQueryCache);
			return this;
		}

		public QueryBuilder setCacheRegion(String cacheRegion) {
			query.setCacheRegion(cacheRegion);
			return this;
		}

		public QueryBuilder setCahceMode(CacheMode cacheMode) {
			query.setCacheMode(cacheMode);
			return this;
		}

		public Query build() {
			return query;
		}

		public <T> List<T> list() {
			return query.list();
		}

	}

	// TODO ����FetchMode
	// /**
	// * .setFetchMode("mate", FetchMode.EAGER) .setFetchMode("kittens",
	// * FetchMode.EAGER)
}