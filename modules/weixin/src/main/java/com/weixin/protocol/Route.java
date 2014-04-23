/**
 * 
 */
package com.weixin.protocol;

import java.util.Map;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.weixin.domain.service.shop.WeixinService;
import com.weixin.protocol.handler.Handler;
import com.weixin.protocol.handler.HandlerMapper;

/**
 * @author chenwentao
 * 
 */
@Component
public class Route {

	private final static Logger LOG = Logger.getLogger(Route.class
			.getSimpleName());

	@Inject
	private HandlerMapper handlerMapper;

	@Inject
	private WeixinService weixinService;

	/**
	 * 存储数据的Map转换分发到相应的Service进行处理
	 * 
	 * @param map
	 *            存储数据的map
	 * @return 返回对应Message对象
	 */
	public String dispatch(Map<String, String> requestMap) {
		if (requestMap == null) {
			throw new NullPointerException("解析微信Request的请求的map为空");
		}

		String fromUserName = requestMap.get("FromUserName"); // 发送方帐号
		String toUserName = requestMap.get("ToUserName");// 开发者微信号
		String msgType = requestMap.get("MsgType");// 消息类型

		Handler handler = handlerMapper.getProtocol(msgType);
		if (handler != null) {
			return handler.process(requestMap);
		}

		return weixinService.replyDefalut(fromUserName, toUserName);

		// if (StringUtils.equals(msgType, Type.Request.REQ_MESSAGE_TYPE_TEXT))
		// {
		// RequestTextMessage textMessage = new RequestTextMessage(requestMap);
		// ResponseTextMessage resp = new ResponseTextMessage();
		// resp.setToUserName(textMessage.getFromUserName());
		// resp.setFromUserName(textMessage.getToUserName());
		// resp.setCreateTime(new Date().getTime());
		// resp.setMsgType(Type.Response.RESP_MESSAGE_TYPE_TEXT);
		// String url =
		// MenuUtil.urlEncodeUTF8("http://www.sharev.org/wx/oauth");
		// StringBuilder sb = new StringBuilder();
		// sb.append("<a href=\"");
		// sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=")
		// .append(WeixinConfig.APPID);
		// sb.append("&redirect_uri=")
		// .append(url)
		// .append("&response_type=code&scope=snsapi_base&state=chen#wechat_redirect");
		// sb.append("\">点击进入注册!!!</a>");
		// System.out.println(sb);
		// resp.setContent(sb.toString());
		// return resp.toXml();
		//
		// //return weixinService.reply(textMessage);
		// }
		//
		// if (StringUtils.equals(msgType, Type.Event.REQ_MESSAGE_TYPE_EVENT)) {
		// String Event=requestMap.get("Event");
		// if(StringUtils.equals(Event, Type.Event.EVENT_TYPE_SUBSCRIBE)){
		// SubscribeEvent event = new SubscribeEvent(requestMap);
		// return weixinService.reply(event);
		// }
		//
		// if(StringUtils.equals(Event, Type.Event.EVENT_TYPE_UNSUBSCRIBE)){
		// UnSubscribeEvent event = new UnSubscribeEvent(requestMap);
		// return weixinService.reply(event);
		// }
		//
		// if(StringUtils.equals(Event, Type.Event.EVENT_TYPE_SCAN)){
		// QRCodeEvent event = new QRCodeEvent(requestMap);
		// return weixinService.reply(event);
		// }
		//
		// if(StringUtils.equals(Event, Type.Event.EVENT_TYPE_LOCATION)){
		// LocationEvent event = new LocationEvent(requestMap);
		// return weixinService.reply(event);
		// }
		//
		// if(StringUtils.equals(Event, Type.Event.EVENT_TYPE_CLICK)){
		// MenuEvent event = new MenuEvent(requestMap);
		// return weixinService.reply(event);
		// }
		//
		//
		// }
	}
}
