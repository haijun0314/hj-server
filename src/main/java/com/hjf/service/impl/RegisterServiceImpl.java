package com.hjf.service.impl;

/**
 * 登录注册服务
 */
import javax.annotation.Resource;

import org.jxjz.common.util.web.CookieUtil;
import org.jxjz.common.util.web.SessionUtil;
import org.jxjz.framework.util.ConfigUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjf.core.bean.reqBean.RegisterReqBean;
import com.hjf.core.bean.respBean.RegisterRespBean;
import com.hjf.core.util.AccountUtil;
import com.hjf.core.util.CodeUtil;
import com.hjf.entity.Account;
import com.hjf.service.AccountService;
import com.hjf.service.RegisterService;
import com.hjf.service.SmsService;
@Service
@Transactional 
public class RegisterServiceImpl extends BaseService  implements RegisterService {
	@Resource  AccountService   	accountService;
	@Resource  SmsService 		    smsService;
 
	/**
	 * 账户注册
	 * 1、检查是否已经注册过
	 * 2、验证短信验证码
	 * 3、准备昵称
	 * 4、创建账户
	 * 3、添加注册用户缓存
	 */
	public RegisterRespBean register( RegisterReqBean  q,RegisterRespBean r ){
		boolean ret =smsService.smsCheck(q.getTelephone(),q.getSmsCode());
		if (!ret) {
			r.fail( CodeUtil.e_1006);
			log.error("【账户注册】"+q.getTelephone()+" 短信验证失败");
			return r;
		}
		//检查是否已经注册过
		Account c=accountService.getAccountByTelephone(q.getTelephone());
		if (c!=null) {
			r.fail(CodeUtil.e_1007);
			log.error("【账户注册】"+q.getTelephone()+" 该账户已经注册   不能重复注册");
			return r;
		}
	
		try {
			//创建账户
			Account a=AccountUtil.createAccount(q);
			int accountId=accountService.addAccount(a);
			if (accountId<0) {
				r.fail(CodeUtil.e_1005);
				log.info("【账户注册】失败"+q.getTelephone());
				return r;
			}
			a.setAccountId(accountId);;
			r.setAccountId(accountId);
			AccountUtil.createToken(a);
			SessionUtil.setSession(ConfigUtil.Session_Login_User, a);
			CookieUtil.setCookie(ConfigUtil.Cookie_Login_User, a.getUserToken());
		} catch (Exception e) {
			log.error("【账户注册】...发生异常..."+e.getMessage());
			e.getMessage();
			r.fail(CodeUtil.e_1005);
			return r;
		}
		r.success();
		return r;
	}
	

}
