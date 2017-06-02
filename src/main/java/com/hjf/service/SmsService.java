package com.hjf.service;


public interface SmsService {
	public  String sendValidateSms(String telePhone);
	/**
	 * 验证码验证
	 */
	public  boolean  smsCheck(String telePhone,String smsCode);
	/**
	 * 注册短信验证码
	 */
	 
	
}
