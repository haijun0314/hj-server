package org.jxjz.common.util.beans;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtilsBean;

public class Conversion {
	
    /**
     * 这里我们使用了第三方的工具类，可以帮之我们省去很多工作量，例如我们不需要自己通过反射的方法去获取bean中
     * 属性的值，PropertyUtilsBean可以帮我们搞定一切
     * @param bean
     * @return
     * @throws Exception
     */
    public static <T> Map<String, Object> conversionToMap(T bean)throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Class[] clazzArray = bean.getClass().getInterfaces();
        for (Class clazz : clazzArray) {
			if(clazz.equals(Map.class)){
				map.putAll((Map) bean);
				return map;
			}
		}
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(bean);

        for (PropertyDescriptor d : descriptors) {
            String fieldName = d.getName();
            Object value = propertyUtilsBean.getNestedProperty(bean, fieldName);
            if(!"class".equals(fieldName))
                map.put(fieldName, value);
        }
        return map;
    }
    
    public static <T> Map<String, Object> conversionToMapExcludeNull(T bean)throws Exception {
    	Map<String, Object> map = Conversion.conversionToMap(bean);
    	Map<String, Object> ret = new HashMap<String, Object>(); 
		for(String key : map.keySet()){
			Object value = map.get(key);
			if(value != null){
				ret.put(key, value);
			}
		}
		return ret;
    }
    
    public static <T> Map<String, Object> conversionToMapExcludeEmpty(T bean)throws Exception {
    	Map<String, Object> map = Conversion.conversionToMap(bean);
    	Map<String, Object> ret = new HashMap<String, Object>(); 
		for(String key : map.keySet()){
			Object value = map.get(key);
			if(value != null && !"".equals(value.toString().trim())){
				ret.put(key, value);
			}
		}
		return ret;
    }
//    public static void main(String[] args) {
//    	Map map = new HashMap();
//    	map.put("sda", 1);
//    	try {
//			conversionToMapByTool(map);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//	}
}
