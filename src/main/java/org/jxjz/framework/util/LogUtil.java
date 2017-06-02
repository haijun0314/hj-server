package org.jxjz.framework.util;

import org.apache.log4j.Logger;
/**
 * 【功能说明】：日志工具类
 * @author lihaijun
 * 【创建时间】2017-05-25
 */
public class LogUtil {
	 
 
	public static  Logger getLogger() {
		return  Logger.getLogger(ConfigUtil.logName); 
		
	}	
	 
}
