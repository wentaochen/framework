package com.weixin.protocol.message.request;

import java.util.Map;

/**
 * 链接消息
 * 
 * @author liufeng
 * @date 2013-09-11
 */
public class RequestLinkMessage extends RequestBaseMessage {

	// 消息标题
	private String Title;
	// 消息描述
	private String Description;
	// 消息链接
	private String Url;

	public RequestLinkMessage(Map<String, String> map) {
		super(map);
		this.Title = map.get("Title");
		this.Description = map.get("Description");
		this.Url = map.get("Url");
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
}
