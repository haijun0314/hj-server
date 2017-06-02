package org.jxjz.framework.enums;


/**
 * 设置  存在状态 存在【0 存在 1 不存在】
 * @author  lihaijun
 * 2012-10-18
 */
public enum ExistStatus implements IHasValueEnum{
	Exist("0"),NotExist("1");
	private String value;
	private ExistStatus(String value){
		this.value = value;
	}
	
	public String getName() {
		switch (this) {
		case Exist:
			return "存在";
		case NotExist:
			return "不存在";
		 
		default:
			break;
		}
		return null;
	}

	public String getValue() {
		return this.value;
	}

}
