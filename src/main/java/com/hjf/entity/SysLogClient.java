package com.hjf.entity;

import java.util.Date;

import org.jxjz.base.model.BaseModel;

/**
 * 客户端日志
 * author lihaijun
 * createTime   2014-12-01
 */

public class SysLogClient  extends BaseModel implements java.io.Serializable {

	private Integer  logId;
	private String   content;
	private Date     logTime;
	private Integer  customerId;
	private String   longitude;//经度                  
	private String   latitude; //纬度                  
	private String   address;	//所在地	
	
	public SysLogClient( String content,Integer  customerId,String   longitude,String   latitude,String   address) {
		this.content = content;
		this.customerId=customerId;
		this.longitude = longitude;
		this.latitude=latitude;
		this.address = address;
	}	
	public SysLogClient( String content,Integer  customerId) {
		this.content = content;
		this.customerId=customerId;
	}
	public SysLogClient( String content) {
		this.content = content;
	}
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	 
	 
	 
 

}