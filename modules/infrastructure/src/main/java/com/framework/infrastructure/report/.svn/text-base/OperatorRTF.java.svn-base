package com.framework.infrastructure.report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.framework.infrastructure.utils.ClassLoaderUtil;

public class OperatorRTF {

	/**
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String getRTFToString(String path) throws IOException {
		InputStream input = ClassLoaderUtil
				.getResource(path, OperatorRTF.class).openStream();
		byte[] b = new byte[1024];
		StringBuilder content = new StringBuilder("");
		while (true) {
			int bytesRead = input.read(b, 0, 1024);
			if (bytesRead == -1) {
				break;
			}
			content.append(new String(b, 0, bytesRead));
		}
		return content.toString();
	}

	/**
	 * @param source
	 * @param data
	 * @return
	 */
	public static String relpaceKeyToValue(String source,
			Map<String, String> data) {
		for (Map.Entry<String, String> entry : data.entrySet()) {
			source = source.replace(entry.getKey(), entry.getValue());
		}
		return source;

	}

	public static void main(String[] args) throws Exception {

		Map<String, String> datas = new HashMap<String, String>();
		datas.put("$project$", "我的项目");
		datas.put("$service$", "我的服务");
		datas.put("$address$", "我的地址");
		datas.put("$address$", "我的地址");
		datas.put("$person$", "我的地址");
		datas.put("$month$", "8月");
		String source = getRTFToString("D:\\Project\\source\\CMC\\config\\template\\rtf\\jjfa.rtf");
		String target = relpaceKeyToValue(source, datas);

		FileOutputStream outputStream = new FileOutputStream(
				"D:\\Project\\source\\CMC\\config\\template\\rtf\\jjfaTest.rtf");
		outputStream.write(target.getBytes());
		outputStream.flush();
		outputStream.close();

		// FileWriter fw = new FileWriter(
		// "D:\\Project\\source\\CMC\\config\\template\\rtf\\jjfaTest.rtf",
		// false);
		// PrintWriter out = new PrintWriter(fw);
		// out.println(target);
		// out.close();
		// fw.close();

		// $idea$ $info$
		// // 读取文件
		// InputStream ins = new FileInputStream(
		// "D:\\Project\\source\\CMC\\config\\template\\rtf\\jjfa.rtf");
		// byte[] b = new byte[1024];
		// StringBuilder sourcecontent = new StringBuilder("");
		// while (true) {
		// int bytesRead = ins.read(b, 0, 1024);
		// if (bytesRead == -1) {
		// System.out.println("读取模板文件结束");
		// break;
		// }
		//
		// sourcecontent.append(new String(b, 0, bytesRead));
		// }
		//
		// // 转换 strToRtf
		// String content = "陈文韬";
		// String result = strToRtfNew(content);
		// // String point = strToRtfNew("$timetop$");
		//
		// // 替换
		// // String target = sourcecontent.replaceAll("tihuana", content);
		// // target = target.replaceAll("klcwt", "dddssddssd");
		// //
		// String target = sourcecontent.toString().replace("$name$", content);
		// target = target.replace("$service$", "klcwt公司");
		//
		// System.out.println("是否找到这个数:" + sourcecontent.indexOf("comanyName"));
		// System.out.println("替换是否相等:" + target.equals(sourcecontent));
		//
		// // /* 结果输出保存到文件 */
		// // File file = new File(
		// // "D:\\Project\\source\\CMC\\config\\template\\rtf\\jjfaTest.rtf");
		// // if (file.exists()) {
		// // file.delete();
		// // }
		// FileWriter fw = new FileWriter(
		// "D:\\Project\\source\\CMC\\config\\template\\rtf\\jjfaTest.rtf",
		// false);
		// PrintWriter out = new PrintWriter(fw);
		// // System.out.println(target);
		// if (target.equals("") || target == "") {
		// out.println(sourcecontent);
		// } else {
		// out.println(target);
		// }
		// out.close();
		// fw.close();
		// System.out.println(" 成功");

	}

