package org.jxjz.base.model;

import java.io.Serializable;
import java.util.Date;

import org.jxjz.framework.enums.DelFlag;


/**
 * 【功能说明】：实体类基类    数据库对应实体要继承此类
 * @author lihaijun
 * 【创建时间】2017-05-25
 */
public class BaseModel implements Serializable{

	private String createdBy;
	private String updatedBy;
	private Date createdTime;
	private Date updatedTime;
	private DelFlag delFlag ;
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public DelFlag getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(DelFlag delFlag) {
		this.delFlag = delFlag;
	}
  


	 
	
}
