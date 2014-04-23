package com.weixin.protocol.message.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weixin.protocol.message.menu.Button;
import com.weixin.protocol.message.menu.ClickButton;
import com.weixin.protocol.message.menu.ComplexButton;
import com.weixin.protocol.message.menu.Menu;
import com.weixin.protocol.message.menu.ViewButton;

/**
 * 自定义菜单工具类
 * 
 * @author liufeng
 * @date 2013-10-17
 */
public class MenuUtil {
	
	private static Logger log = LoggerFactory.getLogger(MenuUtil.class);

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            菜单实例
	 * @param accessToken
	 *            凭证
	 * @return true成功 false失败
	 */
	public static boolean createMenu(Menu menu, String accessToken) {
		boolean result = false;
		String url = WeixinConfig.menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		System.out.println(jsonMenu);
		// 发起POST请求创建菜单
		JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				result = false;
				log.error("创建菜单失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}

		return result;
	}

	/**
	 * 查询菜单
	 * 
	 * @param accessToken
	 *            凭证
	 * @return
	 */
	public static String getMenu(String accessToken) {
		String result = null;
		String requestUrl = WeixinConfig.menu_get_url.replace("ACCESS_TOKEN", accessToken);
		// 发起GET请求查询菜单
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			result = jsonObject.toString();
		}
		return result;
	}

	/**
	 * 删除菜单
	 * 
	 * @param accessToken
	 *            凭证
	 * @return true成功 false失败
	 */
	public static boolean deleteMenu(String accessToken) {
		boolean result = false;
		String requestUrl = WeixinConfig.menu_delete_url
				.replace("ACCESS_TOKEN", accessToken);
		// 发起GET请求删除菜单
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				result = false;
				log.error("删除菜单失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}

	/**
	 * 定义菜单结构
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		ClickButton btn11 = new ClickButton();
		btn11.setName("开源中国");
		btn11.setType("click");
		btn11.setKey("oschina");

		ClickButton btn12 = new ClickButton();
		btn12.setName("ITeye");
		btn12.setType("click");
		btn12.setKey("iteye");

		ViewButton btn13 = new ViewButton();
		btn13.setName("CocoaChina");
		btn13.setType("view");
		btn13.setUrl("http://www.iteye.com");

		ViewButton btn21 = new ViewButton();
		btn21.setName("我要注册");
		btn21.setType("view");
		
		String oauthUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%sresponse_type=code&scope=snsapi_base&state=123#wechat_redirect";
		String url=urlEncodeUTF8("http://cwt.sharev.org/wx/oauth");
		String.format(oauthUrl, WeixinConfig.APPID,url);
		
		btn21.setUrl("http://m.taobao.com");

		ViewButton btn22 = new ViewButton();
		btn22.setName("京东");
		btn22.setType("view");
		btn22.setUrl("http://m.jd.com");

		ViewButton btn23 = new ViewButton();
		btn23.setName("唯品会");
		btn23.setType("view");
		btn23.setUrl("http://m.vipshop.com");

		ViewButton btn24 = new ViewButton();
		btn24.setName("当当网");
		btn24.setType("view");
		btn24.setUrl("http://m.dangdang.com");

		ViewButton btn25 = new ViewButton();
		btn25.setName("苏宁易购");
		btn25.setType("view");
		btn25.setUrl("http://m.suning.com");

		ViewButton btn31 = new ViewButton();
		btn31.setName("注册");
		btn31.setType("view");
		btn31.setUrl("http://www.duopao.com");

		ViewButton btn32 = new ViewButton();
		btn32.setName("一窝88");
		btn32.setType("view");
		StringBuilder sb=new StringBuilder();
		sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=")
		.append(WeixinConfig.APPID);
		sb.append("&redirect_uri=")
		.append(url)
		.append("&response_type=code&scope=snsapi_base&state=chen#wechat_redirect");
		btn32.setUrl("http://www.yi588.com");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("美食");
		mainBtn1.setSub_button(new Button[] { btn11 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("我的");
		mainBtn2.setSub_button(new Button[] { btn21});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("优惠");
		mainBtn3.setSub_button(new Button[] { btn31});

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}

	public static String urlEncodeUTF8(String url) {
		try {
			return URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static void main(String[] args) {
		String oauthUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		String url=urlEncodeUTF8("http://cwt.sharev.org/wx/oauth");
		url =String.format(oauthUrl, WeixinConfig.APPID,url);
		
		// 第三方用户唯一凭证
		String appId = WeixinConfig.APPID;
		// 第三方用户唯一凭证密钥
		String appSecret = WeixinConfig.APPSECRET;

		// 调用接口获取凭证
		Token token = CommonUtil.getToken(appId, appSecret);

		if (null != token) {
			// 创建菜单
			boolean result = MenuUtil.createMenu(getMenu(),
					token.getAccessToken());

			// 判断菜单创建结果
			if (result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败！");
		}
	}
}