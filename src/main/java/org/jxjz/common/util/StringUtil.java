package org.jxjz.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
	
public class StringUtil { 
	
	
	/**
	 * 比较BigDecimal 数值
	 * @param v1
	 * @param v2
	 */
	 public boolean compareTo(BigDecimal v1,BigDecimal v2){
		 if(v1.compareTo(v2)>0){
			 return true;
		 }
		 if(v1.compareTo(v2)<0){
			 return false;
		 }
		 
		return false;
	 }	
	
	public static String   formatMoney(String money) {
		if (StringUtils.isBlank(money)) {
			return "";
		}
		DecimalFormat myformat = new DecimalFormat();
		myformat.applyPattern(".00");
		String ret=myformat.format(new Double(money));
		return ret;
	}	
	public static Double   formatMoney(Double money) {
		DecimalFormat myformat = new DecimalFormat();
		myformat.applyPattern(".00");
		String ret=myformat.format(money);
		money=new Double(ret);
		return money;
	}	
	
	


	
	public static String   createId(String prefix) {
		if (StringUtils.isNotBlank(prefix)) {
			return prefix+TimeUtil.getDateYMDHMS()+StringUtil.randNumStr(15);
		}
		return TimeUtil.getDateYMDHMS()+StringUtil.randNumStr(15);
	}	
	
	
	/**
	 * 分解字符串
	 * str 被分解的字符串
	 * type 分解类型 当传入","时就按逗号分解
	 */
	public static String[] split(String str,String type){
		String[] getString =str.split(type);
		return getString;
	}
	/**
	 * getStringTokenizer
	 * 分解字符串
	 * String str 被分解的字符串
	 * String type 分解类型 当传入","时就按逗号分解  
	 * 与split  区别 : 对于String.split()方法来说，直接传入参数"|"不能正确的分割字符串，需先对它进行转义。
	 * 而对于StringTokenizer类来说，就没有这个必要。同样的操作在遇到下列分割符时同样需要："+"、"*"、"$"、"\"。
	 */
	public static String[] tokenizer(String str,String type){
		StringTokenizer commaToker = new StringTokenizer(str, type);
		String[] result = new String[commaToker.countTokens()];
		int k=0;
		  while (commaToker.hasMoreTokens()) {
		   result[k] = commaToker.nextToken();
		  k++;
		  }
		return result;
	}
	/**
	 * 分解字符串 字符串转化为List列表
	 * String str 被分解的字符串
	 * String type 分解类型 当传入","时就按逗号分解
	 */
	public static List convertToToList(String str,String type){
		List<String> list=CollectUtil.newArrayList();
		if(str!=null&&str.length()>0){
			String[] getString =str.split(type);
			for(int k=0;k<getString.length;k++){
				list.add(getString[k]);
			}
		}
		return list;
	}
	
	/**
	 * listToString
	 * 将List集合转化为字符串
	 * List list 被分解的字符串
	 * String type 转化为以type分割的字符串
	 */
	public static String  convertToString(List list,String type){
		String str="";
		if(list!=null&&list.size()>0){
			Iterator it=list.iterator();
			for(int j=0;j<list.size();j++){
				String s=(String)it.next();
				if(j==list.size()-1){
					str+=s;//组成商品相册字符串
				}else{
					str+=s+type;//组成商品相册字符串
				}
			}
		}
		return str;
	}	
	
	/**
	 * String[]to String
	 * 将String数组转化为integer数组
	 * String str 被转化字符串数组
	 * Integer[] toInt 转化为Integer的数组
	 */
	public static String  convertToString(String[] str,String type){
		String result="";
		if(str!=null&&str.length>0){
			for(int i=0;i<str.length;i++){
				if(i==str.length-1){
					result+=str[i];//组成字符串
				}else{
					result+=str[i]+type;//组成字符串
				}
			}
		}
		return result;
	}	
	


