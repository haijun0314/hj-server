package org.jxjz.common.util;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
public class JsonUtil {   
	 private static ObjectMapper objectMapper =  new ObjectMapper();;
	
	
    /**  
     * 从一个JSON 对象字符格式中得到一个java对象  
     */  
    public static Object json2Obj(String jsonStr,    Class pojoCalss) {   
    	Object pojo = null;
		try {
			pojo = objectMapper.readValue(jsonStr,pojoCalss);
		} catch (Exception e) {
			e.printStackTrace();
			return pojo;   
		}
        return pojo;   
    }   
  
    /**  
     * 将java对象转换成json字符串  
     */  
    public static String obj2Json(Object obj) {   
    	String json = null;
		try {
			json = objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return json;   
		}
        return json;   
    }   
    
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
    }     
  
    /**  
     * 将list转换成String 
     */  
    public static   List    json2List(String json ,Class obj) {   
    	 List lst =new ArrayList ();
    	try {
	        JavaType javaType = JsonUtil.getCollectionType(ArrayList.class, obj); 
	        lst =  (List )objectMapper.readValue(json, javaType); 
		} catch (Exception e) {
			e.printStackTrace();
			return lst;   
		}
        return lst;  
    }    
    
    
    /**  
     * 将list转换成String 
     */  
    public static String getStringFromList(List list) {   
    	String json = null;
		try {
			json = objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return json;   
		}
        return json;  
    }
    
}  
