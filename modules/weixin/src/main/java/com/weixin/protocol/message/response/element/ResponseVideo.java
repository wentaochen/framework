package com.weixin.protocol.message.response.element;

/**
 * 视频model
 * 
 * @author liufeng
 * @date 2013-09-11
 */
public class ResponseVideo {
	// 媒体文件id
	private String MediaId;
	// 缩略图的媒体id
	private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
}
