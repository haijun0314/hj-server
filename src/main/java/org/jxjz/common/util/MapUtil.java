package org.jxjz.common.util;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

public class MapUtil
{
	
	/**
	 * 按键排序
	 */
    @SuppressWarnings("unchecked")
	public static Map  sortMapByKey(Map oriMap) {  
        if (oriMap == null || oriMap.isEmpty()) {  
            return null;  
        }  
        Map sortedMap = new TreeMap(new Comparator<String>() {  
            public int compare(String key1, String key2) {  
                int intKey1 = 0, intKey2 = 0;  
                try {  
                    intKey1 = getInt(key1);  
                    intKey2 = getInt(key2);  
                } catch (Exception e) {  
                    intKey1 = 0;   
                    intKey2 = 0;  
                }  
                return intKey1 - intKey2;  
            }});  
        sortedMap.putAll(oriMap);  
        return sortedMap;  
    }  
      
    private static int getInt(String str) {  
        int i = 0;  
        try {  
            Pattern p = Pattern.compile("^\\d+");  
            Matcher m = p.matcher(str);  
            if (m.find()) {  
                i = Integer.valueOf(m.group());  
            }  
        } catch (NumberFormatException e) {  
            e.printStackTrace();  
        }  
        return i;  
    }  	
	
	
	
	/**
	 *
	 */
	public static Object[]  getMapValues(Map map,Object objs[],boolean idflag,String[] fileds)
	{
		for (int j=0;j<fileds.length&&fileds.length>0;j++) {
			if(idflag){
				objs[j+1]=getMapValue(map, fileds[j]);
			}else{
				objs[j]=getMapValue(map, fileds[j]);
			}
		}
		return objs;
	}
	/**
	 *
	 */
	public static String getMapValue(Map map,String keyName)
	{
		String result="";
		Iterator it = map.keySet().iterator();
		while (it.hasNext())
		{
			String key = (String) it.next();
			if(StringUtils.lowerCase(key).equals(StringUtils.lowerCase(keyName))){
				Object obj=map.get(key);
				if(obj!=null&&obj instanceof java.util.Date ){
					Date sqlDate=(Date)obj;
					result=DateFormatUtils.format(sqlDate,"yyyy-MM-dd HH:mm:ss");
				}
				if(map.get(key)==null){
					result="";
				}else{
					result=map.get(key).toString();
				}
				
			}
		}
		 return result;
	}
	/**
	 * ��Map�е�value��null�ļ�ֵ��ɾ��
	 */
	public static void removeEmptyValue(Map map)
	{
		List list = new Vector();
		Iterator it = map.keySet().iterator();
		while (it.hasNext())
		{
			String key = (String) it.next();
			Object value = map.get(key);
			if (value == null || StringUtils.isEmpty(value.toString())
					|| value.toString().toLowerCase().equals("null"))
				list.add(key);
		}
		for (int i = 0; i < list.size(); i++)
		{
			map.remove(list.get(i));
		}
	}

	/**
	 * ��Map�е�value��null��ת��Ϊ���ַ�
	 */
	public static void blankNullValue(Map map)
	{
		Iterator it = map.keySet().iterator();
		while (it.hasNext())
		{
			String key = (String) it.next();
			Object value = map.get(key);
			if (value == null || value.toString().toLowerCase().equals("null"))
			{
				map.put(key, "");
			}
		}
	}
	public static void blankNullValue(Map map,String defaltValue)
	{
		Iterator it = map.keySet().iterator();
		while (it.hasNext())
		{
			String key = (String) it.next();
			Object value = map.get(key);
			if (value == null || value.toString().toLowerCase().equals("null"))
			{
				map.put(key, defaltValue);
			}
		}
	}
	/**
	 * ��Map�е�value��value��ת��Ϊ���ַ�
	 * 
	 */
	public static void blankExampleValue(Map map,String filtervalue,String[] fields)
	{
		Iterator it = map.keySet().iterator();
		while (it.hasNext())
		{
			String key = (String) it.next();
			if(!checkField(fields,key))continue;
			Object value = map.get(key);
			if (value == null || value.equals(filtervalue))
			{
				map.put(key, "");
			}
		}
	}
	private static boolean checkField(String[] fields,String key){
		if(fields==null||fields.length==0)return true;
		boolean result=false;
		for(int i=0;i<fields.length;i++){
			String field=fields[i];
			if(key.equals(field)){
				result=true;
			}
		}
		return result;
		
	}
	
