/*
 * @(#)ContextPathListener.java 2012-8-9
 * 
 * Copy Right@ chenwt
 */

package com.weixin.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.weixin.infra.config.UploadConfig;

/**
 * <pre>
 * 		Replace the page document ${CTX} for web context
 * </pre>
 * 
 * @version 1.0, 2012-8-9
 * @author chenwentao
 * 
 */
public class ConfigListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		sc.setAttribute(UploadConfig.IMAGE_URL,
				UploadConfig.getProperty(UploadConfig.IMAGE_URL));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
