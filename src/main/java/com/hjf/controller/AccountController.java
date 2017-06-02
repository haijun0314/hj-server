package com.hjf.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jxjz.base.spring.BaseAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hjf.core.bean.BaseReqBean;
import com.hjf.core.bean.BaseRespBean;
import com.hjf.core.bean.reqBean.AccountReqBean;
import com.hjf.core.bean.respBean.AccountRespBean;
import com.hjf.service.AccountService;
 
/**
 * 功能说明:【用户信息】
 * 作    者：lihaijun
 * 创建日期：2014-11-21
 */
@Controller  
@RequestMapping("/account") 
public class AccountController extends BaseAction{
	@Resource AccountService    accountService;
	/**
	 * 【查询自己账户信息】
	 */
	@RequestMapping(params = "myInfo")   
	public void myInfo(HttpServletRequest request,HttpServletResponse response) {
		try {
			log.info("【查询自己账户信息】...");
			BaseReqBean    		q =new BaseReqBean();
			AccountRespBean    r =new AccountRespBean();
			r=accountService.accountInfo(q, r);
			respMsgObj(response, r);
		} catch (Exception e) {
			log.error("【查询自己账户信息】...发生异常");
			errorMsg(response);
		}
	}	
	
	
	
	/**
	 * 【更新自己账户信息】
	 */
	@RequestMapping(params = "update")   
	public void update(HttpServletRequest request,HttpServletResponse response) {
		try {
			AccountReqBean    	q =new AccountReqBean();
			BaseRespBean    	r =new BaseRespBean();
			boolean check        =q.checkParams(request);
			if (!check) {
				err_param(response, r);
				respMsgObj(response, r);
				return;	
			}
			accountService.update(q);
			respMsgObj(response, r);
		} catch (Exception e) {
			log.error("【查询自己账户信息】...发生异常");
			errorMsg(response);
		}
	}		
	
	/**
	 * 【查看他人账户信息】
	 */
	@RequestMapping(params = "customerInfo")   
	public void customerInfo(HttpServletRequest request,HttpServletResponse response) {
		try {
			BaseReqBean    		q =new BaseReqBean();
			AccountRespBean    r =new AccountRespBean();
			r=accountService.accountInfo(q, r);
			respMsgObj(response, r);
		} catch (Exception e) {
			log.error("【查看他人账户信息】...发生异常");
			errorMsg(response);
		}
	}	
	 
}
