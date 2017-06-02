package org.jxjz.framework.enums;


/**
 * 设置  是否超级管理员【0 是 1 否】
 * @author  lihaijun
 * 2012-10-18
 */
public enum IsSuper implements IHasValueEnum{
	Yes("0"),No("1");
	private String value;
	private IsSuper(String value){
		this.value = value;
	}
	
	public String getName() {
		switch (this) {
		case Yes:
			return "超级管理员";
		case No:
			return "普通管理员";
		 
		default:
			break;
		}
		return null;
	}

	public String getValue() {
		return this.value;
	}

}
