package com.hjf.core.util;

public class KeysUtil {
	public static String  Session_login_User		="session:%s";    		//登录用户session缓存
	
	 
     
    public static String getKey(String format, String id) {
        return String.format(format, id);
    }


	public static String getSession_login_User(String id) {
		 return getKey(Session_login_User, id);
	}
 
	
}

