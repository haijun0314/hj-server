package com.hjf.core.util;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.jxjz.common.util.secret.DesUtil;
import org.jxjz.framework.util.ConfigUtil;
import org.jxjz.framework.util.LogUtil;

import com.hjf.core.bean.reqBean.RegisterReqBean;
import com.hjf.entity.Account;


/**
 * 账户工具类
 * lihaijun
 * 2014-12-03
 */
public class AccountUtil { 
	static Logger log=LogUtil.getLogger();
	 private static ThreadLocal<Integer> myIdLocal = new ThreadLocal<Integer>();  
	/**
	 * 初始化账户
	 */
	public static  Account createAccount(RegisterReqBean  q) {
		String password=SecUtil.encrypt(q.getPassword());
		Account cst=new Account();
		cst.setPassword(password);
		cst.setTelephone(q.getTelephone());
		cst.setRegistTime(new Date());
		return cst;
	}
	/**
	 * 创建token
	 */
	public static  void createToken(Account  c) {
		try {
			String token=DesUtil.encrypt(c.getTelephone()+"-"+c.getAccountId()+"-"+UUID.randomUUID(), ConfigUtil.sys_secKey);
			c.setUserToken(token);
		} catch (Exception e) {
			log.error("创建登录账户token  失败"+e.getMessage());
			 e.getStackTrace();
		}
	}	
	
	
    public static Integer getMyId() {  
        return (Integer) myIdLocal.get();  
    }  
  
    public static void setMyId(Integer id) {  
    	myIdLocal.set(id);  
    }   	
	
	 
	
}
