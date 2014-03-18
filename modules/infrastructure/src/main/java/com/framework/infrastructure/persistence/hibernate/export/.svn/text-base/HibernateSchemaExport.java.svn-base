package com.framework.infrastructure.persistence.hibernate.export;

import java.io.File;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * <pre>
 * @author chenwentao
 *
 * @version 0.9
 *
 * 修改版本: 0.9
 * 修改日期: Apr 29, 2011
 * 修改人 :  chenwentao
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
public class HibernateSchemaExport {

	static Session session;

	static Configuration config = null;

	static Transaction tx = null;

	public static void main(String[] args) {
		try {
			config = new Configuration().configure(new File(
					"src/hibernate.cfg.xml"));

			System.out.println("Creating tables...");

			SessionFactory sessionFactory = config.configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			SchemaExport schemaExport = new SchemaExport(config);
			schemaExport.create(true, true);

			System.out.println("Table created.");

			tx.commit();

		}
		catch (HibernateException e) {
			e.printStackTrace();
			try {
				tx.rollback();
			}
			catch (HibernateException e1) {
				e1.printStackTrace();
			}
		}
		finally {

		}
	}
}
