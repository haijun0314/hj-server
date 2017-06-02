package org.jxjz.framework.enums;


/**
 * 设置  取消【0 是 1 否  2 无操作】
 * @author  lihaijun
 * 2012-10-18
 */
public enum IsConcel implements IHasValueEnum{
	Yes("0"),No("1"),NoOper("2");
	private String value;
	private IsConcel(String value){
		this.value = value;
	}
	
	public String getName() {
		switch (this) {
		case Yes:
			return "是";
		case No:
			return "否";
		case NoOper:
			return "无操作";
		default:
			break;
		}
		return null;
	}

	public String getValue() {
		return this.value;
	}

}
