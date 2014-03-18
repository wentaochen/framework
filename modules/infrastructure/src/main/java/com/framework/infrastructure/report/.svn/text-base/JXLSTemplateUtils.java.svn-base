package com.framework.infrastructure.report;

/*
 * Copyright (c) 2006 HEER Software, Inc. All rights reserved.
 * 
 * This software consists of contributions made by many individuals on behalf of
 * Heer R&D. For more information, please see <http://www.heerit.com/>.
 * 
 * Author Heer InfoTech Co., Ltd. @(#)JXLSTemplateUntils.java Mar 22, 2010
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import com.framework.infrastructure.utils.ClassLoaderUtil;

/**
 * <b>简述</b><br>
 * <br>
 * <code>JXLSTemplateUtils.java</code> 的主要功能是<br>
 * 
 * <pre>
 * HSSFWorkbook workbook = JXLSTemplateUtils.produceHSSFWorkbookWithJXLS(
 * 		JXLSTemplateUtils.getJXLSInputStream(&quot;meetingAllFormat.xls&quot;), dataMap);
 * 
 * //以流的形式返回数据到页面上
 * WebUtil.responseExcelByStream(response, workbook, &quot;会议文件.xls&quot;);
 * 
 * </pre>
 * 
 * <p>
 * <b>特点</b>
 * <ul>
 * <li>
 * <li>
 * </ul>
 * <b>注意事项</b><br>
 * <br>
 * 
 * @author <a href="mailto:chenwentaokl@yahoo.com.cn">chenwentao</a>
 * 
 * @Date Mar 22, 2010 2:01:49 PM
 * 
 * @version 1.189, 10/21/05
 * 
 * @see
 * 
 * @since Ver 1.0
 * 
 * 
 */
public class JXLSTemplateUtils {

	/**
	 * 模板文件的存放目录
	 */
	public static final String XLS = "xls/";

	public static final String RTF = "rtf/";

	/**
	 * 根据xls模板文件和数据生成HSSFWorkbook对象
	 * 
	 * @param 文件名称
	 * @param 数据
	 */
	public static Workbook produceHSSFWorkbookWithJXLS(String fileName, Map data) {
		if (fileName == null) {
			throw new NullPointerException("xls模板的文件不能为空");
		}

		try {
			return new XLSTransformer().transformXLS(
					getJXLSInputStream(fileName), data);
		}
		catch (ParsePropertyException e) {
			throw new RuntimeException(e);
		}
		catch (InvalidFormatException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据名称获得JXLS的模板输入流
	 */
	public static InputStream getJXLSInputStream(String fileName) {
		try {
			return ClassLoaderUtil.getResource(fileName,
					JXLSTemplateUtils.class).openStream();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据xls模板文件和数据生成HSSFWorkbook对象
	 * 
	 * @param 文件输入流
	 * @param 数据
	 */
	public static Workbook produceHSSFWorkbookWithJXLS(
			InputStream inpuntStream, Map data) {
		if (inpuntStream == null) {
			throw new NullPointerException("模板文件为空");
		}
		try {
			return new XLSTransformer().transformXLS(inpuntStream, data);
		}
		catch (ParsePropertyException e) {
			throw new RuntimeException(e);
		}
		catch (InvalidFormatException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String args[]) throws ParsePropertyException,
			IOException {

		// 一个例子
		// ProjectInfoVO vo = new ProjectInfoVO();
		// vo.setPro_name("name");
		// Map dataMap = new HashMap();
		// dataMap.put("project", vo);
		//
		// XLSTransformer transformer = new XLSTransformer();
		// // transformer.t
		// transformer.transformXLS("C:\\tzgs.xls", dataMap, "c:\\tzgsNew.xls");

		// URL url =
		// JXLSTemplateUtils.class.getClassLoader().getResource("conf/xls/fileCatalog.xls");

		//

		// HSSFWorkbook worbook = transformer.transformXLS(new
		// FileInputStream("C:\\文件目录导出格式.xls"), beans);
		// FileOutputStream output = new FileOutputStream("C:\\dest.xls");
		// worbook.write(output);
		// 直接输出
		// Map beans = new HashMap();
		// Company company = new Company();
		// company.setName("公司名称");
		// beans.put("company", company);
		// XLSTransformer transformer = new XLSTransformer();
		// transformer.transformXLS("C:\\1.xls", beans, "c:\\2.xls");

	}

	public static class Company {

		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
