package com.framework.infrastructure.utils;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * 通过http://localhost:8082/可以访问控制台
 * @author chenwentao
 *
 * @version 0.9
 *
 * 修改版本: 0.9
 * 修改日期: Nov 9, 2010
 * 修改人 :  chenwentao
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
@Lazy(false)
@Component("h2DataBaseBoot")
public class H2DataBaseBoot {

	private static Logger logger = LoggerFactory
			.getLogger(H2DataBaseBoot.class);

	protected static Server H2_DATABASE_SERVER;

	@PostConstruct
	public void startServer() throws SQLException {
		H2_DATABASE_SERVER = Server.createWebServer(
				new String[] { "-tcpPort", "9094" }).start();
		logger.info("H2数据库在{}端口启动成功", 9094);
	}

	@PreDestroy
	public void destroy() {
		H2_DATABASE_SERVER.shutdown();
		logger.info("H2数据库关闭");
	}
}
