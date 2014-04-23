package com.weixin.protocol.message.request;

import java.util.Map;

/**
 * 图片消息
 * 
 * @author liufeng
 * @date 2013-09-11
 */
public class RequestImageMessage extends RequestBaseMessage {

	// 图片链接
	private String PicUrl;

	public RequestImageMessage(Map<String, String> map) {
		super(map);
		this.PicUrl = map.get("PicUrl");
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
}
