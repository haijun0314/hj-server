package org.jxjz.framework.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
@Service
public class ErrorCodeUtil
{
	/**
	 * 代码说明:起始数字
	 */
	private static Map<String,String>  codeMap=new HashMap<String, String>();
	public static String success="0000";//通用成功
	public static String error="1111"; //系统异常
	public static String token_err="3333"; //权限错误
	public static String doing="2222"; //任务处理中
	public static String noData="0001";//没有数据
	public static String e_9999="9999";//参数错误
	public static String e_9998="9998";//更新数据出错
	public static String e_9997="9997";//插入数据出错
	public static String e_9996="9996";//查询数据出错
	public static String e_9995="9995";//删除数据出错
	public static String e_9994="9994";//数据重复
	public static String e_9993="9993";//文件上传失败
	public static String e_9992="9992";//操作失败  有些地方不需要提示具体信息或者不方便透露  比如因为商户余额不足原因   导致不能领取优惠券 
	public static String e_9990="9990";//参数错误
	 
	public static void initCode(){
		codeMap.put("0000", "成功");
		codeMap.put("3333", "权限错误");
		codeMap.put("0001", "没有数据");
		codeMap.put("1111", "抱歉，操作失败");
		codeMap.put("9999", "参数错误");
		codeMap.put("9998", "更新数据出错");
		codeMap.put("9997", "插入数据出错");
		codeMap.put("9996", "查询数据出错");
		codeMap.put("9995", "删除数据出错");
		codeMap.put("9994", "数据重复");
		codeMap.put("9993", "文件上传失败");
		codeMap.put("9992", "操作失败");
		codeMap.put("9990", "输入信息有误");
		 
		 
	}
	
	
	public static String getCodeMsg(String code) {
		if (codeMap==null||codeMap.isEmpty()) {
			initCode();
		}
		return codeMap.get(code);
	}	 
	
}
