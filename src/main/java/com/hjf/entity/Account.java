package com.hjf.entity;

import java.util.Date;

import org.jxjz.base.model.BaseModel;
/**
 * 功能说明:【账户信息】
 * 作    者：lihaijun
 * 创建日期：2017-05-10
 */
public class Account extends BaseModel implements java.io.Serializable {
	private Integer accountId;
    private String  userToken;					//账户token  免密码登录
	private String  accountName; 				//用户名称
	private String  telephone;					//电话号码
	private String  password;					//账号密码
	private Integer age;						//年龄
	private String  headPic;					//头像地址
	private String 	cityCode;				    //城市编号
	private String 	cityName;				    //城市名称
	private String  dynamic;					//当前动态
	private String  email;						//email
	private Date    loginTime;					//登录时间
	private String  QQ;							//qq
	private Date    registTime;					//注册时间	
	private String  status;						//状态0.正常1.暂停
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDynamic() {
		return dynamic;
	}
	public void setDynamic(String dynamic) {
		this.dynamic = dynamic;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public Date getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
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
	private String  sex;						//性别  0  男 1  女
	
	
	
	
	
	
}
