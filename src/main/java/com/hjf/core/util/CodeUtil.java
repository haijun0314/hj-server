package com.hjf.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统错误码
 * @author lihaijun
 * 2014-12-02
 */
public class CodeUtil {
	/**
	 * 代码说明:起始数字
	 * 9：系统错误
	 * 1：账户错误
	 */
	private static Map<String,String>  codeMap=new HashMap<String, String>();
	
	public static String e_1001="1001";//密码错误
	public static String e_1002="1002";//没有该账户
	public static String e_1003="1003";//账户或密码错误
	public static String e_1004="1004";//发送短信失败
	public static String e_1005="1005";//注册失败
	public static String e_1006="1006";//短信验证失败
	public static String e_1007="1007";//账户已经注册过
	public static String e_1008="1008";//更新账户信息失败
	public static String e_1009="1009";//缺失账户信息
	public static String e_1010="1010";//session  过期
	 
	
	
	public static void initCode(){
		codeMap.put("1001", "密码错误");
		codeMap.put("1002", "没有该账户");
		codeMap.put("1003", "账户或密码错误");
		codeMap.put("1004", "发送短信失败");
		codeMap.put("1005", "注册失败");
		codeMap.put("1006", "短信验证失败");
		codeMap.put("1007", "账户已经注册过");
		codeMap.put("1008", "更新账户信息失败");
		codeMap.put("1009", "缺失账户信息");
		codeMap.put("1010", "session过期");
	}
	
	
	public static String getCodeMsg(String code) {
		if (codeMap==null||codeMap.isEmpty()) {
			initCode();
		}
		return codeMap.get(code);
	}	 
	
}
