package com.hjf.core.bean;

import org.apache.log4j.Logger;
import org.jxjz.framework.util.LogUtil;


/**
 * 请求实体共用
 * author lihaijun
 * createTime   2017-04-01
 */
public class BaseReqBean {
	private String   userToken; 
	private Integer  accountId;		
	private String   versionNO;//版本编号
	private String   telephone;//账户
	private String   reqNO;//请求编号


	public Logger    log = LogUtil.getLogger(); 

 
	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getReqNO() {
		return reqNO;
	}

	public void setReqNO(String reqNO) {
		this.reqNO = reqNO;
	}	 

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getVersionNO() {
		return versionNO;
	}

	public void setVersionNO(String versionNO) {
		this.versionNO = versionNO;
	}



	public String getTelephone() {
		return telephone;
	}



	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


 
	
	
	 
	 
	 
	 
	
}
