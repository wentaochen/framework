package com.weixin.protocol.message.event;

import java.util.Map;

/**
 * 扫描带参数二维码事件
 * 
 * @author liufeng
 * @date 2013-11-04
 */
public class QRCodeEvent extends BaseEvent {

	// 事件KEY值
	private String EventKey;
	// 用于换取二维码图片
	private String Ticket;

	public QRCodeEvent(Map<String, String> map) {
		super(map);
		this.EventKey = map.get("EventKey");
		this.Ticket = map.get("Ticket");
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}
}
