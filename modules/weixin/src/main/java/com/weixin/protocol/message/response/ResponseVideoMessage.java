package com.weixin.protocol.message.response;

import com.weixin.protocol.message.response.element.ResponseVideo;
import com.weixin.protocol.message.util.ResponseUtils;

/**
 * 视频消息
 * 
 * @author liufeng
 * @date 2013-09-11
 */
public class ResponseVideoMessage extends ResponseBaseMessage {
	// 视频
	private ResponseVideo Video;

	public ResponseVideo getVideo() {
		return Video;
	}

	public void setVideo(ResponseVideo video) {
		Video = video;
	}

	@Override
	public String toXml() {
		return ResponseUtils.messageToXml(this);
	}
}
