package com.hjf.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jxjz.base.spring.BaseAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hjf.core.bean.reqBean.LoginReqBean;
import com.hjf.core.bean.respBean.LoginRespBean;
import com.hjf.service.LoginService;
 
/**
 * 功能说明:【用户登录】
 * 作    者：lihaijun
 * 创建日期：2014-11-21
 */
@Controller  
@RequestMapping("/app/login") 
public class LoginController extends BaseAction{
	@Resource LoginService     loginService;
	/**
	 * 【用户登录】
	 */
	@RequestMapping(params = "weblogin")   
	public void login(HttpServletRequest request,HttpServletResponse response) {
		try {
			log.info("【用户登录】...");
			LoginRespBean    r =new LoginRespBean();
			LoginReqBean     q =new LoginReqBean();
			q.setResponse(response);
			boolean check     =q.checkParams(request);
			if (!check) {
				err_param(response, r);
				return;	
			}
			r=loginService.weblogin(q,r);
			respMsgObj(response, r);
		} catch (Exception e) {
			log.error("【用户登录】...发生异常");
			errorMsg(response,e);
		}
	}	
	 
	
}
