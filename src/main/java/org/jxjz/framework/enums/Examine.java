package org.jxjz.framework.enums;


/**
 * 通用【1未通过、0通过】
 * @author  liubin
 * 2014-12-11
 */
public enum Examine implements IHasValueEnum{
	Through("0"),NotThrough("1"),WaitThrough("2");
	private String value;
	private Examine(String value){
		this.value = value;
	}
	
	public String getName() {
		switch (this) {
		case Through:
			return "通过";
		case NotThrough:
			return "未通过";
		case WaitThrough:
			return "待审核";
		default:
			break;
		}
		return null;
	}

	public String getValue() {
		return this.value;
	}

}
