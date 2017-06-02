package org.jxjz.common.util.secret;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * 加密工具类
 * 
 * @author  
 * 
 */
public class SecurityUtil {

	private static final String DES_MODE = "DES/CBC/PKCS5Padding";
	private static final String AES_MODE = "AES/CBC/PKCS5Padding";

	private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };

	/**
	 * 格式化des的key，只能为8位
	 * 
	 * @param key
	 * @return
	 */
	private static String formateDesKey(String key) {
		if (key == null) {
			key = "";
		}
		if (key.length() != 8) {
			key = (key + "wgbox123oop9p89k").substring(0, 8);
		}
		return key;
	}

	/**
	 * 格式化des的key，只能为8位
	 * 
	 * @param key
	 * @return
	 */
	private static String formateAesKey(String key) {
		if (key == null) {
			key = "";
		}
		if (key.length() != 16) {
			key = (key + "wgbox123oop9p89k").substring(0, 16);
		}
		return key;
	}

	/**
	 * 进行DES加密，加密模式cbc，填充模式PKCS5Padding，返回结果为Base64格式字符串。密钥必须为8位，若不是8位，程序自动调整为8位
	 * 。加密失败，返回null
	 * 
	 * @param input
	 * @param strKey
	 * @return
	 */
	public static String desEncrypt(String input, String strKey) {
		String result = null;
		result = getString(getEncDESByte(input, strKey));
		// Cipher cipher;
		// try {
		// cipher = Cipher.getInstance(DES_MODE);
		// cipher.init(Cipher.ENCRYPT_MODE, getKey(strKey));
		// result = getString(cipher.doFinal(input.getBytes("utf-8")));
		// }
		// catch(Exception e) {
		// e.printStackTrace();
		// }
		return result;
	}

	/**
	 * 进行DES加密，返回结果字节组。密钥必须为8位，若不是8位，程序自动调整为8位。加密失败，返回null
	 * 
	 * @param input
	 * @param strKey
	 * @return
	 */
	private static byte[] getEncDESByte(String input, String strKey) {
		// LogUtil.d("开始对字符串进行MD5:" + input);
		byte[] result = null;
		Cipher cipher;

		try {
			strKey = formateDesKey(strKey);
			iv = strKey.getBytes();
			IvParameterSpec zeroIv = new IvParameterSpec(iv);
			SecretKeySpec key = new SecretKeySpec(strKey.getBytes(), "DES");

			cipher = Cipher.getInstance(DES_MODE);
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			result = (cipher.doFinal(input.getBytes("utf-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 进行DES解密，返回结果为普通字符串。密钥必须为8位，若不是8位，程序自动调整为8位。解密失败，返回null
	 * 
	 * @param input
	 * @param strKey
	 * @return
	 */
	public static String desDecrypt(String input, String strKey) {
		String result = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance(DES_MODE);
			strKey = formateDesKey(strKey);
			iv = strKey.getBytes();
			IvParameterSpec zeroIv = new IvParameterSpec(iv);
			SecretKeySpec key = new SecretKeySpec(strKey.getBytes(), "DES");
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
			byte[] data = cipher.doFinal(getByte(input));
			result = new String(data, "utf-8");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 进行128位的AES加密，返回结果为base64字符串。加密失败，返回null
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param strKey
	 *            加密密码
	 * @return
	 */
	public static String aesEncrypt(String content, String strKey) {
		String result = null;
		try {
			strKey = formateAesKey(strKey);
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(strKey.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			iv = strKey.getBytes();
			IvParameterSpec zeroIv = new IvParameterSpec(iv);
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance(AES_MODE);// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);// 初始化
			byte[] data = cipher.doFinal(byteContent);
			result = getString(data); // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 进行128位的AES解密，返回结果为普通字符串。解密失败，返回null
	 * 
	 * @param input
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static String aesDecrypt(String input, String password) {
		String result = null;
		try {
			password = formateAesKey(password);
			byte[] content = getByte(input);
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			iv = password.getBytes();
			IvParameterSpec zeroIv = new IvParameterSpec(iv);
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance(AES_MODE);// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);// 初始化
			byte[] data = cipher.doFinal(content);
			result = new String(data, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getString(byte[] data) {
		return Base64.encode(data);
	}

	public static byte[] getByte(String data) {
		return Base64.decode(data);
	}
}
