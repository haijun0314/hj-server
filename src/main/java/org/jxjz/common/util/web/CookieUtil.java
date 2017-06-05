package org.jxjz.common.util.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jxjz.base.spring.WebUtils;
import org.jxjz.common.util.EscapeEncode;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CookieUtil
{
  public static final String COOKIE_PAGE_SIZE = "_cookie_page_size";
  public static final int DEFAULT_SIZE = 20;
  public static final int MAX_SIZE = 200;
  
  public static int getPageSize(HttpServletRequest request)
  {
    Assert.notNull(request);
    Cookie cookie = getCookie(request, "_cookie_page_size");
    int count = 0;
    if (cookie != null)
      try {
        count = Integer.parseInt(cookie.getValue());
      }
      catch (Exception e) {
      }
    if (count <= 0)
      count = 20;
    else if (count > 200) {
      count = 200;
    }
    return count;
  }

  public static Cookie getCookie(HttpServletRequest request, String name)
  {
    Assert.notNull(request);
    Cookie[] cookies = request.getCookies();
    if ((cookies != null) && (cookies.length > 0)) {
      for (Cookie c : cookies) {
        if (c.getName().equals(name)) {
          return c;
        }
      }
    }
    return null;
  }

  public static Cookie addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value)
  {
    Cookie cookie = new Cookie(name, value);
    String ctx = request.getContextPath();
    if (StringUtils.isBlank(ctx)) {
      cookie.setPath("/");
    }
    else {
      cookie.setPath(ctx);
    }
    response.addCookie(cookie);
    return cookie;
  }

  public static void cancleCookie(HttpServletResponse response, String name, String domain)
  {
    Cookie cookie = new Cookie(name, null);
    cookie.setMaxAge(0);
    cookie.setPath("/");
    if (!(StringUtils.isBlank(domain))) {
      cookie.setDomain(domain);
    }
    response.addCookie(cookie);
  }
  
  
	/**
	 * 获取Cookie
	 */
	public static synchronized String getCookie(String key) {
		Cookie[] cookies = getCookie();
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (key.equalsIgnoreCase(cookies[i].getName())) {
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}

	/**
	 * 获取Cookies
	 * 
	 * @return
	 */
	public static synchronized Cookie[] getCookie() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getCookies();
	}

	/**
	 * 保存cookie,默认有效时间为1天
	 */
	public static synchronized void setCookie(HttpServletResponse response,String key, String value){
		setCookie(response, key, value, 0);
	}
	/**
	 * 保存cookie,默认有效时间为1天
	 */
	public static synchronized void setCookie(String key, String value){
	    HttpServletResponse response = WebUtils.getResponse();
	    
		setCookie(response, key, value, 0);
	}
	
	
	/**
	 * 保存cookie
	 */
	public static synchronized void setCookie(HttpServletResponse response,String key, String value, int day){
		value = EscapeEncode.escape(value);
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		if (day > 0) {
			cookie.setMaxAge(60 * 60 * 24 * day);
		}
		response.addCookie(cookie);
	}

	public static synchronized void removeCookie(HttpServletResponse response,String key) {
		Cookie[] cookies = getCookie();
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (key.equalsIgnoreCase(cookies[i].getName())) {
					Cookie cookie = new Cookie(key, null);
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					break;
				}
			}
		}
	} 
   
}
