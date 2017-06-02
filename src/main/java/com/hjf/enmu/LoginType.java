package com.hjf.enmu;

import org.jxjz.framework.enums.IHasValueEnum;


/**
 * 登录类型  1  账户+密码  2  账户token
 * @author  lihaijun
 * 2012-10-18
 */
public enum LoginType implements IHasValueEnum{
	Password("0"),Token("1"),;
	private String value;
	private LoginType(String value){
		this.value = value;
	}
	
	public String getName() {
		switch (this) {
		case Password:
			return "账户+密码";
		case Token:
			return "账户token";
		 
		default:
			break;
		}
		return null;
	}

	public String getValue() {
		return this.value;
	}

}
