//package com.weixin.web;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpMethod;
//import org.apache.commons.httpclient.URIException;
//import org.apache.commons.httpclient.methods.GetMethod;
//import org.apache.commons.httpclient.params.HttpClientParams;
//import org.apache.commons.httpclient.util.URIUtil;
//
///**
// * Java版本示例代码，使用见{@link DemoApiToolTest.java}
// * <p>
// * 
// * @author : xiaopeng.li
// *         <p>
// * @version 1.0 2013-1-23
// * 
// * @since dianping-java-samples 1.0
// */
//public class DemoApiTool {
//	public static void main(String[] args) throws Exception {
//		String apiUrl = "http://api.dianping.com/v1/business/find_businesses";
//		String appKey = "39614282";
//		String secret = "7d59276250c14d38b42f77d7c69bdac0";
//		Map<String, String> paramMap = new HashMap<String, String>();
//		paramMap.put("city", "武汉");
//		paramMap.put("latitude", "30.47921");
//		paramMap.put("longitude", "114.40721");
//		paramMap.put("offset_type", "0");
//		paramMap.put("category", "美食");
//		paramMap.put("limit", "40");
//		// paramMap.put("category", "美食");
//		// paramMap.put("region", "长宁区");
//		// paramMap.put("limit", "20");
//		// paramMap.put("radius", "2000");
//		// paramMap.put("has_coupon", "1");
//		// paramMap.put("has_deal", "1");
//		// paramMap.put("keyword", "泰国菜");
//		// paramMap.put("sort", "7");
//		// paramMap.put("format", "json");
//
//		StringBuilder stringBuilder = new StringBuilder();
//
//		// 对参数名进行字典排序
//		String[] keyArray = paramMap.keySet().toArray(new String[0]);
//		Arrays.sort(keyArray);
//		// 拼接有序的参数名-值串
//		stringBuilder.append(appKey);
//		for (String key : keyArray) {
//			stringBuilder.append(key).append(paramMap.get(key));
//		}
//		String codes = stringBuilder.append(secret).toString();
//		// SHA-1编码，
//		// 这里使用的是Apache-codec，即可获得签名(shaHex()会首先将中文转换为UTF8编码然后进行sha1计算，使用其他的工具包请注意UTF8编码转换)
//		String sign = org.apache.commons.codec.digest.DigestUtils.shaHex(codes)
//				.toUpperCase();
//
//		// 添加签名
//		stringBuilder = new StringBuilder();
//		stringBuilder.append("appkey=").append(appKey).append("&sign=")
//				.append(sign);
//		for (Entry<String, String> entry : paramMap.entrySet()) {
//			stringBuilder.append('&').append(entry.getKey()).append('=')
//					.append(entry.getValue());
//		}
//		String queryString = stringBuilder.toString();
//
//		StringBuffer response = new StringBuffer();
//		HttpClientParams httpConnectionParams = new HttpClientParams();
//		httpConnectionParams.setConnectionManagerTimeout(1000);
//		HttpClient client = new HttpClient(httpConnectionParams);
//		HttpMethod method = new GetMethod(apiUrl);
//
//		BufferedReader reader = null;
//		String encodeQuery = URIUtil.encodeQuery(queryString, "UTF-8"); // UTF-8
//																		// 请求
//		method.setQueryString(encodeQuery);
//		client.executeMethod(method);
//		reader = new BufferedReader(new InputStreamReader(
//				method.getResponseBodyAsStream(), "UTF-8"));
//		String line = null;
//		while ((line = reader.readLine()) != null) {
//			response.append(line).append(System.getProperty("line.separator"));
//		}
//		reader.close();
//		method.releaseConnection();
//
//		System.out.println(apiUrl + "?" + encodeQuery);
//
//		System.out.println(response.toString());
//	}
//
//	public static String search(String latitube, String longitube,String type)
//			throws Exception {
//		String apiUrl = "http://api.dianping.com/v1/business/find_businesses";
//		String appKey = "39614282";
//		String secret = "7d59276250c14d38b42f77d7c69bdac0";
//		Map<String, String> paramMap = new HashMap<String, String>();
//		paramMap.put("city", "武汉");
//		paramMap.put("latitude", latitube);
//		paramMap.put("longitude", longitube);
//		paramMap.put("offset_type", "0");
//		if(type!=""){
//			paramMap.put("category", type);
//		}
//		paramMap.put("limit", "20");
//		paramMap.put("radius", "5000");
//		// paramMap.put("category", "美食");
//		// paramMap.put("region", "长宁区");
//		// paramMap.put("limit", "20");
//		// paramMap.put("radius", "2000");
//		// paramMap.put("has_coupon", "1");
//		// paramMap.put("has_deal", "1");
//		// paramMap.put("keyword", "泰国菜");
//		// paramMap.put("sort", "7");
//		// paramMap.put("format", "json");
//
//		StringBuilder stringBuilder = new StringBuilder();
//
//		// 对参数名进行字典排序
//		String[] keyArray = paramMap.keySet().toArray(new String[0]);
//		Arrays.sort(keyArray);
//		// 拼接有序的参数名-值串
//		stringBuilder.append(appKey);
//		for (String key : keyArray) {
//			stringBuilder.append(key).append(paramMap.get(key));
//		}
//		String codes = stringBuilder.append(secret).toString();
//		// SHA-1编码，
//		// 这里使用的是Apache-codec，即可获得签名(shaHex()会首先将中文转换为UTF8编码然后进行sha1计算，使用其他的工具包请注意UTF8编码转换)
//		String sign = org.apache.commons.codec.digest.DigestUtils.shaHex(codes)
//				.toUpperCase();
//
//		// 添加签名
//		stringBuilder = new StringBuilder();
//		stringBuilder.append("appkey=").append(appKey).append("&sign=")
//				.append(sign);
//		for (Entry<String, String> entry : paramMap.entrySet()) {
//			stringBuilder.append('&').append(entry.getKey()).append('=')
//					.append(entry.getValue());
//		}
//		String queryString = stringBuilder.toString();
//
//		StringBuffer response = new StringBuffer();
//		HttpClientParams httpConnectionParams = new HttpClientParams();
//		httpConnectionParams.setConnectionManagerTimeout(1000);
//		HttpClient client = new HttpClient(httpConnectionParams);
//		HttpMethod method = new GetMethod(apiUrl);
//
//		BufferedReader reader = null;
//		String encodeQuery = URIUtil.encodeQuery(queryString, "UTF-8"); // UTF-8
//																		// 请求
//		method.setQueryString(encodeQuery);
//		client.executeMethod(method);
//		reader = new BufferedReader(new InputStreamReader(
//				method.getResponseBodyAsStream(), "UTF-8"));
//		String line = null;
//		while ((line = reader.readLine()) != null) {
//			response.append(line).append(System.getProperty("line.separator"));
//		}
//		reader.close();
//		method.releaseConnection();
//
//		return response.toString();
//	}
//}