	/**
	 * String[]to Integer[]
	 * 将String数组转化为integer数组
	 * String str 被转化字符串数组
	 * Integer[] toInt 转化为Integer的数组
	 */
	public static Integer[]  convertToIntegerArray(String[] str){
		Integer[] toInt=null;
		if(str!=null&&str.length>0){
			toInt=new Integer[str.length];
			for(int i=0;i<str.length;i++){
				toInt[i]=Integer.valueOf(str[i]);
			}
		}
		return toInt;
	}

	
	/**
	 * 去除字符串中重复的子串，按照type分割
	 */
	 public static String keepSeqUnique(String keys,String type)
	  {
	    String[] keysArray = StringUtils.split(keys, type);
	    List keyList = CollectUtil.newArrayList();
	    for (int i = 0; i < keysArray.length; i++) {
	      if (!keyList.contains(keysArray[i])) {
	        keyList.add(keysArray[i]);
	      }
	    }
	    StringBuilder keyBuf = new StringBuilder();
	    for (Iterator iter = keyList.iterator(); iter.hasNext(); ) {
	      keyBuf.append((String)iter.next());
	      if (iter.hasNext()) {
	        keyBuf.append(type);
	      }
	    }
	    return keyBuf.toString();
	  }	
	
	 

	/**
	 * 将strSource中的strFrom 替换为strTo
	 * @param strSource
	 * @param strFrom
	 * @param strTo
	 */
	public static String replace(String strSource, String strFrom, String strTo) {   
	    if (strSource == null) {       
	      return null;   
	    } 
	    int i = 0;
	    if ((i = strSource.indexOf(strFrom, i)) >= 0) {
	      char[] cSrc = strSource.toCharArray();
	      char[] cTo = strTo.toCharArray();
	      int len = strFrom.length(); 
	      StringBuffer buf = new StringBuffer(cSrc.length); 
	      buf.append(cSrc, 0, i).append(cTo);
	      i += len;   
	      int j = i;      
	      while ((i = strSource.indexOf(strFrom, i)) > 0) { 
	        buf.append(cSrc, j, i - j).append(cTo);  
	        i += len; 
	        j = i;       
	      }       
	      buf.append(cSrc, j, cSrc.length - j);
	      return buf.toString();
	    }
	    return strSource;
	  } 	
	
	
	
	/**
	 *  隐藏部分信息
	 *  将字符串str从第start位开始隐藏hideCount位
	 */
	public static String hideSomeChar(String str,Integer start,Integer hideCount,String type) {
		String resultStr="";
		if (start==null) {
			start=0;
		}
		if (hideCount==null) {
			hideCount=str.length()-start;
		}
		if (StringUtils.isBlank(str)) {
			return "";
		}
		if (str.length()<=start) {
			return str;
		}
		if (str.length()<=hideCount) {
			return str;
		}

		String b_=StringUtils.substring(str,0, start);
		String a_=StringUtils.substring(str,  start+hideCount);
		resultStr=b_+type+a_;
		return resultStr;
	}	

	
	/**
	 * 字符串头补字符
	 * @param source 源字符串
	 * @param length 总长度
	 * @param flag 字符
	 * @return length长度的字符串
	 */
	public static String appendToHead(String source, int length, String character) {
		if (source == null) {
			return null;
		} else if (source.length() > length || length < 1) {
			return source;
		}
		while (source.length() < length) {
			source = character + source;
		}
		return source;
	}
	
	/**
	 * 字符串尾补字符
	 * 
	 * @param source 源字符串
	 * @param length 总长度
	 * @param flag 字符
	 * @return length长度的字符串
	 */
	public static String appendToEnd(String source, int length, String character) {
		if (source == null) {
			return null;
		} else if (source.length() > length || length < 1) {
			return source;
		}
		while (source.length() < length) {
			source = source + character;
		}
		return source;
	}
	 


	 
	 
	 
 
	/**
	 * String[]to String
	 * 返回一定位数的字符串
	 */
	public static String  specialStr(String c,int len){
		String result="";
		for (int i = 0; i < len&&len>0; i++) {
			result=result+c;
		}
		return result;
	}	
	
	/**
	 * 返回一定位数的字符串
	 */
	public static String randNumStr(int num) {
		Random r = new Random(); 
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			sb.append(r.nextInt(10)+"");
		}
		return sb.toString();
	}
	/**
	 * 返回一定位数的字符串
	 */
	public static String randNumStr(int num,int range) {
		Random r = new Random(); 
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			sb.append(r.nextInt(range)+"");
		}
		return sb.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		 
		}
	
}