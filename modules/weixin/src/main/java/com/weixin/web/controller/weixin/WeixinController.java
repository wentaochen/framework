package com.weixin.web.controller.weixin;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.protocol.Route;
import com.weixin.protocol.message.others.WeixinOauth2Token;
import com.weixin.protocol.message.util.AdvancedUtil;
import com.weixin.protocol.message.util.SignUtil;
import com.weixin.protocol.message.util.WeixinConfig;
import com.weixin.protocol.message.util.XmlUtils;

@Controller
@RequestMapping
public class WeixinController {

	private final static boolean DEBUG = true;

	@Inject
	private Route route;

	// 微信公众平台验证url是否有效使用的接口
	@RequestMapping(value = "/auth", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String auth(HttpServletRequest request) {
		// return checkWeixinRequest(request);
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		// 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			return echostr;
		}
		return null;
	}

	// 接收微信公众号接收的消息，处理后再做相应的回复
	@RequestMapping(value = "/auth", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String replyMessage(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO 仅处理微信服务端发的请求
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		// 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			System.out.println("");
		}

		Map<String, String> requestMap = XmlUtils.parseXml(request
				.getInputStream());

		String responseXml = route.dispatch(requestMap);

		return responseXml;
	}

	@RequestMapping(value = "/oauth")
	@ResponseBody
	public String oauth(HttpServletRequest request) {
		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");

		// 用户同意授权
		if (!"authdeny".equals(code)) {
			// 获取网页授权access_token
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil
					.getOauth2AccessToken(WeixinConfig.APPID,
							WeixinConfig.APPSECRET, code);
			// 网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getAccessToken();
			// 用户标识
			String openId = weixinOauth2Token.getOpenId();
			String url = String.format(
					com.weixin.protocol.ssl.ResponseUtils.REDIRECT_URL,
					"/binding/" + openId, openId);

			return openId;
			// TODO 后期需要对获得用户信息的情况处理
			// 获取用户信息
			// SNSUserInfo snsUserInfo =
			// AdvancedUtil.getSNSUserInfo(accessToken, openId);
			// 设置要传递的参数
			// request.setAttribute("snsUserInfo", snsUserInfo);
			/**/
		} else {
			// TODO 后期需要对不同意授权的用户进行处理
			return null;
		}

	}
}
