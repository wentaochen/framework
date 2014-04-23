package com.weixin.protocol.message.event;

import java.util.Map;

/**
 * 自定义菜单事件
 * 
 * @author liufeng
 * @date 2013-11-04
 */
public class MenuEvent extends BaseEvent {

	// 事件KEY值，与自定义菜单接口中KEY值对应
	private String EventKey;

	public MenuEvent(Map<String, String> map) {
		super(map);
		this.EventKey = map.get("EventKey");
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
}
