package org.jxjz.common.util.secret;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
public class DES3Util {
private static final String Algorithm = "DESede"; //定义 加密算法,可用 DES,DESede,Blowfish
	private static String key= "A3F2569DESJEIWBCJOTY45DY";//获得密钥的参数     //对base64编码的string解码成byte数组  

	 /**
	  * src为被加密的数据缓冲区（源）keybyte为加密密钥，长度为24字节
	  */
    public static byte[] encryptMode(byte[] keybyte, byte[] src) {
       try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            //加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * keybyte为加密密钥，长度为24字节src为加密后的缓冲区
     */
    public static byte[] decryptMode(byte[] keybyte, byte[] src) {      
    try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            //解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * 转换成十六进制字符串
     */
    public static String byte2hex(byte[] b) {
        String hs="";
        String stmp="";
        for (int n=0;n<b.length;n++) {
            stmp=(java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length()==1) hs=hs+"0"+stmp;
            else hs=hs+stmp;
            if (n<b.length-1)  hs=hs+":";
        }
        return hs.toUpperCase();
    }
    /**
	 * 将16进制字节数组转化成字符串
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
	
    /**
	 * 加密
	 */
	public static String encrypt(String data,String key) {
		 byte[] encoded = encryptMode(key.getBytes(), data.getBytes());   
		 String ret=toHexString(encoded);
		return ret;
	}
    /**
	 * 加密
	 */
	public static String decrypt(String data,String key) {
		 byte[] encoded = decryptMode(key.getBytes(), data.getBytes());   
		 String ret=toHexString(encoded);
		return ret;
	}		
	
	
    public static void main(String[] args)
    {
        //添加新安全算法,如果用JCE就要把它添加进去
        final byte[] keyBytes = key.getBytes();    //24字节的密钥
        String szSrc = "q";
        System.out.println("加密前的字符串:" + szSrc);
       
        byte[] encoded = encryptMode(keyBytes, szSrc.getBytes());        
        System.out.println("加密后的字符串:" + toHexString(encoded));
        
        byte[] srcBytes = decryptMode(keyBytes, encoded);
        System.out.println("解密后的字符串:" + (new String(srcBytes)));
    }
    
}
