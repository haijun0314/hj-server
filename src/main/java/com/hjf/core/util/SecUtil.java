package com.hjf.core.util;

import org.jxjz.common.util.secret.DesUtil;
import org.jxjz.framework.util.ConfigUtil;

public class SecUtil {
	 
 
	public static  String encrypt(String msg) {
		msg=DesUtil.encrypt(msg, ConfigUtil.sys_secKey);
		return msg;
	}	
	
	public static  String decrypt(String msg) {
		msg=DesUtil.decrypt(msg, ConfigUtil.sys_secKey);
		return msg;
	}	
	
}
