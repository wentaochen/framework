package com.weixin.protocol.message.response;

import com.weixin.protocol.message.response.element.ResponseImage;
import com.weixin.protocol.message.util.ResponseUtils;

/**
 * 图片消息
 * 
 * @author liufeng
 * @date 2013-09-11
 */
public class ResponseImageMessage extends ResponseBaseMessage {
	// 图片
	private ResponseImage Image;

	public ResponseImage getImage() {
		return Image;
	}

	public void setImage(ResponseImage image) {
		Image = image;
	}

	@Override
	public String toXml() {
		return ResponseUtils.messageToXml(this);
	}
}
