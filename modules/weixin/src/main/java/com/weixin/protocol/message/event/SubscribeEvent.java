package com.weixin.protocol.message.event;

import java.util.Map;

/**
 * 关注/取消关注事件
 * 
 * @author liufeng
 * @date 2013-11-04
 */
public class SubscribeEvent extends BaseEvent {

	public SubscribeEvent(Map<String, String> map) {
		super(map);
	}

}
