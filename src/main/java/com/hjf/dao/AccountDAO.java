package com.hjf.dao;

import org.jxjz.base.mybatis.BaseIbatisDAO;
import org.springframework.stereotype.Repository;

import com.hjf.entity.Account;

/**
 * 用户
 * lihaijun
 * 2014-12-01
 */
@Repository
public class AccountDAO extends BaseIbatisDAO{
	
	 
	/**
	 * 根据sessionId查询用户
	 */
	public Account getBySessionId(String  sessionId) {
		return (Account)getObjById(sessionId,"findBySessionId");
	}	
	/**
	 *【重设账户登录密码】
	 */
	public int resetPassword( Account c){
		return update(c,"resetPassword");
	}	
	
	/**
	 * 根据sessionId查询用户
	 */
	public Account getAccountById(Integer accountId) {
		return (Account)getObjById(accountId);
	}	
	 
	 
	public Class getEntityClass() {
		return Account.class;
	}	
	 
}