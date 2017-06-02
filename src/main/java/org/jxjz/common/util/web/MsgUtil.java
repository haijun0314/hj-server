package org.jxjz.common.util.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jxjz.common.util.JsonUtil;
import org.jxjz.framework.exception.ErrorCodeUtil;
import org.jxjz.framework.util.LogUtil;
 
/**
 * 【功能说明】： 页面操作返回消息
 * @author lihaijun
 * 【创建时间】2017-05-25
 */
public class MsgUtil {
	 
	 /**
	  * 操作信息
	  */
	 public static void operSuccess(HttpServletResponse response){
			  operSuccess(response,ErrorCodeUtil.success);
	 } 
	 
	 /**
	  * 操作成功信息
	  * @return
	  */
	 public static void operSuccess(HttpServletResponse response,String code){
		 	Map msg=new HashMap();
			msg.put("msg", ErrorCodeUtil.getCodeMsg(code));
			msg.put("success",true);
			String msgJson=JsonUtil.obj2Json(msg);	 
			ResponseUtils.renderJson(response,msgJson);
	 }	 
	 
	 /**
	  * 操作成功信息
	  * @return
	  */
	 public static void operMsg(HttpServletResponse response,String msg){
			Map jsonObject=new HashMap();
			jsonObject.put("msg",msg);
			jsonObject.put("success",true);
			String msgJson=JsonUtil.obj2Json(jsonObject);	 
			ResponseUtils.renderJson(response,msgJson);
	 }	 
	  
	 
	 
	 
	 /**
	  * 操作信息
	  */
	 public static void operFail(HttpServletResponse response,Exception e){
		 	operFail(response,  e,ErrorCodeUtil.error);
	 } 	 
	 
	 /**
	  * 操作信息
	  */
	 public static void operFail(HttpServletResponse response,String code){
		 	operFail(response,  null,code);
	 } 
	 /**
	  * 操作信息
	  */
	 public static void operFail(HttpServletResponse response){
		 	operFail(response,  null,ErrorCodeUtil.error);
	 } 
	 
	 
	 /**
	  * 操作失败信息
	  * @return
	  */
	 public static void operFail(HttpServletResponse response,Exception e,String code){
		    if (e!=null) {
		    	LogUtil.getLogger().error(e.getMessage());
			}
		    String msg= ErrorCodeUtil.getCodeMsg(code);
		    if (StringUtils.isBlank(msg)) {
		    	msg=code;
			}
		    Map jsonObject=new HashMap();
			jsonObject.put("msg",msg);
			jsonObject.put("success",false);
			String msgJson=JsonUtil.obj2Json(jsonObject);	 
			ResponseUtils.renderJson(response,msgJson);
	 }		 
	  
}
