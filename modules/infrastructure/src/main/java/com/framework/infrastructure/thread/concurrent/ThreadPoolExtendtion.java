package com.framework.infrastructure.thread.concurrent;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class ThreadPoolExtendtion extends ThreadPoolExecutor {

	private static final Logger logger = LoggerFactory
			.getLogger(ThreadPoolExtendtion.class);

	/**
	 * 正在运行的线程
	 */
	private final Map<Runnable, Boolean> processes = new ConcurrentHashMap<Runnable, Boolean>();

	/**
	 * 每个线程的启动时间 使用ThreadLocal类
	 */
	private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();

	/**
	 * 总的时间
	 */
	private long totalTime;

	/**
	 * 总的任务
	 */
	private int totalTasks;

	public ThreadPoolExtendtion(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory);
	}

	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		processes.put(r, Boolean.TRUE);
		startTime.set(Long.valueOf(System.currentTimeMillis()));
	}

	protected void afterExecute(Runnable r, Throwable t) {
		long time = System.currentTimeMillis() - startTime.get().longValue();
		synchronized (this) {
			totalTime += time;
			++totalTasks;

		}
		processes.remove(r);
		super.afterExecute(r, t);

	}

	public Set<Runnable> getInProgressTasks() {
		return Collections.unmodifiableSet(processes.keySet());
	}

	public synchronized int getTotalTasks() {
		return totalTasks;
	}

	public synchronized double getAverageTaskTime() {
		return (totalTasks == 0) ? 0 : totalTime / (double) totalTasks;
	}

	public static void main(String[] args) {
		long x = 2;
		int y = 5;
		// Wrong: yields result 0.0
		double value1 = x / y;

		// Right: yields result 0.4
		double value2 = x / (double) y;

	}

}
