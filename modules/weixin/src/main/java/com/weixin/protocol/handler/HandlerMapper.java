package com.weixin.protocol.handler;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapper {

	private Map<String, Handler> protocolMap;

	public HandlerMapper() {
		protocolMap = new HashMap<String, Handler>();
	}

	public Map<String, Handler> getProtocolMap() {
		return protocolMap;
	}

	public void setProtocolMap(Map<String, Handler> protocolMap) {
		this.protocolMap = protocolMap;
	}

	public void putProtocol(String id, Handler protocol) {
		this.protocolMap.put(id, protocol);
	}

	public Handler getProtocol(String id) {
		return this.protocolMap.get(id);
	}

}
