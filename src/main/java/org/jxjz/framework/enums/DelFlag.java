package org.jxjz.framework.enums;


/**
 * 设置  是否逻辑删除【0 没删除  1删除 】
 * @author  lihaijun
 * 2012-10-18
 */
public enum DelFlag implements IHasValueEnum{
	NotDelete("0"),delete("1");
	private String value;
	private DelFlag(String value){
		this.value = value;
	}
	
	public String getName() {
		switch (this) {
		case delete:
			return "已删除";
		case NotDelete:
			return "未删除";
		 
		default:
			break;
		}
		return null;
	}

	public String getValue() {
		return this.value;
	}

}
