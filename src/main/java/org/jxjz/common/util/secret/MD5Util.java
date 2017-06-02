package org.jxjz.common.util.secret;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5  加密工具类
 * @author Administrator
 *
 */
public class MD5Util {

	  public static void main(String[] argv) throws Exception {
	      System.out.println(encryption("appid=wx31b7a7b51f928cc0&body=用户充值&mch_id=1311259301&nonce_str=0fa3ae336b624a8abd3326f2057098ce&notify_url=http://192.168.1.156:8080//pay?wxPayNotify&out_trade_no=20160201104547349250&spbill_create_ip=192.168.1.150&total_fee=1&trade_type=APP&key=2644j469x739j9476z809e743d215837").toUpperCase());
	  }	 
	
	/**
	 * @param buffer
	 * @param key
	 * @return
	 */
	public static byte[] getKeyedDigest(byte[] buffer, byte[] key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(buffer);
            return md5.digest(key);
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }
	
	/**
	 * GBK  参数加密 需要加密密钥
	 */
	public static String encryptionGBK(String strSrc, String key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strSrc.getBytes("GBK"));
            
            String result="";
            byte[] temp;
            temp=md5.digest(key.getBytes("GBK"));
    		for (int i=0; i<temp.length; i++){
    			result+=Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
    		}
    		return result;
    		
        } catch (NoSuchAlgorithmException e) {
        	
        	e.printStackTrace();
        	
        }catch(Exception e)
        {
          e.printStackTrace();
        }
        return null;
    }		
	
	 
	
	
	/**
	 * UTF8 参数加密 需要加密密钥
	 */
	public static String encryptionUTF8(String strSrc, String key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strSrc.getBytes("UTF-8"));
            String result="";
            byte[] temp;
            temp=md5.digest(key.getBytes("UTF-8"));
    		for (int i=0; i<temp.length; i++){
    			result+=Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
    		}
    		
    		return result;
    		
        } catch (NoSuchAlgorithmException e) {
        	
        	e.printStackTrace();
        	
        }catch(Exception e)
        {
          e.printStackTrace();
        }
        return null;
    }

	
	
	/**
	 * 参数加密   不需要密钥
	 */
	public static String encryption(String strSrc) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strSrc.getBytes("UTF-8"));
            String result="";
            byte[] temp;
            temp=md5.digest();
    		for (int i=0; i<temp.length; i++){
    			result+=Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
    		}
    		return result;
        } catch (NoSuchAlgorithmException e) {
        	e.printStackTrace();
        	
        }catch(Exception e)
        {
          e.printStackTrace();
        }
        return null;
    }	
	 

}
