package com.hjf.service;

import com.hjf.core.bean.BaseReqBean;
import com.hjf.core.bean.reqBean.AccountReqBean;
import com.hjf.core.bean.respBean.AccountRespBean;
import com.hjf.entity.Account;
/**
 * 功能说明:【账户信息】
 * 作    者：lihaijun
 * 创建日期：2017-05-11
 */
public interface AccountService {
	//【更新账户信息】
	public void update(AccountReqBean q)	;	 
	//【查看账户信息】
	public AccountRespBean accountInfo(BaseReqBean q,AccountRespBean r)	;	
	//【查看账户信息】
	public Account getAccount(Integer accountId)	;	
	//【查看账户信息】
	public Account getAccountByTelephone(String telephone)	;	
	//【添加账户】
	public int addAccount(Account a)	;
}
