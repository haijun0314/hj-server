package org.jxjz.framework.aop;


import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jxjz.common.util.web.SessionUtil;
import org.jxjz.framework.exception.ErrorCodeUtil;
import org.jxjz.framework.exception.RepeatException;
import org.jxjz.framework.util.ConfigUtil;
import org.springframework.stereotype.Component;

import com.hjf.core.bean.BaseReqBean;
import com.hjf.service.impl.BaseService;

/**
 * 【功能说明】：aop切面判断重复提交  根据提交参数reqNO 是否重复验证是否重复提交
 * @author lihaijun
 * 【创建时间】2017-05-25
 */
@Aspect
@Component
public class ReqNoAspect extends BaseService{
	 /**
	  * 定义切面  凡是在方法上添加了@CheckReqNo的都需要验证reqNO来判断是否重复提交
	  */
	@Pointcut("@annotation(org.jxjz.framework.aop.annotation.CheckReqNo)")
	public void checkRepeat(){
		 
	}

	@Before("checkRepeat()")
	public void before(JoinPoint joinPoint) throws Exception {
		 String target= joinPoint.getTarget().getClass().getName();
		 String method = joinPoint.getSignature().getName(); 
		 log.info("【验证重复提交AOP】"+"target="+target+"....method="+method);
		 Object[] arguments = joinPoint.getArgs();
		 BaseReqBean q=(BaseReqBean) arguments[0];
		 String reqNO=q.getReqNO();
		 log.info("【验证重复提交AOP】"+"reqNO="+reqNO);
		 if (StringUtils.isBlank(reqNO)) {
			 throw new RepeatException(ErrorCodeUtil.e_9990);
		 }
		 if (ConfigUtil.isSessionLocal()) {
			 String reqNO_=(String) SessionUtil.getSession("reqNO");
			 if (StringUtils.isNotBlank(reqNO_)&&reqNO_.equals(reqNO)) {
				 throw new RepeatException(ErrorCodeUtil.e_9990);
			}
			 SessionUtil.setSession("reqNO", reqNO);
		 }
	}
	
	  
 
}
