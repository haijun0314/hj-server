package com.hjf.core.bean.respBean;

import com.hjf.core.bean.BaseRespBean;

public class RegisterRespBean extends BaseRespBean {
	private Integer accountId;					//用户编号
    private String  userToken;//账户token  免密码登录
	private Integer userId; //用户编号
	private String  userName;//用户名
	private String  realName;//真是姓名
	private String  status;//【1开启、0关闭】
	private String  sex;//性别
	private String  telePhone;//联系电话
	private String  email;//邮箱
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTelePhone() {
		return telePhone;
	}
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	 
	 



}
