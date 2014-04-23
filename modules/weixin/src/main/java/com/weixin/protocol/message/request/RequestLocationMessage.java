package com.weixin.protocol.message.request;

import java.util.Map;

/**
 * 地理位置消息
 * 
 * @author liufeng
 * @date 2013-09-11
 */
public class RequestLocationMessage extends RequestBaseMessage {
	
	// 地理位置维度
	private String Location_X;
	// 地理位置经度
	private String Location_Y;
	// 地图缩放大小
	private String Scale;
	// 地理位置信息
	private String Label;
	
	public RequestLocationMessage(Map<String, String> map) {
		super(map);
		this.Location_X = map.get("Location_X");
		this.Location_Y = map.get("Location_Y");
		this.Scale = map.get("Scale");
		this.Label = map.get("Label");
	}


	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}

	public String getScale() {
		return Scale;
	}

	public void setScale(String scale) {
		Scale = scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}
}
