package com.weixin.protocol.ssl;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import com.weixin.protocol.message.util.WeixinConfig;

public class HttpClientUtils {

	public static HttpClient httpClient = HttpClientConnectionManager
			.getSSLInstance();

	public static String request(String url) throws Exception {
		HttpGet get = HttpClientConnectionManager.getGetMethod(url);
		HttpResponse response = httpClient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		return jsonStr;
	}
	
	public static String post(String accessToken,String menuContent) throws Exception {
		 HttpPost httpost = HttpClientConnectionManager.getPostMethod(WeixinConfig.MENUURL + accessToken);  
	     httpost.setEntity(new StringEntity(menuContent, "UTF-8"));  
	     HttpResponse response = httpClient.execute(httpost);  
	     String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");  
	     
	     return jsonStr;
	}
	
	

	public static void main(String[] args) throws Exception {
		System.out.println(request(WeixinConfig.TOKENURL));
	}
}
