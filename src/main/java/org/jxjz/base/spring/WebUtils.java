package org.jxjz.base.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 总结一下这样做的好处，其实springmvc有自己的HttpServletRequest和HttpServletResponse。但是，软件分层之后，不能再controller层得到的Session放在参数里一直传下去吧。所以，
 * 这样直接保存一下HttpServletRequest、HttpServletResponse和HttpSession相对来讲还是很划算的。
 */
public class WebUtils {
	 private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();  
	 private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();  
	  
	    public static HttpServletRequest getRequest() {  
	        return (HttpServletRequest) requestLocal.get();  
	    }  
	  
	    public static void setRequest(HttpServletRequest request) {  
	        requestLocal.set(request);  
	    }  
	  
	    public static HttpServletResponse getResponse() {  
	        return (HttpServletResponse) responseLocal.get();  
	    }  
	  
	    public static void setResponse(HttpServletResponse response) {  
	        responseLocal.set(response);  
	    }  
	  
	    public static HttpSession getSession() {  
	        return (HttpSession) ((HttpServletRequest) requestLocal.get()).getSession();  
	    }  
}

