package com.weixin.protocol.message.response;

import com.weixin.protocol.message.response.element.ResponseMusic;
import com.weixin.protocol.message.util.ResponseUtils;

/**
 * 音乐消息
 * 
 * @author liufeng
 * @date 2013-09-11
 */
public class ResponseMusicMessage extends ResponseBaseMessage {
	// 音乐
	private ResponseMusic Music;

	public ResponseMusic getMusic() {
		return Music;
	}

	public void setMusic(ResponseMusic music) {
		Music = music;
	}

	@Override
	public String toXml() {
		return ResponseUtils.messageToXml(this);
	}
}
