package com.framework.infrastructure.thread.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.framework.infrastructure.utils.ReflectionUtils;

/**
 * <pre>
 * @author chenwentao
 *
 * @version 0.9
 *
 * 修改版本: 0.9
 * 修改日期: Oct 24, 2010
 * 修改人 :  chenwentao
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
public class CustomizableThreadFactory implements ThreadFactory {

	protected static final Logger logger = LoggerFactory
			.getLogger(CustomizableThreadFactory.class);

	private static final AtomicInteger poolNumber = new AtomicInteger(1);

	private final ThreadFactory factory;

	private final AtomicInteger threadNumber = new AtomicInteger(1);

	private final String namePrefix;

	/**
	 * 如果用户只传入默认的名称,那么它内部的ThreadFactory将采用 Executors.defaultThreadFactory()
	 * 
	 * @param poolName
	 */
	public CustomizableThreadFactory(String poolName) {
		namePrefix = poolName + "(" + poolNumber.getAndIncrement() + ")";
		factory = Executors.defaultThreadFactory();
	}

	/**
	 * @param factory
	 * @param poolName
	 */
	public CustomizableThreadFactory(ThreadFactory factory, String poolName) {
		this.factory = factory;
		this.namePrefix = poolName + "-[" + poolNumber.getAndIncrement() + "]";
	}

	/**
	 * {@inheritDoc}
	 */
	public Thread newThread(Runnable r) {
		String taskName = getTaskThreadName(r);
		Thread t = factory.newThread(r);
		t.setName(namePrefix + ":" + taskName + "-thread-"
				+ threadNumber.getAndIncrement());
		System.out.println(namePrefix + ":" + taskName + "-thread-"
				+ threadNumber.getAndIncrement());
		return t;
	}

	/**
	 * 如果传入的是Worker对象，那么就读取它的private Runnable firstTask属性的名称
	 * 
	 * @param ThreadPoolExecutor
	 *            .Worker对象
	 * @param 属性的名称
	 * @return 线程的名称
	 */
	protected String getTaskThreadName(Object obj) {
		Object fieldObj = ReflectionUtils.getFieldValue(obj, "firstTask");
		if (fieldObj == null) {
			return "";
		}
		try {
			Thread thread = (Thread) fieldObj;
			return thread.getName();

		}
		catch (Exception exception) {
			logger.info("读取taskThreadName报错");
			return "";
		}

	}

}
