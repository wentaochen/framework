package com.weixin.protocol.ssl;

import com.weixin.domain.model.shop.Member;

public class ResponseUtils {

	//public static final String url = "http://www.wrisc.cn/wx/redirect?forward=%s&openid=%s";
	
	public static final String REDIRECT_URL = "http://cwt.sharev.org/weixin/redirect?forward=%s&openid=%s";

	/**
	 * 进入注册页面
	 * 
	 * @return 主菜单字符信息
	 */
	public static String getForRegister(String fromUserName) {
		String newUrl = String.format(REDIRECT_URL, "/binding/" + fromUserName,
				fromUserName);

		StringBuffer buffer = new StringBuffer();
		buffer.append("您好，环境光临晴天铺子，请进入完成会员注册：").append("\n\n");
		buffer.append("<a href=\"" + newUrl + "\">点击进入注册!!!</a>")
				.append("\n\n");
		return buffer.toString();
	}

	/**
	 * 显示产品页面
	 * @param openid 
	 * 
	 * @return 主菜单字符信息
	 */
	public static String getForShowProduct(Member member, String openid) {
		String newUrl = String.format(REDIRECT_URL, "/product/list", openid);
		String respContent;
		if (member !=null){
			 respContent = "尊敬的会员" + member.getName() + "欢迎您!";
		}else {
			 respContent = "尊敬的新用户欢迎您!，请到本店铺进行选购哦！";
		}
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(respContent).append("\n\n");
		buffer.append("<a href=\"" + newUrl + "\">查看最新产品！</a>")
				.append("\n\n");
		return buffer.toString();
	}
	
	
	
	/**
	 * 显示产品页面
	 * @param openid 
	 * 
	 * @return 主菜单字符信息
	 */
	public static String getForOrder(Member member, String openid) {
		String newUrl = String.format(REDIRECT_URL, "/order/list", openid);
		
		String respContent;
		if (member !=null){
			 respContent = "尊敬的会员" + member.getName() + "欢迎您!";
		}else {
			 respContent = "尊敬的新用户欢迎您!，请到本店铺进行选购哦！";
		}

		StringBuffer buffer = new StringBuffer();
		buffer.append(respContent).append("\n\n");
		buffer.append("<a href=\"" + newUrl + "\">查看订单！</a>")
				.append("\n\n");
		return buffer.toString();
	}
	
	/**
	 * 显示产品页面
	 * @param openid 
	 * 
	 * @return 主菜单字符信息
	 */
	public static String getForMember(Member member, String openid) {
		String newUrl = String.format(REDIRECT_URL, "/member/", openid);
		
		String respContent;
		if (member !=null){
			 respContent = "尊敬的会员" + member.getName() + "欢迎您!";
		}else {
			 respContent = "尊敬的新用户欢迎您!，请到本店铺进行选购哦！";
		}

		StringBuffer buffer = new StringBuffer();
		buffer.append(respContent).append("\n\n");
		buffer.append("<a href=\"" + newUrl + "\">进入会员中心！</a>")
				.append("\n\n");
		return buffer.toString();
	}

	// public static final String url =
	// "http://cwt.sharev.org/wx/redirect?forward=%s&openid=%s";

	public static void main(String[] args) {

		System.out.println();
		;
	}

	/**
	 * 获得主菜单
	 * 
	 * @return 主菜单字符信息
	 */
	public static String getMainMenu() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("您好，环境光临晴天铺子，请回复数字选择服务：").append("\n\n");
		buffer.append("1  注册会员 ").append("\n");
		buffer.append("2  产品查看").append("\n");
		buffer.append("3  订单查询").append("\n");
		buffer.append("4  个人中心").append("\n\n");
		buffer.append("回复“?”显示此帮助菜单");
		return buffer.toString();
	}

	public static String getReadyRegister(Member member) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("您好，").append(member.getName()).append("\n\n");
		buffer.append("输入【2】 产品查看").append("\n\n");
		return buffer.toString();
	}

}
