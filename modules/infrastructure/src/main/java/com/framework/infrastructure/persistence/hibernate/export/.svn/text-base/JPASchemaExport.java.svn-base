package com.framework.infrastructure.persistence.hibernate.export;

import java.io.File;
import java.io.IOException;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

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
public class JPASchemaExport {

	static Session session;

	static Configuration config = null;

	static Transaction tx = null;

	private static TypeFilter[] entityTypeFilters = new TypeFilter[] {
			new AnnotationTypeFilter(Entity.class, false),
			new AnnotationTypeFilter(Embeddable.class, false),
			new AnnotationTypeFilter(MappedSuperclass.class, false),
			new AnnotationTypeFilter(org.hibernate.annotations.Entity.class,
					false) };

	public static void main(String[] args) throws Exception {
		try {
			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

			AnnotationConfiguration config = new AnnotationConfiguration()
					.configure(new File(
							"D:/gjxt/source/news/config/hibernate/hibernate.cfg.xml"));
			config.setProperty("output", "C:\\ddl.txt");

			String[] packagesToScan = { "com.team.cmc.domain.model" };
			for (String pkg : packagesToScan) {
				String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
						+ ClassUtils.convertClassNameToResourcePath(pkg)
						+ "/**/*.class";
				Resource[] resources = resourcePatternResolver
						.getResources(pattern);
				MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(
						resourcePatternResolver);
				for (Resource resource : resources) {
					if (resource.isReadable()) {
						MetadataReader reader = readerFactory
								.getMetadataReader(resource);
						String className = reader.getClassMetadata()
								.getClassName();
						if (matchesFilter(reader, readerFactory)) {
							config.addAnnotatedClass(resourcePatternResolver
									.getClassLoader().loadClass(className));
						}
					}
				}
			}

			SessionFactory sessionFactory = config.buildSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			SchemaExport schemaExport = new SchemaExport(config);
			schemaExport.setOutputFile("c:\\a.txt");
			schemaExport.create(true, true);

			System.out.println("Table created.");

			tx.commit();

			//			
			// config = new Configuration().configure(new File(
			// "src/hibernate.cfg.xml"));
			//
			// System.out.println("Creating tables...");
			//
			// SessionFactory sessionFactory = config.configure()
			// .buildSessionFactory();
			// session = sessionFactory.openSession();
			// tx = session.beginTransaction();
			//
			// SchemaExport schemaExport = new SchemaExport(config);
			// schemaExport.create(true, true);
			//
			// System.out.println("Table created.");
			//
			// tx.commit();

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

	/**
	 * Check whether any of the configured entity type filters matches the
	 * current class descriptor contained in the metadata reader.
	 */
	private static boolean matchesFilter(MetadataReader reader,
			MetadataReaderFactory readerFactory) throws IOException {
		if (entityTypeFilters != null) {
			for (TypeFilter filter : entityTypeFilters) {
				if (filter.match(reader, readerFactory)) {
					return true;
				}
			}
		}
		return false;
	}
}
