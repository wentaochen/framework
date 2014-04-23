package com.weixin.protocol.message.event;

import java.util.Map;

/**
 * 上报地理位置事件
 * 
 * @author liufeng
 * @date 2013-11-02
 */
public class LocationEvent extends BaseEvent {

	// 地理位置纬度
	private String Latitude;
	// 地理位置经度
	private String Longitude;
	// 地理位置精度
	private String Precision;

	public LocationEvent(Map<String, String> map) {
		super(map);
		this.Latitude = map.get("Latitude");
		this.Longitude = map.get("Longitude");
		this.Longitude = map.get("Longitude");
	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getPrecision() {
		return Precision;
	}

	public void setPrecision(String precision) {
		Precision = precision;
	}
}
