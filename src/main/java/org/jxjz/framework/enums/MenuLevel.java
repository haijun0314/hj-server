package org.jxjz.framework.enums;


/**
 * 菜单级别【1、2、3】
 * @author  lihaijun
 * 2012-10-14
 */
public enum MenuLevel implements IHasValueEnum{
	One("1"),Two("2"),Three("3");
	private String value;
	private MenuLevel(String value){
		this.value = value;
	}
	
	public String getName() {
		 
		return null;
	}

	public String getValue() {
		return this.value;
	}

}
