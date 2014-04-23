package com.weixin.protocol.message.request;

import java.util.Map;

/**
 * 文本消息
 * 
 * @author liufeng
 * @date 2013-09-11
 */
public class RequestTextMessage extends RequestBaseMessage {
	// 消息内容
	private String Content;

	public RequestTextMessage(Map<String, String> map) {
		super(map);
		this.Content = map.get("Content");
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
