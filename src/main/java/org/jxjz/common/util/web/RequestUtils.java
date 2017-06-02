package org.jxjz.common.util.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jxjz.common.util.URLUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UrlPathHelper;
/**
 * 【功能说明】：Request  通用处理工具类
 * @author lihaijun
 * 【创建时间】2017-05-25
 */
public class RequestUtils {
    public static Logger logger = Logger.getLogger(RequestUtils.class);
    
    public static HttpServletRequest getRequest() {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	return request;
    }
    
    /**
     * 获取客户端IP地址，此方法用在proxy环境中
     */
    public static String getRemoteAddr(HttpServletRequest req) {
        String ip = req.getHeader("X-Forwarded-For");
        if(StringUtils.isNotBlank(ip)){
            String[] ips = StringUtils.split(ip,',');
            if(ips!=null){
                for(String tmpip : ips){
                    if(StringUtils.isBlank(tmpip))
                        continue;
                    tmpip = tmpip.trim();
                    if(isIPAddr(tmpip) && !tmpip.startsWith("10.") && !tmpip.startsWith("192.168.") && !"127.0.0.1".equals(tmpip)){
                        return tmpip.trim();
                    }
                }
            }
        }
        ip = req.getHeader("x-real-ip");
        if(isIPAddr(ip))
            return ip;
        ip = req.getRemoteAddr();
        if(ip.indexOf('.')==-1)
            ip = "127.0.0.1";
        return ip;
    }
 
    /**
     * 判断是否为搜索引擎
     */
    public static boolean isRobot(HttpServletRequest req){
        String ua = req.getHeader("user-agent");
        if(StringUtils.isBlank(ua)) return false;
        return (ua != null
                && (ua.indexOf("Baiduspider") != -1 || ua.indexOf("Googlebot") != -1
                        || ua.indexOf("sogou") != -1
                        || ua.indexOf("sina") != -1
                        || ua.indexOf("iaskspider") != -1
                        || ua.indexOf("ia_archiver") != -1
                        || ua.indexOf("Sosospider") != -1
                        || ua.indexOf("YoudaoBot") != -1
                        || ua.indexOf("yahoo") != -1
                        || ua.indexOf("yodao") != -1
                        || ua.indexOf("MSNBot") != -1
                        || ua.indexOf("spider") != -1
                        || ua.indexOf("Twiceler") != -1
                        || ua.indexOf("Sosoimagespider") != -1
                        || ua.indexOf("naver.com/robots") != -1
                        || ua.indexOf("Nutch") != -1
                        || ua.indexOf("spider") != -1));    
    }
 
    /**
     * 获取用户访问URL中的根域名
     * 例如: www.dlog.cn -> dlog.cn
     */
    public static String getDomainOfServerName(String host){
        if(isIPAddr(host))
            return null;
        String[] names = StringUtils.split(host, '.');
        int len = names.length;
        if(len==1) return null;
        if(len==3){
            return makeup(names[len-2],names[len-1]);
        }
        if(len>3){
            String dp = names[len-2];
            if(dp.equalsIgnoreCase("com")||dp.equalsIgnoreCase("gov")||dp.equalsIgnoreCase("net")||dp.equalsIgnoreCase("edu")||dp.equalsIgnoreCase("org"))
                return makeup(names[len-3],names[len-2],names[len-1]);
            else
                return makeup(names[len-2],names[len-1]);
        }
        return host;
    }
 
    /**
     * 判断字符串是否是一个IP地址
     */
    public static boolean isIPAddr(String addr){
        if(StringUtils.isEmpty(addr))
            return false;
        String[] ips = StringUtils.split(addr, '.');
        if(ips.length != 4)
            return false;
        try{
            int ipa = Integer.parseInt(ips[0]);
            int ipb = Integer.parseInt(ips[1]);
            int ipc = Integer.parseInt(ips[2]);
            int ipd = Integer.parseInt(ips[3]);
            return ipa >= 0 && ipa <= 255 && ipb >= 0 && ipb <= 255 && ipc >= 0
                    && ipc <= 255 && ipd >= 0 && ipd <= 255;
        }catch(Exception e){}
        return false;
    }
     
