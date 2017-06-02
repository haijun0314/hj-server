package com.hjf.core.bean.reqBean;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jxjz.common.util.ValidateUtils;

import com.hjf.core.bean.BaseReqBean;
import com.hjf.core.util.SecUtil;

public class RegisterReqBean extends BaseReqBean {
	 private String  telephone;   
	 private String  password;
	 private String  smsCode;
	/**
	 *  【账户注册参数验证】
	 */
	public boolean checkRegisterParams(HttpServletRequest request) {
		String telephone  =request.getParameter("telephone");
		String password   =request.getParameter("password");
		String smsCode    =request.getParameter("smsCode");
		if (StringUtils.isBlank(telephone)) {
			log.error(" 【账户注册参数验证】...参数错误...缺失账户名称.");
			return false;
		}
		if (!ValidateUtils.isMobile(telephone)) {
			log.error(" 【账户注册参数验证】...参数错误...手机号码错误.");
			return false;
		}
		if (StringUtils.isBlank(password)) {
			log.error(" 【账户注册参数验证】...参数错误...缺失密码");
			return false;
		}
		if (StringUtils.isBlank(smsCode)) {
			log.error(" 【账户注册参数验证】...参数错误....缺失短信验证码");
			return false;
		}
		setTelephone(telephone);
		setPassword(SecUtil.encrypt(password));
		setSmsCode(smsCode);
		return true;
	}	 
 
 
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	 
	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
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

 


 
	
	
}
