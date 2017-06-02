package com.hjf.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jxjz.base.spring.BaseAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hjf.core.bean.reqBean.RegisterReqBean;
import com.hjf.core.bean.respBean.RegisterRespBean;
import com.hjf.service.RegisterService;
/**
 * 注册
 * author lihaijun
 * createTime   2014-12-02
 */
@Controller  
@RequestMapping("/register") 
public class RegisterController extends BaseAction{
	@Resource RegisterService    registerService;
	/**
	 * 【用户注册】
	 */
	@RequestMapping(params = "register")   
	public void register(HttpServletRequest request,HttpServletResponse response) throws Exception{
		log.info("【用户注册】开始....");
		RegisterRespBean r =new RegisterRespBean();
		RegisterReqBean  q =new RegisterReqBean();
		//检查参数
		boolean check=q.checkRegisterParams(request);
		if (!check) {
			log.error("【用户注册】....检查参数失败");
				err_param(response, r);
			respMsgObj(response, r);
			return;
		}
		//注册
		r=registerService.register(q,r) ;
		respMsgObj(response, r);
		log.info("【用户注册】...注册结束..account"+q.getTelephone());	 
	}	
	 
	
}
