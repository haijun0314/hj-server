package org.jxjz.framework.enums;


/**
 * 菜单类型【1导航菜单、2菜单、3按钮】
 * @author  lihaijun
 * 2012-10-14
 */
public enum MenuType implements IHasValueEnum{
	Banner("0"),TreeMenu("1"),Button("2");
	private String value;
	private MenuType(String value){
		this.value = value;
	}
	
	public String getName() {
		 
		return null;
	}

	public String getValue() {
		return this.value;
	}

}
