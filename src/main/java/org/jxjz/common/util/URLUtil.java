package org.jxjz.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class URLUtil {
	/** 
	 * 对url进行编码 
	 */
	public static String encodeURL(String url) {
		if(StringUtils.isBlank(url)){
			url="";
		}
		try {
			return URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}

	/** 
	 * 对url进行解码 
	 * @param url 
	 * @return 
	 */
	public static String decodeURL(String url) {
		if(StringUtils.isBlank(url)){
			url="";
		}
		try {
			return URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
	



    public static String buildFullRequestUrl(HttpServletRequest r) {
        return buildFullRequestUrl(r.getScheme(), r.getServerName(), r.getServerPort(), r.getRequestURI(), r.getQueryString());
    }
    public static String buildFullRequestUrl(String scheme, String serverName, int serverPort, String requestURI, String queryString) {
        scheme = scheme.toLowerCase();
        StringBuilder url = new StringBuilder();
        url.append(scheme).append("://").append(serverName);
        if ("http".equals(scheme)) {
            if (serverPort != 80) {
                url.append(":").append(serverPort);
            }
        } else if ("https".equals(scheme)) {
            if (serverPort != 443) {
                url.append(":").append(serverPort);
            }
        }
        url.append(requestURI);
        if (queryString != null) {
            url.append("?").append(queryString);
        }
        return url.toString();
    }
 
    public static String buildRequestUrl(HttpServletRequest r) {
        return buildRequestUrl(r.getServletPath(), r.getRequestURI(), r.getContextPath(), r.getPathInfo(),r.getQueryString());
    }

    private static String buildRequestUrl(String servletPath, String requestURI, String contextPath, String pathInfo,String queryString) {
        StringBuilder url = new StringBuilder();
        if (StringUtils.isNotBlank(servletPath)) {
        	servletPath=servletPath.substring(1);
            url.append(servletPath);
            if (pathInfo != null) {
                url.append(pathInfo);
            }
        } else {
            url.append(requestURI.substring(contextPath.length()));
        }
        if (queryString != null) {
        	queryString=StringUtils.split(queryString,"&")[0];
            url.append("?").append(queryString);
        }
        return url.toString();
    }
    
    
    
    public static boolean isValidRedirectUrl(String url) {
        return url != null && url.startsWith("/") || isAbsoluteUrl(url);
    }
    public static boolean isAbsoluteUrl(String url) {
        final Pattern ABSOLUTE_URL = Pattern.compile("\\A[a-z.+-]+://.*", Pattern.CASE_INSENSITIVE);

        return ABSOLUTE_URL.matcher(url).matches();
    }	
	
   	
	
	

}
