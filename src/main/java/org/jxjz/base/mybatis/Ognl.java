package org.jxjz.base.mybatis;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
 
/**
 * 【功能说明】： Ognl工具类，主要是为了在ognl表达式访问静态方法时可以减少长长的类名称编写 Ognl访问静态方法的表达式为:
 * @author lihaijun
 * 【创建时间】2017-05-25
 */
@SuppressWarnings("all")
public class Ognl {

	/**
	 * 可以用于判断String,Map,Collection,Array是否为空
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isEmpty(Object o) throws IllegalArgumentException {
		if (o == null)
			return true;

		if (o instanceof String) {
			if (((String) o).length() == 0) {
				return true;
			}
		} else if (o instanceof Collection) {
			if (((Collection) o).isEmpty()) {
				return true;
			}
		} else if (o.getClass().isArray()) {
			if (Array.getLength(o) == 0) {
				return true;
			}
		} else if (o instanceof Map) {
			if (((Map) o).isEmpty()) {
				return true;
			}
		} else {
			return false;
			// throw new
			// IllegalArgumentException("Illegal argument type,must be : Map,Collection,Array,String. but was:"+o.getClass());
		}

		return false;
	}

	/**
	 * 可以用于判断 Map,Collection,String,Array是否不为空
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}

	public static boolean isNotBlank(Object o) {
		return !isBlank(o);
	}

	public static boolean isNumber(Object o) {
		if (o == null)
			return false;
		if (o instanceof Number) {
			return true;
		}
		if (o instanceof String) {
			String str = (String) o;
			if (str.length() == 0)
				return false;
			if (str.trim().length() == 0)
				return false;
			return StringUtils.isNumeric(str);
		}
		return false;
	}

	public static boolean isBlank(Object o) {
		if (o == null)
			return true;
		if (o instanceof String) {
			String str = (String) o;
			return isBlank(str);
		}
		return false;
	}

	public static boolean isBlank(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}

		for (int i = 0; i < str.length(); i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
