package org.jxjz.framework.enums;


/**
 * 设置  是否超级管理员
 * @author  lihaijun
 * 操作类型    0 新增    1 修改    2 删除    3 查询    4 上传    5 下载  6 登录  7  退出      可以自定义
 * 2012-10-18
 */
public enum LogType implements IHasValueEnum{
	Add("0"),Update("1"),Delete("2"),Search("3"),Upload("4"),Download("5"),Login("6"),Logout("7");
	private String value;
	private LogType(String value){
		this.value = value;
	}
	
	public String getName() {
		switch (this) {
		case Add:
			return "新增";
		case Update:
			return "修改";
		case Delete:
			return "删除";
		case Search:
			return "查询";
		case Upload:
			return "上传";
		case Download:
			return "下载";
		case Login:
			return "登录";
		case Logout:
			return "退出";	
			
		default:
			break;
		}
		return null;
	}

	public String getValue() {
		return this.value;
	}

}
