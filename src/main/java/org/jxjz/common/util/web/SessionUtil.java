package org.jxjz.common.util.web;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jxjz.framework.cache.redis.RedisCacheUtil;
import org.jxjz.framework.util.ConfigUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hjf.core.util.KeysUtil;
import com.hjf.entity.Account;

/**
 * 【功能说明】：Session工具类
 * @author lihaijun
 * 【创建时间】2017-05-25
 */
public class SessionUtil {
	public static RedisCacheUtil  rc=new RedisCacheUtil();
	public  static int sessionValidTime=1800;//session  有效期单位 秒
	
	/**
	 *  获取Session
	 *  name 如果是本地session    表示固定值session_login_user
	 *  如果是远程session  redis  表示手机号
	 */
	public static synchronized Serializable getSession(String name) {
		// 生产模式：保存在redis中
		if (ConfigUtil.isSessionLocal()) {
			return getLocalSession().get(name);
		} else {
			return rc.getStr(name);
		}
	}
	
	/**
	 *  获取Session
	 *  name 如果是本地session    表示固定值session_login_user
	 *  如果是远程session  redis  表示手机号
	 */
	public static synchronized Serializable getRemoteSession(String telephone) {
		return rc.getStr(KeysUtil.getSession_login_User(telephone));
	}

	/**
	 *  获取Session
	 *  name 如果是本地session    表示固定值session_login_user
	 *  如果是远程session  redis  表示手机号
	 */
	public static synchronized Serializable getLocalSession(String name) {
		return getLocalSession().get(name);
	}	
	
	
	/**
	 * 获取本地session
	 */
	public static synchronized Map<String, Serializable> getLocalSession(){
		// 生产模式：保存在redis中
		Map<String, Serializable> map = null;
		// 开发模式：保存在本地session中
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		map = new HashMap<String, Serializable>();
		HttpSession session = request.getSession();
		Enumeration en = request.getSession().getAttributeNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			map.put(key, (Serializable) session.getAttribute(key));
		}
		return map;
	}

	/**
	 * 设置Session
	 */
	public static synchronized void setSession(String key, Serializable obj){
		// 生产模式：保存在redis中
		if (!ConfigUtil.isSessionLocal()) {
			 rc.save(key,sessionValidTime, obj);
		} else {
			// 开发模式：保存在本地session中
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			session.setAttribute(key, obj);
		}
	}	

	/**
	 * 设置Session
	 */
	public static synchronized void setSession(String name, Account obj){
		// 生产模式：保存在redis中
		if (!ConfigUtil.isSessionLocal()) {
			 rc.save(KeysUtil.getSession_login_User(obj.getTelephone()),sessionValidTime, obj.getUserToken());
		} else {
			// 开发模式：保存在本地session中
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			session.setAttribute(name, obj);
		}
	}

	/**
	 * 设置Session
	 */
	public static synchronized void continueRedisSession(String telephone, String token){
		 rc.save(KeysUtil.getSession_login_User(telephone),sessionValidTime,token);
	}
	
	
	
	/**
	 * 清理session
	 */
	public static synchronized void removeSession(String name){
		if (!ConfigUtil.isSessionLocal()) {
			rc.remove(KeysUtil.getSession_login_User(name));
		} else {
			// 开发模式：保存在本地session中
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			session.removeAttribute(name);
		}
	}

	/**
	 * 清理session
	 */
	public static synchronized void clearSession(String name) {
		// 生产模式：保存在redis中
		if (!ConfigUtil.isSessionLocal()) {
			KeysUtil.getSession_login_User(name);
		} else {
			// 开发模式：保存在本地session中
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			@SuppressWarnings("rawtypes")
			Enumeration en = request.getSession().getAttributeNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				session.removeAttribute(key);
			}
		}
	}

}
