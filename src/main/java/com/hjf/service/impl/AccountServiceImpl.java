package com.hjf.service.impl;

/**
 * 功能说明:【账户信息】
 * 作    者：lihaijun
 * 创建日期：2014-11-21
 */
import javax.annotation.Resource;

import org.jxjz.framework.exception.ErrorCodeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjf.core.bean.BaseReqBean;
import com.hjf.core.bean.reqBean.AccountReqBean;
import com.hjf.core.bean.respBean.AccountRespBean;
import com.hjf.dao.AccountDAO;
import com.hjf.entity.Account;
import com.hjf.service.AccountService;
@Service
@Transactional 
public class AccountServiceImpl   extends BaseService implements AccountService {
	@Resource AccountDAO accountDAO;
	
 
	/**
	 * 【更新自己账户信息】
	 */
	public void update(AccountReqBean q){
		try {
			Account c=new Account();
			copyProperties(c, q);
			int ret=accountDAO.updateById(c);
			if (ret<0) {
				log.error("【更新自己账户信息】..."+q.getAccountId()+"发生异常...");
			}
		} catch (Exception e) {
			log.error("【更新自己账户信息】..."+q.getAccountId()+"发生异常...");
			e.printStackTrace();
		}
	}
	
	/**
	 * 【查看他人账户信息】
	 */
	public AccountRespBean accountInfo(BaseReqBean q,AccountRespBean r){
		try {
			Account map =accountDAO.getAccountById(q.getAccountId());
			copyProperties(r, map);
		} catch (Exception e) {
			r.fail(ErrorCodeUtil.e_9996);
			log.error("【查看他人账户信息】..."+q.getAccountId()+"发生异常...");
			e.printStackTrace();
			return r;
		}
		r.success();
		return r;
	}	
	/**
	 * 【查看账户信息】
	 */
	public Account getAccount(Integer accountId)	{
		Account account =accountDAO.getAccountById(accountId);
		return account;
	}
	
	
	/**
	 * 【查看账户信息】
	 */
	public Account getAccountByTelephone(String telephone)	{
		Account account =(Account) accountDAO.getObjById(telephone, "findByTelephone");
		return account;
	}
	
	
	/**
	 * 【添加账户】
	 */
	public int addAccount(Account a)	{
		int id=accountDAO.saveWithReturnId(a);
		return id;
	}	
}
