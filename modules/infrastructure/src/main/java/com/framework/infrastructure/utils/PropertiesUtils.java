package com.framework.infrastructure.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * Properties Util函数.
 * 
 * @author calvin
 */
public final class PropertiesUtils {

	private static final String DEFAULT_ENCODING = "UTF-8";

	private static Logger logger = LoggerFactory
			.getLogger(PropertiesUtils.class);

	private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();

	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	private static PropertiesUtils instance = null;

	private Map<String, String> values = new HashMap<String, String>();

	private PropertiesUtils() {

		Properties properties = null;
		try {
			properties = loadProperties("application.properties");
		}
		catch (IOException e) {
			logger.error("email配置文件没有找到", e);
		}
		Enumeration<?> names = properties.propertyNames();
		
		Assert.notNull(names, "application.properties加载失败");
		
		while (names.hasMoreElements()) {
			String key = (String) names.nextElement();
			String value = properties.getProperty(key);
			values.put(key, value);
		}
	}

	public static PropertiesUtils getInstance() {
		synchronized (PropertiesUtils.class) {
			if (instance == null) {
				instance = new PropertiesUtils();
			}
		}

		return instance;
	}

	/**
	 * 载入多个properties文件, 相同的属性最后载入的文件将会覆盖之前的载入.
	 * 
	 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
	 */
	public static Properties loadProperties(String... locations)
			throws IOException {
		Properties props = new Properties();

		for (String location : locations) {

			logger.debug("Loading properties file from classpath:" + location);

			InputStream is = null;
			try {
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				propertiesPersister.load(props, new InputStreamReader(is,
						DEFAULT_ENCODING));
			}
			catch (IOException ex) {
				logger.info("Could not load properties from classpath:"
						+ location + ": " + ex.getMessage());
			}
			finally {
				if (is != null) {
					is.close();
				}
			}
		}
		return props;
	}

	/**
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return (String) getInstance().values.get(key);
	}
}
