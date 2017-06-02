package org.jxjz.framework.exception;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jxjz.common.util.web.ResponseUtils;
import org.jxjz.framework.util.LogUtil;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;   
/**
 * 【功能说明】：spring mvc 定义通用异常处理类
 * @author lihaijun
 * 【创建时间】2017-05-25
 */
public class ExceptionHandler implements HandlerExceptionResolver {   
	    public ModelAndView resolveException(HttpServletRequest request,    HttpServletResponse response, Object handler, Exception ex) { 
		 	LogUtil.getLogger().error("......"+ex.getMessage());
		 	LogUtil.getLogger().error(ex.getStackTrace());
	        if (ex instanceof  RepeatException) {
	        	ResponseUtils.renderJson(response, ((RepeatException) ex).err_msg);
	        	return null;
			}
		 	return new ModelAndView("error/error");   
	    }   
}
