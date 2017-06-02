package org.jxjz.framework.util;

import org.jxjz.base.spring.BaseAction;

import com.hjf.service.SysLogClientService;
/**
 * 【功能说明】：日志收集持久化类
 * @author lihaijun
 * 【创建时间】2017-05-25
 */
public class ClientLogUtil extends BaseAction {
	public static SysLogClientService clientLogService=(SysLogClientService)org.jxjz.base.spring.ApplicationFactory.getBean("clientLogService");
	
	 
	
  
	 
}
