package com.weixin.protocol.handler;

import java.util.Date;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.weixin.domain.model.shop.Member;
import com.weixin.domain.service.shop.MemberService;
import com.weixin.protocol.message.request.RequestTextMessage;
import com.weixin.protocol.message.response.ResponseTextMessage;
import com.weixin.protocol.message.util.Type;
import com.weixin.protocol.ssl.ResponseUtils;

@Component(Type.Request.REQ_MESSAGE_TYPE_TEXT)
public class TextHandler implements Handler {

	@Inject
	private MemberService memberService;

	public String process(Map<String, String> map) {
		RequestTextMessage request = new RequestTextMessage(map);

		String text = request.getContent();
		Member member = memberService.findByOpenid(request.getFromUserName());
		// 绑定处理
		if (text.equals("1")) {
			if (member == null) {
				ResponseTextMessage textMessage = initReback(request);
				textMessage.setContent(ResponseUtils.getForRegister(request
						.getFromUserName()));
				return textMessage.toXml();
			} else {
				ResponseTextMessage textMessage = initReback(request);
				textMessage.setContent(ResponseUtils.getForShowProduct(member,
						request.getFromUserName()));
				return textMessage.toXml();

			}
		} else if (text.equals("2")) {
			ResponseTextMessage textMessage = initReback(request);
			textMessage.setContent(ResponseUtils.getForShowProduct(member,
					request.getFromUserName()));
			return textMessage.toXml();

		} else if (text.equals("3")) {
			ResponseTextMessage textMessage = initReback(request);
			textMessage.setContent(ResponseUtils.getForOrder(member,
					request.getFromUserName()));
			return textMessage.toXml();

		} else if (text.equals("4")) {
			ResponseTextMessage textMessage = initReback(request);
			textMessage.setContent(ResponseUtils.getForMember(member,
					request.getFromUserName()));
			return textMessage.toXml();
		}

		ResponseTextMessage textMessage = initReback(request);
		textMessage.setContent("您发送的请求正在开发中，请耐心等待!");
		return textMessage.toXml();
	}

	public ResponseTextMessage initReback(RequestTextMessage request) {
		ResponseTextMessage textMessage = new ResponseTextMessage();
		textMessage.setToUserName(request.getFromUserName());
		textMessage.setFromUserName(request.getToUserName());
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(Type.Response.RESP_MESSAGE_TYPE_TEXT);
		return textMessage;
	}

}
