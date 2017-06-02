package org.jxjz.common.util.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.lang.time.DateFormatUtils;
import org.jxjz.common.util.ArrayUtil;
import org.jxjz.common.util.TimeUtil;
public class BeanUtil {

    /***************************************************************************************  
     * 转化bean对象的数据为Map【每个属性都要转化】
     * fields:表示需要处理的字段
     * flag:包含字段还是不包含
     ****************************************************************************************/ 
    public static <T> Map<String, Object> beanToMap(T bean,String[] fields,boolean flag) {  
        Class<? extends Object> type = bean.getClass();  
        Map<String, Object> returnMap = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(type);  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor descriptor : propertyDescriptors) {  
                String propertyName = descriptor.getName(); 
                boolean f_flag=ArrayUtil.checkExistStrArray(propertyName, fields);
                Method readMethod = descriptor.getReadMethod();  
                Object result = readMethod.invoke(bean, new Object[0]);  
                if (flag) {//只包含该数组字段
                	if (!propertyName.equals("class")&&f_flag) {  
                        returnMap.put(propertyName, result != null ? result : "");                    
                    }  
				}else {
					if (!propertyName.equals("class")&&!f_flag) {  
	                    returnMap.put(propertyName, result != null ? result : "");                    
	                }  
				}
                
            }  
        } catch (IntrospectionException e) {  
            throw new RuntimeException("分析类属性失败", e);  
        } catch (IllegalAccessException e) {  
            throw new RuntimeException("分析类属性失败", e);  
        } catch (InvocationTargetException e) {  
            throw new RuntimeException("分析类属性失败", e);  
        }  
        return returnMap;  
    }  
    /***************************************************************************************  
     * 转化map对象的数据为bean【每个属性都要转化】
     ****************************************************************************************/  	
    public static <T> T mapToBean(Map<String, Object> paramMap, Class<T> clazz) {   
        T beanObj = null;  
        try {  
            beanObj = clazz.newInstance();  
            String propertyName = null;  
            Object propertyValue = null;  
            for (Map.Entry<String, Object> entity : paramMap.entrySet()) {  
                propertyName = entity.getKey();  
                propertyValue = entity.getValue();  
                setProperties(beanObj, propertyName, propertyValue);  
            }  
        } catch (IllegalArgumentException e) {  
            throw new RuntimeException("不合法或不正确的参数", e);  
        } catch (IllegalAccessException e) {  
            throw new RuntimeException("实例化JavaBean失败", e);       
        } catch (Exception e) {  
            throw new RuntimeException("Map转换为Java Bean对象失败", e);  
        }  
        return beanObj;  
    }  	
    
    
    public static   void copyMapToBean(Object bean, Map paramMap) {
    	Iterator it = paramMap.keySet().iterator();
		while (it.hasNext())
		{
			String key = (String) it.next();
			Object value = paramMap.get(key);
			ConvertUtils.register(new DateConvert(), java.util.Date.class);  
			try {
				BeanUtils.setProperty(bean, key, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }  

    
    
    
    
    public static <T> void setProperties(T entity, String propertyName,  
            Object value) throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {  
        PropertyDescriptor pd = new PropertyDescriptor(propertyName, entity.getClass());  
        Method methodSet = pd.getWriteMethod();  
        methodSet.invoke(entity, value);  
    }    
    
    
    
    
    /***************************************************************************************  
     * 转化bean对象的数据为数组【每个属性都要转化】
     ****************************************************************************************/  
	public static Object[] getBeanValues(Object obj){
		DynaBean dynaBean = new LazyDynaBean();
		try {
			BeanUtils.copyProperties(dynaBean, obj);
			DynaProperty[] dynaProperty =dynaBean.getDynaClass().getDynaProperties();
			Object objs[]=new Object[dynaProperty.length];
			for (int j=0;j<dynaProperty.length;j++) {
				DynaProperty dp=dynaProperty[j];
				objs[j]=dynaBean.get(dp.getName()).toString();
				if(dp.getType().getName().equals("java.sql.Timestamp")){
					Timestamp sqlDate=(Timestamp)dynaBean.get(dp.getName());
					objs[j]=DateFormatUtils.format(sqlDate,"yyyy年MM月dd日 HH时mm分ss秒");
				};
			}
			return objs;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
    /***************************************************************************************  
     * 转化bean对象的数据为数组【只转化转化fields字段属性】
     * fields：要转化的bean 包含的字段
     ****************************************************************************************/  
	public static Map getBeanValues_map(Object obj,Object objs[],String[] fields,boolean idFlag){
			Map map=new HashMap();
			for (int j=0;j<fields.length&&fields.length>0;j++) {
				map.put( fields[j], getBeanValueByFieldName(obj, fields[j]));
			}
			return map;
	}	
	
    /***************************************************************************************  
     * 转化bean对象的数据为数组【只转化转化fields字段属性】
     * fields：要转化的bean 包含的字段
     ****************************************************************************************/  
	public static Object[] getBeanValues(Object obj,Object objs[],String[] fields,boolean idFlag){
			for (int j=0;j<fields.length&&fields.length>0;j++) {
				if(idFlag){
					objs[j+1]=getBeanValueByFieldName(obj, fields[j]);
				}else{
					objs[j]=getBeanValueByFieldName(obj, fields[j]);
				}
			}
			return objs;
	}
    /***************************************************************************************  
     * 查找bean中fieldName的对应值
     ****************************************************************************************/ 
	public static  String  getBeanValueByFieldName(Object obj,String fieldName){
		fieldName=fieldName.toLowerCase();
		DynaBean dynaBean = new LazyDynaBean();
		String beanFieldvalue="";		
		try {
			//拷贝obj的数据到动态Bean中
			BeanUtils.copyProperties(dynaBean, obj);
			//取得bean的属性
			DynaProperty[] dynaProperty =dynaBean.getDynaClass().getDynaProperties();
			for(int i=0;i<dynaProperty.length&&dynaProperty.length>0;i++){
				DynaProperty dp=dynaProperty[i];
				String name=dp.getName().toLowerCase();
				//判断属性名称和fieldName是否相同
				if(!name.equals(fieldName))continue;
				beanFieldvalue=dynaBean.get(dp.getName()).toString();
				//取值
				if(dp.getType().getName().equals("java.sql.Timestamp")){
					Timestamp sqlDate=(Timestamp)dynaBean.get(dp.getName());
					beanFieldvalue=DateFormatUtils.format(sqlDate,"yyyy年MM月dd日 HH:mm");
				};
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beanFieldvalue;
	}
	
	
	  // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map  
    public static Map<String, Object> beanToMap(Object obj) {  
        if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj); 
                    Class clazz=getter.getReturnType();
                    String className=clazz.getName();
                    if (className.equals("java.util.Date")) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        value= sdf.format(value);
					}
                    if (value==null) {
						continue;
					}
                    map.put(key, value.toString());  
                }  
  
            }  
        } catch (Exception e) {  
            System.out.println("transBean2Map Error " + e);  
        }  
  
        return map;  
  
    }  	
	
	
	
	
	
	
	
	
	
	
	
	
}
