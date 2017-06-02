package com.hjf.service;

import com.hjf.core.bean.reqBean.RegisterReqBean;
import com.hjf.core.bean.respBean.RegisterRespBean;

public interface RegisterService {
	 
	//【账户注册】
	public RegisterRespBean register( RegisterReqBean  q,RegisterRespBean r ) ;
	 
	 
}
