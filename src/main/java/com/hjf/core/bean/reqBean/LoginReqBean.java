package com.hjf.core.bean.reqBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.hjf.core.bean.BaseReqBean;
import com.hjf.core.util.SecUtil;

public class LoginReqBean extends BaseReqBean {
	private String  telephone;//账户
	private String  password;//用户密码
	private HttpServletResponse response;

	/**
	 * 【账户登录参数验证】
	 */
	public boolean checkParams(HttpServletRequest request) {
		String telephone    =request.getParameter("telephone");
		String password     =request.getParameter("password");	
		if (StringUtils.isBlank(telephone)) {
			log.error("【账户登录参数验证】 验证失败 telephone缺失 ");
			return  false;
		}
		if (StringUtils.isBlank(password)) {
			log.error("【账户登录参数验证】 验证失败 password缺失 ");
			return  false;
		}
		setResponse(response);
		setTelephone(telephone); 		
		setPassword(SecUtil.encrypt(password));
		return true;
	}

 


	public String getTelephone() {
		return telephone;
	}




	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}




	public HttpServletResponse getResponse() {
		return response;
	}




	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}




	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
 
}
