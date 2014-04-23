package com.weixin.protocol.message.response;

import com.weixin.protocol.message.response.element.ReponseVoice;
import com.weixin.protocol.message.util.ResponseUtils;

/**
 * 语音消息
 * 
 * @author liufeng
 * @date 2013-09-11
 */
public class ResponseVoiceMessage extends ResponseBaseMessage {
	// 语音
	private ReponseVoice Voice;

	public ReponseVoice getVoice() {
		return Voice;
	}

	public void setVoice(ReponseVoice voice) {
		Voice = voice;
	}

	@Override
	public String toXml() {
		return ResponseUtils.messageToXml(this);
	}
}
