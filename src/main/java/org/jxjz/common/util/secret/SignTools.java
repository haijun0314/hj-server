package org.jxjz.common.util.secret;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jxjz.common.util.HttpUtil;
import org.jxjz.common.util.StringUtil;
import org.jxjz.common.util.TimeUtil;


 
public class SignTools {
	public static Logger log = Logger.getLogger(SignTools.class);
	public static void main(String[] args)throws Exception  {
        String url="http://192.168.0.156:8001/t?sign";
		String timestamp=TimeUtil.getDateYMDHMS();
		String nonce=StringUtil.randNumStr(10);
		String params="&id=222&name=haijun&timestamp="+timestamp+"&nonce="+nonce; 
		
		Map map=new HashMap();  
		map.put("id", "222");
		map.put("name", "摇一摇");
		map.put("timestamp", timestamp);
		map.put("nonce",nonce);
		
		String hmac=SignTools.sign(map, "HS201703");
		url=url+params+"&signature="+hmac;
		String ret=HttpUtil.httpGet(url)  ;
        System.out.print(ret);
	}
	
	/**
	 * 对接口请求进行验证
	 */
	public static boolean checkSign (Map<String,String> hm, String hmac, String appkey) {		
		try {		
			if (hm.isEmpty()) {
				return false ;
			}				
			Object[] key = hm.keySet().toArray(); 
			Arrays.sort(key); 
			StringBuffer sBuf = new StringBuffer ();
			for (int i = 0; i < key.length; i++) { 
				sBuf.append(key[i]+"="+java.net.URLDecoder.decode(hm.get(key[i])==null?"":hm.get(key[i]),"UTF-8")+"&");
			} 
			String orgStr = "appSecret="+appkey+"&"+sBuf.substring(0,sBuf.length()-1); 
			String signStr = MD5Util.encryption(orgStr);	
			log.error("签名原始数据原始数据...."+orgStr);
			log.error("签名后数据:"+signStr);
			if (signStr != null && signStr.equals(hmac)) {
				log.error("签名成功");
				return true ;
			}	else {
				log.error("签名失败");
			}	
		}catch(Exception e) {
			log.error("签名失败 发生异常"+e.getMessage());
			e.printStackTrace();
		}
		return false ;
	}
	
	
	
	/**
	 * 对传递过来的参数签名然后返回组合的字符串
	 */
	public static String sign(Map<String,String> hm,String appSecret) {		
		StringBuffer sb = new StringBuffer();
		sb.append("appSecret="+appSecret+"&");
		try {		
			if (hm.isEmpty()) {
				return null ;
			}				
			Object[] keys = hm.keySet().toArray(); 
			Arrays.sort(keys); 
			for (int i = 0; i < keys.length; i++) { 
				//sb.append(keys[i]+"="+java.net.URLEncoder.encode(hm.get(keys[i])==null?"":hm.get(keys[i]),"UTF-8")+"&");	
				sb.append(keys[i]+"="+hm.get(keys[i])+"&");	
			} 
		}catch(Exception e) {
			e.printStackTrace();
		}
		String signStr=MD5Util.encryption(sb.substring(0,sb.length()-1));
		return  signStr; 
	}
	
	 
	
}
