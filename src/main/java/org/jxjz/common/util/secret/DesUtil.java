package org.jxjz.common.util.secret;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DesUtil {

	/**
	 * 解密数据
	 * @param message数据
	 * @param key秘钥
	 */
	public static String decrypt(String message, String key) {
		try {
			byte[] bytesrc = convertHexString(message);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
			cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
			byte[] retByte = cipher.doFinal(bytesrc);
			return new String(retByte);
		} catch (Exception e) {
			return "";
		}

	}

	 

	/**
	 * 加密数据
	 * @param message数据
	 * @param key 秘钥
	 */
	public static String encrypt(String message, String key) {
		try {
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
			return toHexString(cipher.doFinal(message.getBytes("UTF-8")));
		} catch (Exception e) {
			e.getMessage();
			return "";
		}

	}	
	
	
	
	/**
	 * 将字符串转换成16进制字节
	 * @param str
	 * @return
	 */
	public static byte[] convertHexString(String str) {
		byte digest[] = new byte[str.length() / 2];
		for (int i = 0; i < digest.length; i++) {
			String byteString = str.substring(2 * i, 2 * i + 2);
			int byteValue = Integer.parseInt(byteString, 16);
			digest[i] = (byte) byteValue;
		}

		return digest;
	}

	/**
	 * 将16进制字节数组转化成字符串
	 * @param b
	 * @return
	 */
	public static String toHexString(byte b[]) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String plainText = Integer.toHexString(0xff & b[i]);
			if (plainText.length() < 2)
				plainText = "0" + plainText;
			hexString.append(plainText);
		}
		return hexString.toString();
	}
	

    public static void main(String[] args) throws Exception
    {
        String  key="A3F2569D";
        String encoded = encrypt("123456",key);        
        System.out.println("加密后的字符串:" + encrypt("123456",key));
        System.out.println("加密后的字符串:" + decrypt("3040b254ce578ea07534d0c632fb6bb8", key));
        

    }	
	
	
	

}
