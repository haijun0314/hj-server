package org.jxjz.framework.enums;


/**
 * 通用【0选中、1未选中】
 * @author  liubin
 * 2014-12-11
 */
public enum SelectStatus implements IHasValueEnum{
	Select("0"),NotSelect("1");
	private String value;
	private SelectStatus(String value){
		this.value = value;
	}
	
	public String getName() {
		switch (this) {
		case Select:
			return "选中";
		case NotSelect:
			return "未选中";
		default:
			break;
		}
		return null;
	}

	public String getValue() {
		return this.value;
	}

}