	/**
	 * ��Map�е�ָ��parameter[]�еļ�ɾ��
	 */
	public static void removeKey(Map map, String[] parameter)
	{
		for (int i = 0; i < parameter.length; i++)
		{
			map.remove(parameter[i]);
		}
	}

	/**
	 * ��Map�е�ָ��parameter[]�еļ��Ƶ�һ���µ�Map��
	 */
	public static Map copyMap(Map map, String[] parameter)
	{
		Map newmap = new HashMap();
		for (int i = 0; i < parameter.length; i++)
		{
			if (map.get(parameter[i]) != null)
				newmap.put(parameter[i], map.get(parameter[i]));
			else
				newmap.put(parameter[i], "");
		}
		return newmap;
	}

	/**
	 * ��Map�е�ָ��parameter[]�еļ��fromMap���Ƶ�toMap��
	 */
	public static Map addMap(Map fromMap, Map toMap, String[] parameter)
	{
		for (int i = 0; i < parameter.length; i++)
		{
			if (fromMap.get(parameter[i]) != null)
				toMap.put(parameter[i], fromMap.get(parameter[i]));
		}
		return toMap;
	}

	/**
	 * ��Map���Ƿ��пյ�ֵ
	 */
	public static boolean hasEmptyValue(Map map)
	{
		Iterator it = map.keySet().iterator();
		boolean falg = false;
		while (it.hasNext())
		{
			String key = (String) it.next();
			Object value = map.get(key);
			if (!StringUtils.isEmpty(value.toString()))
			{
				falg = true;
			}
		}
		return falg;
	}

	/**
	 * ��request.getParameterMap<String,String[]>�е�Map ת��Ϊ<String,String>
	 */
	public static Map convertParameterMap(Map map)
	{
		Map newMap = new HashMap();
		Iterator it = map.keySet().iterator();
		while (it.hasNext())
		{
			String key = (String) it.next();
			String[] val = (String[]) map.get(key);
			if (val.length == 1)
			{
				newMap.put(key, val[0]);
			}
			else
			{
				String newstr = "";
				for (int i = 0; i < val.length; i++)
				{
					newstr = newstr + val[i] + ",";
				}
				newstr = newstr.substring(0, newstr.length() - 1);
				newMap.put(key, newstr);
			}
		}
		return newMap;
	}

	public static String convertStringArrayToValue(Map map, String key, String dilm)
	{
		try
		{
			String strarray[] = (String[]) map.get(key);
			String newstr = "";
			for (int i = 0; i < strarray.length; i++)
			{
				newstr = newstr + strarray[i] + dilm;
			}
			newstr = newstr.substring(0, newstr.length() - 1);
			return newstr;
		}
		catch (Exception e)
		{
			String str = (String) map.get(key);
			if (str != null)
				return str;
		}
		return "";
	}

	public static String replaceMapString(String str, Map map)
	{
		if (map == null)
			return str;
		Iterator it = map.keySet().iterator();
		while (it.hasNext())
		{
			String key = (String) it.next();
			Object val = map.get(key);
			if (val == null)
				continue;
			str = StringUtils.replace(str, "${" + key + "}", val.toString());
		}
		return str;
	}

	public static void filterMap(Map map)
	{
		List list = new Vector();
		Iterator it = map.keySet().iterator();
		while (it.hasNext())
		{
			String key = (String) it.next();
			Object value = map.get(key);
			if (value == null || StringUtils.isEmpty(value.toString()))
				list.add(key);
		}
		for (int i = 0; i < list.size(); i++)
		{
			map.remove(list.get(i));
		}
	}
	/**
	 * 格式化  将数据存储为字符串
	 * @param map
	 */
	public static void formatMap(Map map)
	{
		List list = new Vector();
		Iterator it = map.keySet().iterator();
		while (it.hasNext())
		{
			String key = (String) it.next();
			Object value = map.get(key);
			if (value == null || StringUtils.isEmpty(value.toString())){
				list.add(key);
				continue;
			}
			map.put(key, value.toString());
		}
		for (int i = 0; i < list.size(); i++)
		{
			map.remove(list.get(i));
		}
	}	
	
	
	
	
	
	
	
}