    private static String makeup(String...ps){
        StringBuilder s = new StringBuilder();
        for(int idx = 0; idx < ps.length; idx++){
            if(idx > 0)
                s.append('.');
            s.append(ps[idx]);
        }
        return s.toString();
    }
 
    
    
     
    /**
     * 获取浏览器提交的字符串参
     */
    public static String getParam(HttpServletRequest req, String param){
        return req.getParameter(param);
    }
   
	
	/**
	 * 获取http提交请求参数
	 */
	public static Map getParameters(HttpServletRequest request){
		Map<String, String> hmInput = null;
		String paramName 			= null;
		String paramValue 			= null;
		// 获取接口请求的所有参数名、参数值
		Enumeration enm 			= request.getParameterNames();
		hmInput 					= new HashMap<String, String>();
		while (enm.hasMoreElements()) {
			paramName 				= enm.nextElement().toString();
			paramValue 				= request.getParameter(paramName);
			try{
				paramValue = java.net.URLDecoder.decode(paramValue, "UTF-8");
			}catch(Exception e){
				e.printStackTrace();
			}
			if (StringUtils.isNotBlank(paramValue)) {
				hmInput.put(paramName, paramValue);
			}
		}
		return hmInput;
	}     
     
    
    public static Map getParameter(ServletRequest request, String[] paras) {
        HashMap map = new HashMap();
        if ((paras == null) || (paras.length == 0)) {
            return map;
        }
        for (int i = 0; i < paras.length; i++) {
            String para = paras[i];
            if ((para == null) || (para.trim().length() == 0)) {
                continue;
            }
            String value = request.getParameter(para);
            if ((value == null) || (value.trim().length() == 0)) {
                continue;
            } else {
                value = value.trim();
            }
            map.put(para, URLUtil.decodeURL(value));
        }
        return map;
    }

    public static String getParamValue(HttpServletRequest request, String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        if (request.getMethod().equalsIgnoreCase("POST")) {
            return request.getParameter(name);
        }
        String s = request.getQueryString();
        if (StringUtils.isBlank(s)) {
            return null;
        }
        try {
            s = URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("encoding UTF-8 not support?", e);
        }
        String[] values = (String[]) parseParamStr(s).get(name);
        if ((values != null) && (values.length > 0)) {
            return values[(values.length - 1)];
        }
        return null;
    }

    public static Map<String, String[]> parseParamStr(String s) {
        String[] valArray = null;
        if (s == null) {
            throw new IllegalArgumentException();
        }
        Map ht = new HashMap();
        StringTokenizer st = new StringTokenizer(s, "&");
        while (st.hasMoreTokens()) {
            String pair = st.nextToken();
            int pos = pair.indexOf(61);

            if (pos == -1) {
                continue;
            }
            String key = pair.substring(0, pos);
            String val = pair.substring(pos + 1, pair.length());
            if (ht.containsKey(key)) {
                String[] oldVals = (String[]) (String[]) ht.get(key);
                valArray = new String[oldVals.length + 1];

                for (int i = 0; i < oldVals.length; ++i) {
                    valArray[i] = oldVals[i];
                }

                valArray[oldVals.length] = val;
            } else {
                valArray = new String[1];
                valArray[0] = val;
            }
            ht.put(key, valArray);
        }
        return ht;
    }

    

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");

        if ((!(StringUtils.isBlank(ip))) &&
                (!("unknown".equalsIgnoreCase(ip)))) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if ((!(StringUtils.isBlank(ip))) &&
                (!("unknown".equalsIgnoreCase(ip)))) {
            int index = ip.indexOf(44);

            if (index != -1) {
                return ip.substring(0, index);
            }

            return ip;
        }

        return request.getRemoteAddr();
    }

    public static String getLocation(HttpServletRequest request) {
        UrlPathHelper helper = new UrlPathHelper();
        StringBuffer buff = request.getRequestURL();
        String uri = request.getRequestURI();
        String origUri = helper.getOriginatingRequestUri(request);
        buff.replace(buff.length() - uri.length(), buff.length(), origUri);
        String queryString = helper.getOriginatingQueryString(request);
        if (queryString != null) {
            buff.append("?").append(queryString);
        }
        return buff.toString();
    }

    public static String getRequestedSessionId(HttpServletRequest request) {
        String sid = request.getRequestedSessionId();
        String ctx = request.getContextPath();
        if ((request.isRequestedSessionIdFromURL()) ||
                (StringUtils.isBlank(ctx))) {
            return sid;
        }
        Cookie cookie = CookieUtil.getCookie(request, "JSESSIONID");
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }
   
	 
	public static String getParams(Map  hm) {		
		StringBuffer sb = new StringBuffer();
		try {		
			if (hm.isEmpty()) {
				return null ;
			}				
			Object[] key = hm.keySet().toArray(); 
			Arrays.sort(key); 
			for (int i = 0; i < key.length; i++) { 
				Object  o=hm.get(key[i]);
				if (o==null) {
					o="";
				}
				sb.append(key[i]+"="+java.net.URLEncoder.encode(o.toString(),"UTF-8")+"&");	
			} 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return  sb.toString();
	}   
     
}
