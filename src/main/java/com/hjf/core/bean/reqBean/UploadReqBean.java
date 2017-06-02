package com.hjf.core.bean.reqBean;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hjf.core.bean.BaseReqBean;

public class UploadReqBean extends BaseReqBean {
	 private Logger   log = Logger.getLogger("common"); 
	 private String   module;	//上传模块  //ask headPic  verify  cardPic
	 
	/**
	 * 【文件上传参数验证】
	 */
	public boolean	checkParams(HttpServletRequest request){
		String module  =request.getParameter("module");
		if (StringUtils.isBlank(module)) {
			 log.error("【文件上传参数验证】..检查参数失败  缺失模块module");
			 return false;
		} 
		setModule(module);
		return true;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}		
	
	 
	 
	 
}
