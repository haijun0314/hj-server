package org.jxjz.framework.enums;


/**
 * 设置  系统通用状态【0 是 1 否】
 * @author  lihaijun
 * 2012-10-18
 */
public enum DefaultStatus implements IHasValueEnum{
	Yes("0"),No("1");
	private String value;
	private DefaultStatus(String value){
		this.value = value;
	}
	
	public String getName() {
		switch (this) {
		case Yes:
			return "是";
		case No:
			return "否";
		 
		default:
			break;
		}
		return null;
	}

	public String getValue() {
		return this.value;
	}

}
