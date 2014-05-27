/*
 * @(#)SystemProperties.java 2012-7-30
 * 
 * Copy Right@ chenwt
 */

package com.weixin.infra.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * <pre>
 * </pre>
 * 
 * @version 1.0, 2012-7-30
 * @author chenwentao
 * 
 */
@Configuration
@PropertySource("classpath:/properties/${spring.profiles.default}.properties")
public class UploadConfig {

	/**
	 * 上传图片保存路径
	 */
	public static final String UPLOAD_SAVEPATH = "savepath";

	/**
	 * 图片在url地址中访问的地址
	 */
	public static final String IMAGE_URL = "imageUrl";

	private static Environment environment;

	@Inject
	public void setEnvironment(Environment environment) {
		UploadConfig.environment = environment;
	}

	public UploadConfig() {
	}

	public static String getProperty(String key) {
		return environment.getProperty(key);

	}

}
