package org.jxjz.framework.util;

import org.jxjz.common.util.PropUtils;

/**
 * 【功能说明】：系统配置通用处理类
 * @author lihaijun
 * 【创建时间】2017-05-25
 */
public class ConfigUtil {
	
	 public  static String  environment;
	 public  static String  server_app_host_url;
	 public  static String  server_app_host;
	 public  static String  sys_session_mode;
	 public  static String  sys_appSecret;
	 public  static String  sys_secKey;
	 public  static String  sys_uploadPath;
	 public  static String  sys_user_defaultPassword;
	 public  static String  cus_headPic;
	 public  static String  dimeCode_path;


	

	 public  static String  logName="h";
	 public  static String  logName_sys="h";
	 public  static String  Session_Login_User= "session_login_user";//页面跳转类型【请求数据】	
	 public  static String  Cookie_Login_User= "CKLHJ";//页面跳转类型【请求数据】	
	 
	static{
		environment 		=PropUtils.getMsgStr("environment");
		server_app_host_url =PropUtils.getMsgStr("server.app.host.url");
		server_app_host 	=PropUtils.getMsgStr("server.app.host");
		sys_session_mode	=PropUtils.getMsgStr("sys.session.mode");
		sys_appSecret		=PropUtils.getMsgStr("sys.appSecret");
		sys_secKey			=PropUtils.getMsgStr("sys.secKey");
		sys_uploadPath		=PropUtils.getMsgStr("sys.uploadPath");
		sys_user_defaultPassword=PropUtils.getMsgStr("sys.user.defaultPassword");
		cus_headPic 		=PropUtils.getMsgStr("cus.headPic");
		dimeCode_path 		=PropUtils.getMsgStr("dimeCode.path");
		 
	}		
	 
	public static boolean isSessionLocal(){
		boolean isLocal = "local".equalsIgnoreCase(sys_session_mode);
		return isLocal;
	}
	
	

	 
	 
	 
}
