package com.hjf.core.bean.reqBean;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.hjf.core.bean.BaseReqBean;
import com.hjf.core.util.SecUtil;

public class AccountReqBean extends BaseReqBean {
	private Integer accountId;
    private String  userToken;					//账户token  免密码登录
	private String  accountName; 				//用户名称
	private String  password;					//账号密码
	private Integer age;						//年龄
	private String  headPic;					//头像地址
	private String  dynamic;					//当前动态
	private String  email;						//email
	private String  QQ;							//qq
	
	
	/**
	 * 【账户登录参数验证】
	 */
	public boolean checkParams(HttpServletRequest request) {
		String accountId    =request.getParameter("accountId");
		if (StringUtils.isBlank(accountId)) {
			return  false;
		}
		 setAccountId(new Integer(accountId));
		return true;
	}	
	
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
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
 
	
	
}
