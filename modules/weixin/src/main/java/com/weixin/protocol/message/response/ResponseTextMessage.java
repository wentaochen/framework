package com.weixin.protocol.message.response;

import com.weixin.protocol.message.util.ResponseUtils;

/**
 * 文本消息
 * 
 * @author liufeng
 * @date 2013-09-11
 */
public class ResponseTextMessage extends ResponseBaseMessage {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	@Override
	public String toXml() {
		return ResponseUtils.messageToXml(this);
	}
}
