package com.framework.infrastructure.persistence.hibernate;

import java.util.Map;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.EntityPersister;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

import com.framework.infrastructure.test.spring.SpringTxTestCase;

/**
 * 简单测试所有Entity类的O/R Mapping.
 * 
 * @author calvin
 */
// @ContextConfiguration(locations = { "/spring/applicationContext-test-2.5.xml"
// })
@ContextConfiguration(locations = { "/applicationContext-core-test.xml" })
public class HibernateMappingTest extends SpringTxTestCase {

	private static Logger logger = LoggerFactory
			.getLogger(HibernateMappingTest.class);

	@Inject
	private SessionFactory sessionFactory;

	@Test
	@SuppressWarnings("unchecked")
	public void allClassMapping() throws Exception {
		Session session = sessionFactory.openSession();

		try {
			Map metadata = sessionFactory.getAllClassMetadata();
			for (Object o : metadata.values()) {
				EntityPersister persister = (EntityPersister) o;
				String className = persister.getEntityName();
				Query q = session.createQuery("from " + className + " c");
				q.iterate();
				logger.debug("ok: " + className);
			}
		}
		finally {
			session.close();
		}
	}
}
