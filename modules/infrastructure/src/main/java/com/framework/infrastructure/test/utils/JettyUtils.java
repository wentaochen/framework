package com.framework.infrastructure.test.utils;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * ����Jetty Server�Ĺ�����.
 * 
 * @author calvin
 */
public class JettyUtils {

	/**
	 * ���������������е��Ե�Jetty Server, ��src/main/webappΪWebӦ��Ŀ¼.
	 */
	public static Server buildNormalServer(int port, String contextPath) {
		Server server = new Server(port);
		WebAppContext webContext = new WebAppContext("src/main/webapp",
				contextPath);
		webContext.setClassLoader(Thread.currentThread()
				.getContextClassLoader());
		server.setHandler(webContext);
		server.setStopAtShutdown(true);
		return server;
	}

	/**
	 * ��������Functional Test��Jetty Server: 1.��src/main/webappΪWebӦ��Ŀ¼.
	 * 2.��test/resources/web.xmlָ��applicationContext-test.xml�������Ի���.
	 */
	public static Server buildTestServer(int port, String contextPath) {
		Server server = buildNormalServer(port, contextPath);
		((WebAppContext) server.getHandler())
				.setDescriptor("src/test/resources/web.xml");
		return server;
	}
}
