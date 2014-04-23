package com.weixin.domain.service.shop;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.domain.model.shop.Member;
import com.weixin.protocol.message.event.LocationEvent;
import com.weixin.protocol.message.event.MenuEvent;
import com.weixin.protocol.message.event.QRCodeEvent;
import com.weixin.protocol.message.event.SubscribeEvent;
import com.weixin.protocol.message.event.UnSubscribeEvent;
import com.weixin.protocol.message.request.RequestTextMessage;
import com.weixin.protocol.message.response.ResponseTextMessage;
import com.weixin.protocol.message.util.MenuUtil;
import com.weixin.protocol.message.util.Type;
import com.weixin.protocol.message.util.WeixinConfig;
import com.weixin.protocol.ssl.ResponseUtils;

@Service("weixinService")
@Transactional
public class WeixinService {

	@Inject
	private MemberService memberService;

	public String reply(RequestTextMessage request) {
		String text = request.getContent();
		Member member = memberService.findByOpenid(request.getFromUserName());
		// 绑定处理
		if (text.equals("1")) {

			if (member == null) {
				ResponseTextMessage textMessage = new ResponseTextMessage();
				textMessage.setToUserName(request.getFromUserName());
				textMessage.setFromUserName(request.getToUserName());
				textMessage.setCreateTime(new Date().getTime());
				textMessage.setMsgType(Type.Response.RESP_MESSAGE_TYPE_TEXT);
				textMessage.setContent(ResponseUtils.getForRegister(request
						.getFromUserName()));
				return textMessage.toXml();
			} else {
				ResponseTextMessage textMessage = new ResponseTextMessage();
				textMessage.setToUserName(request.getFromUserName());
				textMessage.setFromUserName(request.getToUserName());
				textMessage.setCreateTime(new Date().getTime());
				textMessage.setMsgType(Type.Response.RESP_MESSAGE_TYPE_TEXT);
				textMessage.setContent(ResponseUtils.getForShowProduct(member,
						request.getFromUserName()));
				return textMessage.toXml();

			}
		} else if (text.equals("2")) {
			ResponseTextMessage textMessage = new ResponseTextMessage();
			textMessage.setToUserName(request.getFromUserName());
			textMessage.setFromUserName(request.getToUserName());
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(Type.Response.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setContent(ResponseUtils.getForShowProduct(member,
					request.getFromUserName()));
			return textMessage.toXml();

		} else if (text.equals("3")) {
			ResponseTextMessage textMessage = new ResponseTextMessage();
			textMessage.setToUserName(request.getFromUserName());
			textMessage.setFromUserName(request.getToUserName());
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(Type.Response.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setContent(ResponseUtils.getForOrder(member,
					request.getFromUserName()));
			return textMessage.toXml();

		} else if (text.equals("4")) {
			ResponseTextMessage textMessage = new ResponseTextMessage();
			textMessage.setToUserName(request.getFromUserName());
			textMessage.setFromUserName(request.getToUserName());
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(Type.Response.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setContent(ResponseUtils.getForMember(member,
					request.getFromUserName()));
			return textMessage.toXml();
		}

		ResponseTextMessage textMessage = new ResponseTextMessage();
		textMessage.setToUserName(request.getFromUserName());
		textMessage.setFromUserName(request.getToUserName());
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(Type.Response.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent("您发送的请求正在开发中，请耐心等待!");
		return textMessage.toXml();
	}

	/**
	 * 返回默认消息
	 * 
	 * @param requestMessage
	 * @return
	 */
	public String replyDefalut(String fromUser, String toUser) {
		ResponseTextMessage textMessage = new ResponseTextMessage();
		textMessage.setToUserName(fromUser);
		textMessage.setFromUserName(toUser);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(Type.Response.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent("您发送的请求正在开发中，请耐心等待!");
		return textMessage.toXml();
	}

	public static void main(String[] args) {
		String url = MenuUtil.urlEncodeUTF8("http://www.sharev.org/wx/oauth");
		StringBuilder sb = new StringBuilder();
		sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=")
				.append(WeixinConfig.APPID);
		sb.append("&redirect_uri=")
				.append(url)
				.append("&response_type=code&scope=SCOPE&state=STATE#wechat_redirect");
		System.out.println(sb);
	}

	public String reply(SubscribeEvent event) {
		String oauthUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		String url = MenuUtil.urlEncodeUTF8("http://www.sharev.org/wx/oauth");
		url = String.format(oauthUrl, WeixinConfig.APPID, url);

		StringBuffer buffer = new StringBuffer();
		buffer.append("您好，环境光临晴天铺子，请进入完成会员注册：").append("\n\n");
		buffer.append("<a href=\"" + url + "\">点击进入注册!!!</a>").append(url);

		ResponseTextMessage textMessage = new ResponseTextMessage();
		textMessage.setToUserName(event.getFromUserName());
		textMessage.setFromUserName(event.getToUserName());
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(Type.Response.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent(buffer.toString());
		return textMessage.toXml();
	}

	public String reply(UnSubscribeEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	public String reply(QRCodeEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	public String reply(LocationEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	public String reply(MenuEvent event) {
		// TODO Auto-generated method stub
		return null;
	}
}
