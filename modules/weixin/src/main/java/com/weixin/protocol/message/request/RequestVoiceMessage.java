package com.weixin.protocol.message.request;

import java.util.Map;

/**
 * 语音消息
 * 
 * @author liufeng
 * @date 2013-09-11
 */
public class RequestVoiceMessage extends RequestBaseMessage {
	// 媒体ID
	private String MediaId;
	// 语音格式
	private String Format;
	// 语音识别结果，UTF8编码
	private String Recognition;

	public RequestVoiceMessage(Map<String, String> map) {
		super(map);
		this.MediaId = map.get("MediaId");
		this.Format = map.get("Format");
		this.Recognition = map.get("Recognition");
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
}
