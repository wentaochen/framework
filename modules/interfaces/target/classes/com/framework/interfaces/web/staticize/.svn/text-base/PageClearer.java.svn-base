/*
 * @(#)PageClearer.java 2010-11-3
 * 
 * Copy Right@ 精伦电子股份有限公司
 */

package com.framework.interfaces.web.staticize;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import sun.nio.ch.FileChannelImpl;

/**
 * <pre>
 * @author wangzh
 * 
 * @version 1.0
 * 
 * 修改版本: 1.0
 * 修改日期: 2010-11-3
 * 修改人 :  wangzh
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
public class PageClearer {

	/**
	 * 静态页面存放位置
	 */
	private String pagesLocation = "c:/p";

	/**
	 * 清理所有静态页面
	 */
	public void clearAllPages() {
		deleteQuietly(new File(pagesLocation));
	}

	/**
	 * 删除文件或目录不抛异常
	 * 
	 * @param file
	 *            文件或目录对象
	 * @return 是否删除成功
	 */
	public static boolean deleteQuietly(File file) {
		if (file == null) {
			return false;
		}

		if (file.isDirectory()) {
			try {
				File[] fs = file.listFiles();
				if (fs != null && fs.length > 0) {
					for (File f : fs) {
						deleteQuietly(f);
					}
				}
			}
			catch (Exception e) {
			}
		}

		return deleteFile(file);
	}

	/**
	 * 彻底删除文件或目录,如果文件正在读写,则等待之后加锁清空文件内容再删除
	 * 
	 * @param file
	 *            文件或目录对象
	 * @return 是否删除成功
	 */
	private static boolean deleteFile(File file) {
		try {
			if (file.delete()) {
				return true;
			}
		}
		catch (Exception ex) {
		}

		if (!file.isFile()) {
			return false;
		}

		FileOutputStream out = null;
		try {
			// 先给文件加排他锁,然后清空文件内容
			out = new FileOutputStream(file);
			FileChannel fc = FileChannelImpl.open(out.getFD(), true, true, out);
			fc.lock();

			return true;
		}
		catch (Exception ex) {
			return false;
		}
		finally {
			if (out != null) {
				// 关闭文件流
				try {
					out.close();
				}
				catch (Exception ex) {
				}
			}
			try {
				file.delete();
			}
			catch (Exception ex) {
			}
		}
	}
}
