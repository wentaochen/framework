package com.weixin.protocol.message.request;

import java.util.Map;

/**
 * 请求消息基类（普通用户 -> 公众帐号）
 * 
 * @author liufeng
 * @date 2013-09-11
 */
public abstract class RequestBaseMessage {
	// 开发者微信号
	private String ToUserName;
	// 发送方帐号（一个OpenID）
	private String FromUserName;
	// 消息创建时间 （整型）
	private long CreateTime;
	// 消息类型
	private String MsgType;
	// 消息id，64位整型
	private long MsgId;

	protected RequestBaseMessage(Map<String, String> map) {
		this.ToUserName = map.get("ToUserName");
		this.FromUserName = map.get("FromUserName");
		this.CreateTime = Long.valueOf(map.get("CreateTime"));
		this.MsgType = map.get("MsgType");
		this.MsgId = Long.valueOf(map.get("MsgId"));
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}
}