	//	
	// /**
	// * 字符串转换为rtf编码
	// *
	// * @param content
	// * @return
	// */
	// public String strToRtf(String content) {
	//
	// try {
	// System.out.println(content);
	// content = new String(content.getBytes(), "GB2312");
	// System.out.println(content);
	// } catch (UnsupportedEncodingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// char[] digital = "0123456789ABCDEF".toCharArray();
	// StringBuffer sb = new StringBuffer("");
	// byte[] bs = null;
	// // bs = content.getBytes();
	// try {
	// bs = content.getBytes("unicode");
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// }
	//
	// int bit;
	// for (int i = 0; i < bs.length; i++) {
	// bit = (bs[i] & 0x0f0) >> 4;
	// sb.append("\\'");
	// sb.append(digital[bit]);
	// bit = bs[i] & 0x0f;
	// sb.append(digital[bit]);
	// }
	// return sb.toString();
	// }
	//
	// /**
	// * 替换文档的可变部分
	// *
	// * @param content
	// * @param replacecontent
	// * @param flag
	// * @return
	// */
	// public String replaceRTF(String content, String replacecontent, int flag)
	// {
	// String rc = strToRtf(replacecontent);
	// String target = "";
	// System.out.println(rc);
	// if (flag == 0) {
	// target = content.replace("$timetop$", rc);
	// }
	// if (flag == 1) {
	// target = content.replace("$info$", rc);
	// }
	// if (flag == 2) {
	// target = content.replace("$idea$", rc);
	// }
	// if (flag == 3) {
	// target = content.replace("$advice$", rc);
	// }
	// if (flag == 4) {
	// target = content.replace("$infosend$", rc);
	// }
	// return target;
	// }
	//
	// /**
	// * 获取文件路径
	// *
	// * @param flag
	// * @return
	// */
	// public String getSavePath() {
	//
	// String path = "C:\\";
	// File fDirecotry = new File(path);
	// if (!fDirecotry.exists()) {
	// fDirecotry.mkdirs();
	// }
	// return path;
	// }
	//
	// /**
	// * 半角转为全角
	// */
	// public String ToSBC(String input) {
	// char[] c = input.toCharArray();
	// for (int i = 0; i < c.length; i++) {
	// if (c[i] == 32) {
	// c[i] = (char) 12288;
	// continue;
	// }
	// if (c[i] < 127) {
	// c[i] = (char) (c[i] + 65248);
	// }
	// }
	// return new String(c);
	// }
	//
	// public void rgModel(String sourceName, String username, String content) {
	// /* 构建生成文件名 targetname:12时10分23秒_username_记录.rtf */
	// Date current = new Date();
	//
	// SimpleDateFormat sdf = new java.text.SimpleDateFormat(
	// "yyyy-MM-dd HH:mm:ss");
	//
	// String targetname = sdf.format(current).substring(11, 13) + "时";
	// targetname += sdf.format(current).substring(14, 16) + "分";
	// targetname += sdf.format(current).substring(17, 19) + "秒";
	// targetname += "_" + username + "_记录.doc";
	//
	// /* 字节形式读取模板文件内容,将结果转为字符串 */
	// String strpath = getSavePath();
	// String sourcecontent = "";
	// InputStream ins = null;
	// try {
	// ins = new FileInputStream(sourceName);
	// byte[] b = new byte[1024];
	// if (ins == null) {
	// System.out.println("源模板文件不存在");
	// }
	// int bytesRead = 0;
	// while (true) {
	// bytesRead = ins.read(b, 0, 1024); // return final read bytes
	// // counts
	// if (bytesRead == -1) {// end of InputStream
	// System.out.println("读取模板文件结束");
	// break;
	// }
	// // convert to string using bytes
	// sourcecontent += new String(b, 0, bytesRead);
	//
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// /* 修改变化部分 */
	// String targetcontent = "";
	// /**
	// * 拆分之后的数组元素与模板中的标识符对应关系 array[0]:timetop array[1]:info array[2]:idea
	// * array[3]:advice array[4]:infosend
	// */
	// String array[] = content.split("~");
	//
	// // 2008年11月27日：更新模板之后时间无需自动填充
	// for (int i = 0; i < array.length; i++) {
	// if (i == 0) {
	// targetcontent = replaceRTF(sourcecontent, array[i], i);
	// } else {
	// targetcontent = replaceRTF(targetcontent, array[i], i);
	// }
	// }
	// /* 结果输出保存到文件 */
	// try {
	// FileWriter fw = new FileWriter(getSavePath() + "\\" + targetname,
	// true);
	// PrintWriter out = new PrintWriter(fw);
	// System.out.println(targetcontent);
	// if (targetcontent.equals("") || targetcontent == "") {
	// out.println(sourcecontent);
	// } else {
	// out.println(targetcontent);
	// }
	// out.close();
	// fw.close();
	// System.out.println(getSavePath() + " 该目录下生成文件" + targetname
	// + " 成功");
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// private static String strToRtfNew(String content)
	// throws UnsupportedEncodingException {
	// System.out.println(content);
	// content = new String(content.getBytes(), "GB2312");
	// System.out.println(content);
	//
	// char[] digital = "0123456789ABCDEF".toCharArray();
	// StringBuffer sb = new StringBuffer("");
	// byte[] bs = null;
	// // bs = content.getBytes();
	// try {
	// bs = content.getBytes("unicode");
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// }
	//
	// int bit;
	// for (int i = 0; i < bs.length; i++) {
	// bit = (bs[i] & 0x0f0) >> 4;
	// sb.append("\\'");
	// sb.append(digital[bit]);
	// bit = bs[i] & 0x0f;
	// sb.append(digital[bit]);
	// }
	// String result = sb.toString();
	// return result;
	// }
}
