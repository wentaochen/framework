package com.weixin.protocol.message.util;

public class WeixinConfig {

	public static final String TOKEN = "chenwentao";

	public static final String ID = "gh_29fe529d3399";

	public static final String APPID = "wxee693cde0ee3b381";

	public static final String APPSECRET = "6bb56742710d7eb26a354618e9202474";

	public static final String TOKENURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
			+ WeixinConfig.APPID + "&secret=" + WeixinConfig.APPSECRET;

	public static final String MENUURL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";

	// 凭证获取（GET）
	public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	// 菜单创建（POST）
	public final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	// 菜单删除（GET）
	public final static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	// 菜单查询（GET）
	public final static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	public static String getOauthBase(String url) {
		StringBuilder sb = new StringBuilder();
		sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=")
				.append(WeixinConfig.APPID);
		sb.append("&redirect_uri=")
				.append(url)
				.append("&response_type=code&scope=snsapi_base&state=chen#wechat_redirect");

		return sb.toString();
	}

}
