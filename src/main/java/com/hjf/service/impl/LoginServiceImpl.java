package com.hjf.service.impl;

/**
 * 【用户登录】
 */
import javax.annotation.Resource;

import org.jxjz.common.util.web.CookieUtil;
import org.jxjz.common.util.web.SessionUtil;
import org.jxjz.framework.util.ConfigUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjf.core.bean.reqBean.LoginReqBean;
import com.hjf.core.bean.respBean.LoginRespBean;
import com.hjf.core.util.AccountUtil;
import com.hjf.core.util.CodeUtil;
import com.hjf.dao.AccountDAO;
import com.hjf.entity.Account;
import com.hjf.service.LoginService;
@Service
@Transactional 
public class LoginServiceImpl extends BaseService  implements LoginService {
	@Resource AccountDAO accountDAO;
	/**
	 * 【用户登录】
	 */
	public LoginRespBean weblogin(LoginReqBean q,LoginRespBean     r ){
		log.info("登录操作开始....账户"+q.getTelephone());
		Account  lo=new Account();
		lo.setPassword(q.getPassword());
		lo.setTelephone(q.getTelephone());
		Account c =(Account) accountDAO.getObjById(lo, "login");
		if (c==null) {
			log.error("【用户登录】...登录失败....没有查询到该账户信息");
			r.fail(CodeUtil.e_1003);
			return r;
		}
		AccountUtil.createToken(c);
		SessionUtil.setSession(ConfigUtil.Session_Login_User, c);
		CookieUtil.setCookie(ConfigUtil.Cookie_Login_User, c.getUserToken());
		copyProperties(r, c);
		r.success();
		return r;
	}
	
	 
	

}
