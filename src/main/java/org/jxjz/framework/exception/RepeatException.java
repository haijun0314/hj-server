package org.jxjz.framework.exception;
/**
 * 【功能说明】：重复提交异常处理类
 * @author lihaijun
 * 【创建时间】2017-05-25
 */
public class RepeatException  extends  RuntimeException{

	public String err_msg="";	
	public String err_code="";
	public RepeatException(){
		
	}
	
	public RepeatException(String code){
		this.err_code=code;
		this.err_msg=ErrorCodeUtil.getCodeMsg(code);
	}

	public String getErr_msg() {
		return err_msg;
	}

	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	
	
	
	
}
