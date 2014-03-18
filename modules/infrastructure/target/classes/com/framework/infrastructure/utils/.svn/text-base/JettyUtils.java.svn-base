package com.framework.infrastructure.utils;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * URL问题
 * 
 * 注意:采用debug模式启动Application才能进入调试模式
 * 
 * 创建Jetty Server的工具类.
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
	 * 以发布的形式启动jetty
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
	 * 创建用于正常运行调试的Jetty Server, 以src/main/webapp为Web应用目录.
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
	 * 创建用于Functional Test的Jetty Server: 1.以src/main/webapp为Web应用目录.
	 * 2.以test/resources/web.xml指向applicationContext-test.xml创建测试环境.
	 */
	public static Server buildTestServer(int port, String contextPath) {
		Server server = buildNormalServer(port, contextPath);
		((WebAppContext) server.getHandler())
				.setDescriptor("D:\\CMC\\WebRoot\\WEB-INF\\web.xml");
		return server;
	}
}
