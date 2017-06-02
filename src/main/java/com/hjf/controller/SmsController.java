package com.hjf.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jxjz.base.spring.BaseAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hjf.core.bean.BaseRespBean;
import com.hjf.core.util.CodeUtil;
import com.hjf.service.SmsService;
/**
 * 短信验证码
 * author lihaijun
 * createTime   2014-12-02
 */
@Controller  
@RequestMapping("/sms") 
public class SmsController extends BaseAction{
	@Resource SmsService smsService;
	
	/**
	 * 【用户注册短信发送】
	 */
	@RequestMapping(params = "sendValidateSms")   
	public void sendValidateSms(HttpServletRequest request,HttpServletResponse response) throws Exception{
		BaseRespBean r=new BaseRespBean();
		log.info("【用户注册短信发送】：发送验证码开始...."); 
		String telePhone=request.getParameter("telephone");
		log.info("【用户注册短信发送】：发送验证码开始....telephone="+telePhone); 
		if (StringUtils.isBlank(telePhone)) {
			log.info("【用户注册短信发送】：手机号码为空，不能发送"); 
			err_param(response, r);
			respMsgObj(response, r);
			return;
		}
		String  smsCode=smsService.sendValidateSms(telePhone);
		if (StringUtils.isBlank(smsCode)) {
			log.info("【用户注册短信发送】：短信发送失败telePhone="+telePhone); 
			r.fail(CodeUtil.e_1004);
			respMsgObj(response, r);
			return;
		}
		log.info("【用户注册短信发送】：短信发送成功telePhone="+telePhone+"短信放进缓存smsCode="+smsCode); 
		r.success();
		respMsgObj(response, r); 
	}	
	
	
	 	
	
	
	
	
	
	  
	
}
