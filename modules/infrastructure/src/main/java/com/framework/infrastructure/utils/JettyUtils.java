package com.framework.infrastructure.utils;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * URL����
 * 
 * ע��:����debugģʽ����Application���ܽ������ģʽ
 * 
 * ����Jetty Server�Ĺ�����.
 * 
 * @author calvin
 */
public class JettyUtils {

	public static void main(String[] args) throws Exception {
		int port = 88;
		String contextPath = "/";
		String warPath = "D:/gjxt/source/news/WebRoot";

		buildWarServer(port, contextPath, warPath);
	}

	/**
	 * �Է�������ʽ����jetty
	 */
	private static void buildWarServer(int port, String contextPath,
			String warPath) throws Exception {
		Server server = new Server(port);
		WebAppContext webContext = new WebAppContext();
		webContext.setClassLoader(Thread.currentThread()
				.getContextClassLoader());
		webContext.setContextPath(contextPath);
		webContext.setWar(warPath);
		server.setHandler(webContext);
		server.setStopAtShutdown(true);
		((WebAppContext) server.getHandler())
				.setDescriptor("D:/gjxt/source/news/WebRoot/WEB-INF/web.xml");
		server.start();
		server.join();
	}

	/**
	 * ���������������е��Ե�Jetty Server, ��src/main/webappΪWebӦ��Ŀ¼.
	 */
	public static Server buildNormalServer(int port, String contextPath) {
		Server server = new Server(port);
		WebAppContext webContext = new WebAppContext("D:\\CMC\\WebRoot",
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
				.setDescriptor("D:\\CMC\\WebRoot\\WEB-INF\\web.xml");
		return server;
	}
}
