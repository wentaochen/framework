package com.framework.weixin.message.request;

/**
 * 文本消息
 * 
 * @date 2013-05-19
 */
public class TextMessage extends BaseMessage {
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}