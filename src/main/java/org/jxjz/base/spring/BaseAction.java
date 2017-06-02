package org.jxjz.base.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jxjz.common.util.JsonUtil;
import org.jxjz.common.util.web.ResponseUtils;
import org.jxjz.framework.enums.DelFlag;
import org.jxjz.framework.exception.ErrorCodeUtil;
import org.jxjz.framework.util.LogUtil;
import org.springframework.stereotype.Controller;

import com.hjf.core.bean.BaseRespBean;
import com.hjf.entity.SysLogClient;
import com.hjf.service.SysLogClientService;
/**
 * 【功能说明】：通用controller 继承类
 * @author lihaijun
 * 【创建时间】2017-05-25
 */
@Controller  
public class BaseAction {
	 
	
	public Logger log = LogUtil.getLogger();	
	/**
	 * 系统错误返回
	 */
	public void   errorMsg(HttpServletResponse response){
		BaseRespBean brb=new BaseRespBean();
		brb.fail(ErrorCodeUtil.error);
		String jsonStr=JsonUtil.obj2Json(brb);
		ResponseUtils.renderJson(response,jsonStr);
	}	
	
	/**
	 * 系统错误返回
	 */
	public void   errorMsg(HttpServletResponse response,Exception e){
		log.error("系统错误..."+e.getMessage());
		log.error("系统错误..."+e.getStackTrace());
		BaseRespBean brb=new BaseRespBean();
		brb.fail(ErrorCodeUtil.error);
		String jsonStr=JsonUtil.obj2Json(brb);
		ResponseUtils.renderJson(response,jsonStr);
	}	
	
	 
	
	/**
	 * 记录操作日志
	 */
	public void   addClientLog(HttpServletRequest request,SysLogClient clientLog){
		SysLogClientService clientLogService=(SysLogClientService)org.jxjz.base.spring.ApplicationFactory.getBean("clientLogService");
		clientLog.setDelFlag(DelFlag.NotDelete);
		clientLogService.addCacheLog(clientLog);
	}
	public void  respMsgObj(HttpServletResponse response,Object obj) {
		String jsonStr=JsonUtil.obj2Json(obj);
		ResponseUtils.renderJson(response,jsonStr);
		log.info(jsonStr);
	} 
	
	public void  err_param(HttpServletResponse response,BaseRespBean r) {
		r.fail(ErrorCodeUtil.e_9999);
		String jsonStr=JsonUtil.obj2Json(r);
		ResponseUtils.renderJson(response,jsonStr);
		log.info(jsonStr);
	} 
 
	
	/**
	 * 记录操作日志
	 */
	public void   checkVersion(HttpServletRequest request){
		 String version=request.getParameter("version");
		
	}	
	
	
	 
  

			
}
