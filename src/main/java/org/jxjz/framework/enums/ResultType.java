package org.jxjz.framework.enums;


/**
 * 设置  系统操作结果   返回类型【0 失败 1 成功 2 消息】
 * @author  lihaijun
 * 2012-10-18
 */
public enum ResultType implements IHasValueEnum{
	Success("1"),Fail("0"),Msg("2");
	private String value;
	private ResultType(String value){
		this.value = value;
	}
	
	public String getName() {
		switch (this) {
		case Success:
			return "成功";
		case Fail:
			return "失败";
		case Msg:
			return "其它";
		default:
			break;
		}
		return null;
	}

	public String getValue() {
		return this.value;
	}

}
