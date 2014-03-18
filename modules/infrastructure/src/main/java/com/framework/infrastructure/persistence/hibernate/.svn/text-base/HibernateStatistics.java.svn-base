package com.framework.infrastructure.persistence.hibernate;

import java.lang.management.ManagementFactory;
import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.hibernate.SessionFactory;
import org.hibernate.jmx.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 启动JMX,来监控Statistics
 * @author chenwentao
 *
 * @version 0.9
 *
 * 修改版本: 0.9
 * 修改日期: Nov 18, 2010
 * 修改人 :  wangzh
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
@Service("hibernateStatistics")
public class HibernateStatistics {

	private static final Logger logger = LoggerFactory
			.getLogger(HibernateStatistics.class);

	@Inject
	protected SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void register() {
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		Hashtable tb = new Hashtable();
		tb.put("type", "statistics");
		tb.put("sessionFactory", "all");
		try {
			ObjectName satisticsMBeanName = new ObjectName(
					"hibernate:name=statistics");
			// on = new ObjectName("hibernate:name=statistics", tb);
			StatisticsService stats = new StatisticsService();
			stats.setSessionFactory(sessionFactory);
			stats.setStatisticsEnabled(true);
			server.registerMBean(stats, satisticsMBeanName);
			logger.info("hibernateStatistics注册到MBeanServer");
		}
		catch (Exception e) {
			logger.error("", e);
		}
	}
}
