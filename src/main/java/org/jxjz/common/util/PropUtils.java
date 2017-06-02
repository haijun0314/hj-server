package org.jxjz.common.util;

import org.jxjz.base.spring.ApplicationFactory;
import org.springframework.context.support.MessageSourceAccessor;

public class PropUtils {
	private static MessageSourceAccessor message =new MessageSourceAccessor(ApplicationFactory.getContext());
	
	 /**
	  * 获取系统资源
	  * @return
	  */
	 public static MessageSourceAccessor getMessageSource(){
		return  message;
	 }
	 
	 /**
	  * 获取系统资源信息
	  * @return
	  */
	 public static String getMsgStr(String resourceKey){
		return  message.getMessage(resourceKey);
	 }

}
